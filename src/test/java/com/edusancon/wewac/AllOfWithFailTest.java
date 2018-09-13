package com.edusancon.wewac;

import com.edusancon.wewac.util.ThreadHelper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllOfWithFailTest {

    public static final Executor EXECUTOR = Executors.newFixedThreadPool(3);

    @Test
    public void shouldFailFast() throws ExecutionException, InterruptedException {

        final CompletableFuture<Integer> composedFuture = new CompletableFuture<>();

        final List<CompletableFuture<Integer>> futures =
                IntStream.rangeClosed(1, 5).boxed()
                    .map(this::getLater)
                    .collect(Collectors.toList());

        futures.add(failLater(1000L));

        futures.addAll(IntStream.rangeClosed(6, 10).boxed()
                .map(this::getLater)
                .collect(Collectors.toList()));

        futures.stream().forEach(
                f -> f.whenComplete((v,e) -> {
                    if (e != null) composedFuture.completeExceptionally(e);
                    else System.out.println("Futuro completo con valor " + v);
                }));


        final CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenRun(() -> composedFuture.complete(42));

        composedFuture
                .handle((v,e) -> {
                    if (e != null) {
                        System.out.println("ha ocurrido un error");
                        futures.stream().forEach(f -> f.cancel(true));
                        return 0;
                    }
                    else {
                        System.out.println("Se acabó lo que se daba");
                        return v;
                    }
                }).get();

        allFutures
                // esta línea la pongo para que no salga la excepción para fuera
                .handle((v,e) -> {return (e != null) ? 0 : v;})
                .get();

        // esta línea se puede poner para comprobar que efectivamente, las tareas de los futuros no se están ejecutando
        //ThreadHelper.sleep(5000L);

    }

    private <T> CompletableFuture<T> getLater(final T value) {
        return CompletableFuture.supplyAsync(
                () -> {
                    ThreadHelper.sleep(3000L);
                    return value;
                }, EXECUTOR);
    }

    private <T> CompletableFuture<T> failLater(Long delay) {
        return CompletableFuture.supplyAsync(new Supplier<T>() {
            @Override
            public T get() {
                ThreadHelper.sleep(delay);
                throw new RuntimeException();
            }
        }, EXECUTOR);
    }
}
