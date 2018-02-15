package com.edusancon.wewac.bigbrother.supplier;

import com.edusancon.wewac.bigbrother.model.AcademicInfo;
import com.edusancon.wewac.students.model.Course;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

class RandomObjectSupplierTest {

    @Test
    public void testRandomObjectGeneration(){

        final CompletableFuture<Course> course = new RandomObjectSupplier<Course>(Course.class, 1000L).get();

        System.out.println(course.join());
    }

    @Test
    public void testRandomObjectGeneration2(){

        final CompletableFuture<AcademicInfo> future = new RandomObjectSupplier<AcademicInfo>(AcademicInfo.class, 1000L).get();

        System.out.println(future.join());
    }
}