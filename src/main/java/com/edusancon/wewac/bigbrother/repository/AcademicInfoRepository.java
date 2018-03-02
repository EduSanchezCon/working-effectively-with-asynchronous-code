package com.edusancon.wewac.bigbrother.repository;

import com.edusancon.wewac.bigbrother.model.AcademicInfo;
import com.edusancon.wewac.bigbrother.supplier.FutureSupplier;
import com.edusancon.wewac.bigbrother.supplier.RandomObjectSupplier;

import java.util.concurrent.CompletableFuture;

public class AcademicInfoRepository {

    public CompletableFuture<AcademicInfo> getAcademicInfo(long personID){

        return new FutureSupplier<>(
                    new RandomObjectSupplier<>(AcademicInfo.class))
                .get();
    }
}
