package com.edusancon.wewac.students.task;

import com.edusancon.wewac.students.model.Student;
import com.edusancon.wewac.util.JsonReader;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class GetStudentInfo implements Function<Integer, Optional<Student>> {


    @Override
    public Optional<Student> apply(final Integer studentId) {

        List<Student> students = JsonReader.STUDENTS.get();
        return students.stream().filter(x -> x.getId() == studentId).findAny();
    }
}
