package com.edusancon.wewac.bigbrother.model;

import com.edusancon.wewac.util.JsonWritable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MedicalInfo extends JsonWritable {

    private final String medicalCard;
    private String name;
    private Date birthdate;

    @JsonCreator
    public MedicalInfo(
           @JsonProperty("medicalCard") String medicalCard) {
        this.medicalCard = medicalCard;
    }

    public String getMedicalCard() {
        return medicalCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
