package com.edusancon.wewac.students.model;

import com.edusancon.wewac.util.JsonWritable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("serial")
public class Course extends JsonWritable {

    private final  int id;
    private final String name;
    private Collection<Practice> practices;
    @JsonIgnore
    private final int[] practiceIds;

    @JsonCreator
    public Course(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("practices") int[] practiceIds) {
        this.id = id;
        this.name = name;
        this.practiceIds = practiceIds;
        this.practices = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Practice> getPractices() {
        return practices;
    }

    public void setPractices(Collection<Practice> practices) {
        this.practices = practices;
    }

    public int[] getPracticeIds() {
        return practiceIds;
    }
}

