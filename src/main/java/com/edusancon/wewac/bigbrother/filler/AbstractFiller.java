package com.edusancon.wewac.bigbrother.filler;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public abstract class AbstractFiller<T, U> implements Filler<T, U> {

    private final CompletableFuture<U> infoPromise;

    public AbstractFiller(){

        infoPromise = new CompletableFuture<U>();
    }

    @Override
    public CompletableFuture<UnaryOperator<T>> get(){

        return obtainInfo()
                .whenComplete((u,e)-> {
                    if (e != null) infoPromise.completeExceptionally(e);
                    else infoPromise.complete(u);})
                .thenApply(info -> getFillerFunction(info));
    }

    public CompletableFuture<U> getInfoPromise() {
        return infoPromise;
    }

    abstract protected CompletableFuture<U> obtainInfo();

    abstract protected UnaryOperator<T> getFillerFunction(U info);
}
