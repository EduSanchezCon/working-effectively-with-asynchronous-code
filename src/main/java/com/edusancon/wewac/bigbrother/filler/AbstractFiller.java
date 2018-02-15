package com.edusancon.wewac.bigbrother.filler;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public abstract class AbstractFiller<T, U> {


    public CompletableFuture<UnaryOperator<T>> get(final T original){

        return obtainInfo(original)
                .thenApply(info -> getFillerFunction(info));
    }

    abstract protected CompletableFuture<U> obtainInfo(T original);

    abstract protected UnaryOperator<T> getFillerFunction(U info);
}
