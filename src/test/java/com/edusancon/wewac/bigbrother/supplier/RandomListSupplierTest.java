package com.edusancon.wewac.bigbrother.supplier;

import com.edusancon.wewac.students.model.Practice;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

class RandomListSupplierTest {

    @Test
    public void testRandomListGeneration(){

        final CompletableFuture<List<Practice>> future = new RandomListSupplier<Practice>(Practice.class, 1000L).get();

        System.out.println(future.join());
    }
}