package com.edusancon.wewac.bigbrother.repository;

import com.edusancon.wewac.bigbrother.model.MedicalInfo;
import com.edusancon.wewac.bigbrother.supplier.FutureSupplier;
import com.edusancon.wewac.bigbrother.supplier.RandomObjectSupplier;

import java.util.concurrent.CompletableFuture;

public class MedicalInfoRepository {

    public CompletableFuture<MedicalInfo> getMedicalInfo(long personID){

        return new FutureSupplier<>(
                    new RandomObjectSupplier<>(MedicalInfo.class))
                .get();
    }
}
