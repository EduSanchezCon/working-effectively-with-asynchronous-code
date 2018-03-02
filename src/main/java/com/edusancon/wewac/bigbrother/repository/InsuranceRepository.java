package com.edusancon.wewac.bigbrother.repository;

import com.edusancon.wewac.bigbrother.model.Insurance;
import com.edusancon.wewac.bigbrother.supplier.FutureSupplier;
import com.edusancon.wewac.bigbrother.supplier.RandomListSupplier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class InsuranceRepository {

    public CompletableFuture<List<Insurance>> getInsurances(long personID){

        return new FutureSupplier<>(
                    new RandomListSupplier<>(Insurance.class))
                .get();
    }
}
