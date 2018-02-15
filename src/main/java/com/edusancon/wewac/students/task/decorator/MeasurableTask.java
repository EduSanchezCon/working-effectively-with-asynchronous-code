package com.edusancon.wewac.students.task.decorator;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;

public class MeasurableTask<I, O> implements Function<I, O> {

    protected final String taskName;

    private final Function<I, O> function;

    public MeasurableTask(String taskName, Function<I, O> function) {
        this.taskName = taskName;
        this.function = function;
    }

    @Override
    public O apply(final I input) {

        Instant a = Instant.now();

        O out = function.apply(input);

        System.out.format("*****\nTask %s executed in %d millis\n*****\n", taskName, Duration.between(a, Instant.now()).toMillis());

        return out;
    }
}
