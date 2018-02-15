package com.edusancon.wewac.students.model;

import com.edusancon.wewac.util.JsonWritable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("serial")
public class Student extends JsonWritable {

    private final int id;
    private final String name;
    @JsonIgnore
    private final int[] courseIds;
    private Collection<Course> courses;

    @JsonCreator
    public Student(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("courses") int[] courseIds) {
        this.id = id;
        this.name = name;
        this.courseIds = courseIds;
        this.courses = new ArrayList<>();
    }


    public Collection<Course> getCourses() {
        return courses;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int[] getCourseIds() {
        return courseIds;
    }

}
