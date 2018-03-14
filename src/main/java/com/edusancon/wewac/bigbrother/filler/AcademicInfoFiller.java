package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.AcademicInfo;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.AcademicInfoRepository;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class AcademicInfoFiller extends AbstractFiller<Person, AcademicInfo>{

    private final long personId;
    private final AcademicInfoRepository repository;

    public AcademicInfoFiller(long personId, AcademicInfoRepository repository) {
        this.personId = personId;
        this.repository = repository;
    }


    @Override
    protected CompletableFuture<AcademicInfo> obtainInfo() {
        return repository.getAcademicInfo(personId);
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(AcademicInfo academicInfo) {
        return  p -> {
            p.setAcademicInfo(academicInfo);

            return p;
        };
    }
}
