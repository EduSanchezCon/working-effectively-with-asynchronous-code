package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.Insurance;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.supplier.FutureSupplier;
import com.edusancon.wewac.bigbrother.supplier.RandomListSupplier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class InsurancesFiller extends AbstractFiller<Person, List<Insurance>>{

    @Override
    protected CompletableFuture<List<Insurance>> obtainInfo(Person person) {
        return new FutureSupplier<>(
                    new RandomListSupplier<Insurance>(Insurance.class))
                .get();
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(List<Insurance> insurances) {
        return  p -> {
            p.setInsurances(insurances);

            return p;
        };
    }
}
