package com.edusancon.wewac.bigbrother.model;

import com.edusancon.wewac.util.JsonWritable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("serial")
public class Person extends JsonWritable {

    private final long id;
    private String name;
    private String passport;
    private LocalDate birthday;

    private List<Insurance> insurances;
    private MedicalInfo medicalInfo;
    private List<BankAccount> bankAccounts;
    private AcademicInfo academicInfo;

    @JsonCreator
    public Person(
            @JsonProperty("id") long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public MedicalInfo getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(MedicalInfo medicalInfo) {
        this.medicalInfo = medicalInfo;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public AcademicInfo getAcademicInfo() {
        return academicInfo;
    }

    public void setAcademicInfo(AcademicInfo academicInfo) {
        this.academicInfo = academicInfo;
    }


}
