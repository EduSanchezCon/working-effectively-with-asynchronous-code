package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.MedicalInfo;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.supplier.FutureSupplier;
import com.edusancon.wewac.bigbrother.supplier.RandomObjectSupplier;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class MedicalInfoFiller extends AbstractFiller<Person, MedicalInfo>{

    @Override
    protected CompletableFuture<MedicalInfo> obtainInfo(Person person) {
        return new FutureSupplier<>(
                    new RandomObjectSupplier<MedicalInfo>(MedicalInfo.class))
                .get();
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(MedicalInfo medicalInfo) {
        return  p -> {
            p.setMedicalInfo(medicalInfo);

            return p;
        };
    }
}
