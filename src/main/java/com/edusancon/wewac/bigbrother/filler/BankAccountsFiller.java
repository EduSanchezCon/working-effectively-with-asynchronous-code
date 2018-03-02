package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.BankAccount;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.supplier.FutureSupplier;
import com.edusancon.wewac.bigbrother.supplier.RandomListSupplier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class BankAccountsFiller extends AbstractFiller<Person, List<BankAccount>>{

    @Override
    protected CompletableFuture<List<BankAccount>> obtainInfo(Person person) {
        return new FutureSupplier<>(
                    new RandomListSupplier<BankAccount>(BankAccount.class))
                .get();
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(List<BankAccount> bankAccounts) {
        return  p -> {
            p.setBankAccounts(bankAccounts);

            return p;
        };
    }
}
