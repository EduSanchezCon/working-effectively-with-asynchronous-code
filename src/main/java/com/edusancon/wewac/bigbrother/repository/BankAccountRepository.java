package com.edusancon.wewac.bigbrother.repository;

import com.edusancon.wewac.bigbrother.model.BankAccount;
import com.edusancon.wewac.bigbrother.supplier.FutureSupplier;
import com.edusancon.wewac.bigbrother.supplier.RandomListSupplier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BankAccountRepository {

    public CompletableFuture<List<BankAccount>> getBankAccounts(long personID){

        return new FutureSupplier<>(
                    new RandomListSupplier<>(BankAccount.class))
                .get();
    }
}
