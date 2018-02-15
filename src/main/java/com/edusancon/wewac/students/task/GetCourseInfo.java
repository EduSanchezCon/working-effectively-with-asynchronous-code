package com.edusancon.wewac.students.task;

import com.edusancon.wewac.students.model.Course;
import com.edusancon.wewac.util.JsonReader;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class GetCourseInfo implements Function<Integer, Optional<Course>> {


    @Override
    public Optional<Course> apply(final Integer courseId) {

        List<Course> courses = JsonReader.COURSES.get();
        return courses.stream().filter(x -> x.getId() == courseId).findAny();
    }
}
