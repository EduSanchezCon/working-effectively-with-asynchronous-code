package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.MedicalInfo;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.MedicalInfoRepository;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class MedicalInfoFiller extends AbstractFiller<Person, MedicalInfo>{


    private final long personId;
    private final MedicalInfoRepository repository;

    public MedicalInfoFiller(long personId, MedicalInfoRepository repository) {
        this.personId = personId;
        this.repository = repository;
    }

    @Override
    protected CompletableFuture<MedicalInfo> obtainInfo() {
        return repository.getMedicalInfo(personId);
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(MedicalInfo medicalInfo) {
        return  p -> {
            p.setMedicalInfo(medicalInfo);

            return p;
        };
    }
}
