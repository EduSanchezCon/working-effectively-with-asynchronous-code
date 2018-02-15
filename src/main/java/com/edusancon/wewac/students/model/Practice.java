package com.edusancon.wewac.students.model;

import com.edusancon.wewac.util.JsonWritable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class Practice extends JsonWritable {

    private final int id;
    private final String name;
    private final String description;

    @JsonCreator
    public Practice(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
