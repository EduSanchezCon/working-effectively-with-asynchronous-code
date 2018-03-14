package com.edusancon.wewac.bigbrother.filler;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class AbstractFillerTest {

    private final Object input = new Object();
    private final Object output = new Object();

    private AbstractFiller<Object, Object> sut = createConcreteFiller();


    @Test
    public void shouldRetrieveInfoAndTransformInput(){

        final CompletableFuture<UnaryOperator<Object>> functionFuture = sut.get();
        final UnaryOperator<Object> function = functionFuture.join();

        assertThat(function.apply(input), is(output));
    }


    private AbstractFiller<Object, Object> createConcreteFiller() {
        return new AbstractFiller<Object, Object>() {
            @Override
            protected CompletableFuture<Object> obtainInfo() {
                return CompletableFuture.completedFuture(output);
            }

            @Override
            protected UnaryOperator<Object> getFillerFunction(Object info) {
                return o -> info;
            }
        };
    }
}