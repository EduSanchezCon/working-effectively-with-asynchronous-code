package com.edusancon.wewac.util;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

public class FunctionCollector<T> implements Collector<UnaryOperator<T>, T, T> {

    private final T original;

    public FunctionCollector(T original) {
        this.original = original;
    }

    @Override
    public Supplier<T> supplier() {
        return () -> original;
    }

    @Override
    public BiConsumer<T, UnaryOperator<T>> accumulator() {
        return (acc, fun) -> fun.apply(acc);
    }

    @Override
    public BinaryOperator<T> combiner() {
        return null;
    }

    @Override
    public Function<T, T> finisher() {
        return p -> p;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.UNORDERED, Characteristics.IDENTITY_FINISH);
    }
}
