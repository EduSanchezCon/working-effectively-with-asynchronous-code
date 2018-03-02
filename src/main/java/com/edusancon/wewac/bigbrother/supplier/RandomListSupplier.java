package com.edusancon.wewac.bigbrother.supplier;

import com.edusancon.wewac.util.RandomGenerator;
import com.edusancon.wewac.util.ThreadHelper;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class RandomListSupplier<T>  implements Supplier<List<T>>{

    private final ParameterizedType parameterizedType;

    public RandomListSupplier(Class<T> clazz){
        parameterizedType = TypeUtils.parameterize(List.class, clazz);
    }

    public List<T> get(){
        return (List<T>) RandomGenerator.getRandomObject(parameterizedType);
    }

}
