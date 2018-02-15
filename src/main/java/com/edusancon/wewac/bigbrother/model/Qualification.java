package com.edusancon.wewac.bigbrother.model;

import com.edusancon.wewac.util.JsonWritable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Qualification extends JsonWritable {

    private final String subject;
    private final float qualification;

    @JsonCreator
    public Qualification(
            @JsonProperty("name") String subject,
            @JsonProperty("qualification") float qualification) {
        this.subject = subject;
        this.qualification = qualification;
    }

    public String getSubject() {
        return subject;
    }

    public float getQualification() {
        return qualification;
    }
}
