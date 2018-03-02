package com.edusancon.wewac.bigbrother.supplier;

import com.edusancon.wewac.bigbrother.model.AcademicInfo;
import com.edusancon.wewac.students.model.Course;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.greaterThan;

class RandomObjectSupplierTest {

    @Test
    public void testRandomObjectGeneration(){

        final Course course = new RandomObjectSupplier<>(Course.class).get();

        assertThat(course, notNullValue());
        assertThat(course.getName(), not( isEmptyString()));
    }

    @Test
    public void testRandomObjectGeneration2(){

        final AcademicInfo academicInfo = new RandomObjectSupplier<>(AcademicInfo.class).get();

        assertThat(academicInfo, notNullValue());
        assertThat(academicInfo.getQualifications(), hasSize(greaterThan(0)));
    }
}