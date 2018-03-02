package com.edusancon.wewac.bigbrother.supplier;

import com.edusancon.wewac.students.model.Practice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

class RandomListSupplierTest {

    @Test
    public void testRandomListGeneration(){

        final List<Practice> list = new RandomListSupplier<>(Practice.class).get();

        assertThat(list, hasSize(greaterThan(0)));
    }
}