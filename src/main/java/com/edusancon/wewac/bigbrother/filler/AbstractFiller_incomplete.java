package com.edusancon.wewac.bigbrother.filler;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public abstract class AbstractFiller_incomplete<T, U>  {

    public CompletableFuture<UnaryOperator<T>> get(){

        return obtainInfo()
                .thenApply(info -> getFillerFunction(info));
    }

    abstract protected CompletableFuture<U> obtainInfo();

    abstract protected UnaryOperator<T> getFillerFunction(U info);
}
