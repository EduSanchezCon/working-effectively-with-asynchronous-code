package com.edusancon.wewac.bigbrother.supplier;

import com.edusancon.wewac.util.ThreadHelper;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class RandomListSupplier<T>  implements Supplier<CompletableFuture<List<T>>>{


    private final ParameterizedType parameterizedType;
    private final long delay;

    public RandomListSupplier(Class<T> clazz){

        this(clazz, 1000L);
    }

    public RandomListSupplier(Class<T> clazz, long delay){

        parameterizedType = TypeUtils.parameterize(List.class, clazz);
        this.delay = delay;
    }

    public CompletableFuture<List<T>> get(){
        return CompletableFuture.supplyAsync(
                () -> {
                    ThreadHelper.sleep(delay);
                    return (List<T>) RandomGenerator.getRandomObject(parameterizedType);
                }, ThreadHelper.APP_EXECUTOR);
    }

}
