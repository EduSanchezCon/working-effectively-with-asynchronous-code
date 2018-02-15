package com.edusancon.wewac.bigbrother.supplier;

import com.edusancon.wewac.util.ThreadHelper;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class RandomObjectSupplier<T>  implements Supplier<CompletableFuture<T>>{

    private final Type type;
    private final long delay;

    public RandomObjectSupplier(Class<T> clazz){
        this(clazz, 1000L);
    }

    public RandomObjectSupplier(Class<T> clazz, long delay){
        type = clazz;
        this.delay = delay;
    }

    public CompletableFuture<T> get(){
        return CompletableFuture.supplyAsync(
                () -> {
                    ThreadHelper.sleep(delay);
                    return (T) RandomGenerator.getRandomObject(type);
                }, ThreadHelper.APP_EXECUTOR);
    }
}
