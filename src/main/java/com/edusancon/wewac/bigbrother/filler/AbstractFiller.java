package com.edusancon.wewac.bigbrother.filler;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public abstract class AbstractFiller<T, U> {


    public <P> CompletableFuture<UnaryOperator<T>> get(final P original){

        return obtainInfo(original)
                .thenApply(info -> getFillerFunction(info));
    }

    abstract protected <P> CompletableFuture<U> obtainInfo(P original);

    abstract protected UnaryOperator<T> getFillerFunction(U info);
}
