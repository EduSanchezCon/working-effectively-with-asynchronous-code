package com.edusancon.wewac.bigbrother.filler;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public abstract class AbstractNestedFiller<T, U, V> implements Filler<T, U>{

    private Filler<?, V> previousFiller;

    private final CompletableFuture<U> infoPromise;

    public AbstractNestedFiller(Filler<?, V> previousFiller){
        this.previousFiller = previousFiller;
        infoPromise = new CompletableFuture<U>();
    }

    public CompletableFuture<UnaryOperator<T>> get(){

        return previousFiller.getInfoPromise().thenComposeAsync(
                v ->  {
                    if (conditionToExecute(v)) {
                        return obtainInfo(v)
                                .whenComplete((value,ex)-> {
                                        if (ex != null) infoPromise.completeExceptionally(ex);
                                        else infoPromise.complete(value);})
                                .thenApply(info -> getFillerFunction(info, v));
                    } else {
                        return CompletableFuture.completedFuture(x -> x);
                    }
                }

        ).handle((f, ex2) -> {
            if (ex2 != null) {
                infoPromise.completeExceptionally(ex2);
                return x -> x;
            }
            else return f;
        });

    }

    public CompletableFuture<U> getInfoPromise() {
        return infoPromise;
    }

    protected boolean conditionToExecute(V previousResult){
        return true;
    }

    abstract protected CompletableFuture<U> obtainInfo(V previousResult);

    abstract protected UnaryOperator<T> getFillerFunction(U info, V previousInfo);
}
