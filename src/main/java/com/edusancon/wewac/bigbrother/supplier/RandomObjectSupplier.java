package com.edusancon.wewac.bigbrother.supplier;

import com.edusancon.wewac.util.RandomGenerator;

import java.lang.reflect.Type;
import java.util.function.Supplier;

public class RandomObjectSupplier<T>  implements Supplier<T>{

    private final Type type;

    public RandomObjectSupplier(Class<T> clazz){
        type = clazz;
    }

    public T get(){
        return (T) RandomGenerator.getRandomObject(type);
    }
}
