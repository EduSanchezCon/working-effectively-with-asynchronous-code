package com.edusancon.wewac;

import com.edusancon.wewac.students.model.Course;
import com.edusancon.wewac.students.task.GetCourseInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureJava7Test {

    @Test
    public void getInfoFrom3Courses(){

//        List<Future<Course>> futures = IntStream.rangeClosed(1, 3).boxed()
//                .map( (id) -> executor.submit( () -> getCourseInfo.apply(id).get()))
//                .collect(Collectors.toList());

        ExecutorService executor = Executors.newFixedThreadPool(3);
        final GetCourseInfo getCourseInfo = new GetCourseInfo();

        List<Future<Course>> futures = new ArrayList<>();
        for (int i=1; i<=3; i++){
            final int id = i;
            Future<Course> future = executor.submit(
                    () -> getCourseInfo.apply(id).get()
            );
            futures.add(future);
        }

        for (Future<Course> future : futures){
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {}
        }
    }
}
