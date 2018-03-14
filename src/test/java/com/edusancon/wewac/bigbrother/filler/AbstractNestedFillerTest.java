package com.edusancon.wewac.bigbrother.filler;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class AbstractNestedFillerTest {

    private final Object input = new Object();
    private final Object output = new Object();

    private AbstractNestedFiller<Object, Object, Object> sut;


    @Test
    public void shouldRetrieveInfoAndTransformInput(){

        sut  = createConcreteFiller(new Object(), true);

        final CompletableFuture<UnaryOperator<Object>> functionFuture = sut.get();
        final UnaryOperator<Object> function = functionFuture.join();

        assertThat(function.apply(input), is(output));
    }

    @Test
    public void whenPreviousFillerFailsThenShouldNotChangeOriginalObject(){

        sut  = spy( createConcreteFiller(new IllegalArgumentException(), true));

        final CompletableFuture<UnaryOperator<Object>> functionFuture = sut.get();
        final UnaryOperator<Object> function = functionFuture.join();

        verify(sut, never()).obtainInfo(any());

        assertThat(function.apply(input), is(input));
    }

    @Test
    public void whenConditionToExecuteIsFalseThenShouldNotChangeOriginalObject(){

        sut  = spy( createConcreteFiller(new Object(), false));

        final CompletableFuture<UnaryOperator<Object>> functionFuture = sut.get();
        final UnaryOperator<Object> function = functionFuture.join();

        verify(sut, never()).obtainInfo(any());

        assertThat(function.apply(input), is(input));
    }


    private AbstractNestedFiller<Object, Object, Object> createConcreteFiller(Object prevValue, boolean mustExecute) {

        final Filler<Object, Object> previousFiller = getPreviousFiller(prevValue);
        previousFiller.get();

        return new AbstractNestedFiller<Object, Object, Object>(previousFiller) {
            @Override
            protected CompletableFuture<Object> obtainInfo(Object prev) {
                return CompletableFuture.completedFuture(output);
            }

            @Override
            protected UnaryOperator<Object> getFillerFunction(Object info, Object prev) {
                return o -> info;
            }

            @Override
            protected boolean conditionToExecute(Object previousResult) {
                return mustExecute;
            }
        };
    }

    private Filler<Object, Object> getPreviousFiller(final Object prevValue){

        return new AbstractFiller<Object, Object>() {
            @Override
            protected CompletableFuture<Object> obtainInfo() {
                if (prevValue instanceof Exception){
                    return CompletableFuture.failedFuture((Throwable) prevValue);
                }
                return CompletableFuture.completedFuture(prevValue);
            }

            @Override
            protected UnaryOperator<Object> getFillerFunction(Object info) {
                return o -> info;
            }
        };
    }
}