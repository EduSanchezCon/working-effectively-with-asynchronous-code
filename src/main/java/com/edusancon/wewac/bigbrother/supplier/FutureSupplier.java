package com.edusancon.wewac.bigbrother.supplier;

import com.edusancon.wewac.util.ThreadHelper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class FutureSupplier<T> implements Supplier<CompletableFuture<T>> {

    private final Supplier<T> innerSupplier;
    private final long delay;

    public FutureSupplier(Supplier<T> innerSupplier) {
        this(innerSupplier, 1000L);
    }

    public FutureSupplier(Supplier<T> innerSupplier, long delay) {
        this.innerSupplier = innerSupplier;
        this.delay = delay;
    }

    @Override
    public CompletableFuture<T> get() {
        return CompletableFuture.supplyAsync(
                () -> {
                    ThreadHelper.sleep(delay);
                    return innerSupplier.get();
                }, ThreadHelper.APP_EXECUTOR);
    }
}
