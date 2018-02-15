package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.AcademicInfo;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.supplier.RandomObjectSupplier;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class AcademicInfoFiller extends AbstractFiller<Person, AcademicInfo>{

    @Override
    protected CompletableFuture<AcademicInfo> obtainInfo(Person person) {
        return new RandomObjectSupplier<AcademicInfo>(AcademicInfo.class).get();
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(AcademicInfo academicInfo) {
        return  p -> {
            p.setAcademicInfo(academicInfo);

            return p;
        };
    }
}
