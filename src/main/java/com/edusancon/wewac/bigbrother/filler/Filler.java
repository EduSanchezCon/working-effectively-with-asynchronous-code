package com.edusancon.wewac.bigbrother.filler;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public interface Filler<T, U> {

    CompletableFuture<UnaryOperator<T>> get();

    CompletableFuture<U> getInfoPromise();
}
