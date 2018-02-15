package com.edusancon.wewac.students.task.decorator;

import com.edusancon.wewac.util.ThreadHelper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class DelayedSupplier<T> implements Supplier<CompletableFuture<T>> {

    private final Supplier<T> innerSupplier;
    private final long delay;

    public DelayedSupplier(Supplier<T> innerSupplier) {
        this(innerSupplier, 1000L);
    }

    public DelayedSupplier(Supplier<T> innerSupplier, long delay) {
        this.innerSupplier = innerSupplier;
        this.delay = delay;
    }

    @Override
    public CompletableFuture<T> get() {
        return CompletableFuture.supplyAsync(
                () -> {
                    ThreadHelper.sleep(delay);
                    return innerSupplier.get();
                });
    }
}
