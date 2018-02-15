package com.edusancon.wewac.students.task.decorator;

import com.edusancon.wewac.util.ThreadHelper;

import java.util.function.Function;

public class LongTask<I, O> implements Function<I, O> {

    protected final long delay;

    private final Function<I, O> function;

    public LongTask(long delay, Function<I, O> function) {
        this.delay = delay;
        this.function = function;
    }

    @Override
    public O apply(final I input) {

        ThreadHelper.sleep(delay);

        return function.apply(input);
    }
}
