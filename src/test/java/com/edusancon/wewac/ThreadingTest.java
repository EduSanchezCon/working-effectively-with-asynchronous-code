package com.edusancon.wewac;

import com.edusancon.wewac.util.ThreadHelper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadingTest {

    public static final Executor EXECUTOR = Executors.newFixedThreadPool(10);

    @Test
    public void defaultExecutorTest() throws ExecutionException, InterruptedException {



        final List<CompletableFuture<Integer>> futures = IntStream.rangeClosed(1, 8).boxed()
                .map(this::getLater)
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, ex) -> System.out.println("Se acabó lo que se daba"))
                .get();

    }

    private <T> CompletableFuture<T> getLater(final T value) {
        return CompletableFuture.supplyAsync(
                () -> {
                    ThreadHelper.sleep(1000L);
                    return value;
                }, EXECUTOR);
    }
}
