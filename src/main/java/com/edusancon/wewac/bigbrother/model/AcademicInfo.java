package com.edusancon.wewac.bigbrother.model;

import com.edusancon.wewac.util.JsonWritable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AcademicInfo extends JsonWritable {

    private final String name;
    private List<Qualification> qualifications;

    @JsonCreator
    public AcademicInfo(
           @JsonProperty("degree") String degree) {
        this.name = degree;
    }

    public String getName() {
        return name;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }
}
