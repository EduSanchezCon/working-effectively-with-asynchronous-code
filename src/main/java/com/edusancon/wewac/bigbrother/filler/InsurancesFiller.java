package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.Insurance;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.InsuranceRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class InsurancesFiller extends AbstractFiller<Person, List<Insurance>>{

    private final long personId;
    private final InsuranceRepository repository;

    public InsurancesFiller(long personId, InsuranceRepository repository) {
        this.personId = personId;
        this.repository = repository;
    }

    @Override
    protected CompletableFuture<List<Insurance>> obtainInfo() {
        return repository.getInsurances(personId);
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(List<Insurance> insurances) {
        return  p -> {
            p.setInsurances(insurances);

            return p;
        };
    }
}
