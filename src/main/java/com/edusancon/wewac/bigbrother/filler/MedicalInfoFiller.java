package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.MedicalInfo;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.MedicalInfoRepository;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class MedicalInfoFiller extends AbstractFiller<Person, MedicalInfo>{


    private final MedicalInfoRepository repository;

    public MedicalInfoFiller(MedicalInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected <P> CompletableFuture<MedicalInfo> obtainInfo(P personId) {
        return repository.getMedicalInfo((long) personId);
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(MedicalInfo medicalInfo) {
        return  p -> {
            p.setMedicalInfo(medicalInfo);

            return p;
        };
    }
}
