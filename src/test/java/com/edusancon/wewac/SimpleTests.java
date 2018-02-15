package com.edusancon.wewac;

import com.edusancon.wewac.students.model.Course;
import com.edusancon.wewac.students.model.Practice;
import com.edusancon.wewac.students.model.Student;
import com.edusancon.wewac.students.task.GetCourseInfo;
import com.edusancon.wewac.students.task.GetPracticeInfo;
import com.edusancon.wewac.students.task.GetStudentInfo;
import com.edusancon.wewac.students.task.decorator.LongTask;
import com.edusancon.wewac.students.task.decorator.MeasurableTask;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class SimpleTests {

    @Test
    public void asynchronousCalls(){

        final LongTask<Integer, Optional<Course>> getCourseInfo = new LongTask<>(2000L, new GetCourseInfo());

        Course course = getCourseInfo.apply(1).get();
        System.out.println(course);

        course = getCourseInfo.apply(3).get();
        System.out.println(course);
    }

    @Test
    public void asynchronousCallPractices(){

        final LongTask<Integer, Optional<Practice>> getPracticeInfo = new LongTask<>(1000L, new GetPracticeInfo());

        Practice practice = getPracticeInfo.apply(1).get();
        System.out.println(practice);
    }

    @Test
    public void asynchronousCallStudentsWithTimeMeasurement(){

        final MeasurableTask<Integer, Optional<Student>> getStudentInfo =
                new MeasurableTask<>("getStudentInfo",
                        new LongTask<>(3000L, new GetStudentInfo()));

        Student student = getStudentInfo.apply(1).get();
        System.out.println(student);
    }

}
