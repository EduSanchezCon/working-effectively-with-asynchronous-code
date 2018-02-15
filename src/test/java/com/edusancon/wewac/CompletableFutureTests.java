package com.edusancon.wewac;

import com.edusancon.wewac.students.model.Course;
import com.edusancon.wewac.students.task.GetCourseInfo;
import com.edusancon.wewac.students.task.decorator.LongTask;
import com.edusancon.wewac.util.JsonSerializer;
import com.edusancon.wewac.util.ThreadHelper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class CompletableFutureTests {

    @Test
    public void extractValuesFromFutures(){

        ExecutorService executor = Executors.newFixedThreadPool(3);
        final Function<Integer, Optional<Course>> getCourseInfo = new LongTask<>(1000L,  new GetCourseInfo());

        List<CompletableFuture<Course>> futures = new ArrayList<>();
        for (int i=1; i<=3; i++){
            final int id = i;
            CompletableFuture<Course> future = CompletableFuture.supplyAsync(
                    () -> new LongTask<>(1000L * id,  new GetCourseInfo()).apply(id).get()
                    , executor);
            futures.add(future);
        }

        for (CompletableFuture<Course> future : futures){
            System.out.println(future.join());

        }
    }

    @Test
    public void testMapping(){

        final CompletableFuture<String> future = CompletableFuture.completedFuture( "hola");

        future.thenApply( String::toUpperCase );

        future.thenAccept( System.out::println );

        future.thenRun( () -> System.out.println("La tarea terminó") );

        future.whenComplete((v, ex) -> {
            if (ex != null) System.out.println(v);
        } );

        ThreadHelper.sleep(500L);
    }

    @Test
    public void testCompose(){

        // Una llamada depende de la otra
        CompletableFuture<Integer> future = duplicateAsync(3)
                .thenCompose( n -> duplicateAsync(n));

        future.thenAccept(System.out::println);
    }

    private CompletableFuture<Integer> duplicateAsync(final int i){
        return CompletableFuture.supplyAsync(() -> 2*i);
    }

    @Test
    public void testCombine(){

        //Combinar dos llamadas en paralelo
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync( () -> "MOLA");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync( () -> "MAZO");

        CompletableFuture<String> combine = future1.thenCombine(future2,
                (s1, s2) -> s1 + " " + s2);



        combine.thenAccept(System.out::println);
    }


    @Test
    public void testTakeFirst(){

        List<CompletableFuture<Course>> futures = new ArrayList<>();
        for (int i=1; i<=3; i++){
            final int id = i;
            CompletableFuture<Course> future = CompletableFuture.supplyAsync(
                    () -> new LongTask<>(1000L*(4-id),  new GetCourseInfo()).apply(id).get());
            futures.add(future);
        }

        CompletableFuture[] arrayOfFutures = futures.toArray(new CompletableFuture[futures.size()]);

        // Se queda con el primer futuro que se resuelva
        CompletableFuture<Object> fastestFuture = CompletableFuture.anyOf(arrayOfFutures);

        fastestFuture.whenComplete((course, th) -> {
            if(th == null) {
                System.out.println(course);
            }
        }).join();
    }

    @Test
    public void testWaitForAll(){

        List<CompletableFuture<Course>> futures = new ArrayList<>();
        for (int i=1; i<=3; i++){
            final int id = i;
            CompletableFuture<Course> future = CompletableFuture.supplyAsync(
                    () -> new LongTask<>(1000L*(4-id),  new GetCourseInfo()).apply(id).get());
            futures.add(future);
        }

        CompletableFuture<Course>[] arrayOfFutures = futures.toArray(new CompletableFuture[futures.size()]);

        // Espera hasta resolver todos los futuros
        CompletableFuture<Void> all = CompletableFuture.allOf(arrayOfFutures);

        all.thenAccept(v -> {
            Arrays.stream(arrayOfFutures)
                    .map(CompletableFuture::join)
                    .map(JsonSerializer.ONE_LINE::serialize)
                    .forEach(System.out::println);
        }).join();

    }
}
