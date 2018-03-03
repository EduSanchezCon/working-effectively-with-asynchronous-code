package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.Insurance;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.InsuranceRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class InsurancesFiller extends AbstractFiller<Person, List<Insurance>>{

    private final InsuranceRepository repository;

    public InsurancesFiller(InsuranceRepository repository) {
        this.repository = repository;
    }

    @Override
    protected <P> CompletableFuture<List<Insurance>> obtainInfo(P personId) {
        return repository.getInsurances((long) personId);
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(List<Insurance> insurances) {
        return  p -> {
            p.setInsurances(insurances);

            return p;
        };
    }
}
