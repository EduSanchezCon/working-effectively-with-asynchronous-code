package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.BankAccount;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.BankAccountRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class BankAccountsFiller extends AbstractFiller<Person, List<BankAccount>>{

    private final BankAccountRepository repository;

    public BankAccountsFiller(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    protected <P> CompletableFuture<List<BankAccount>> obtainInfo(P personId) {
        return repository.getBankAccounts((long) personId);
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(List<BankAccount> bankAccounts) {
        return  p -> {
            p.setBankAccounts(bankAccounts);

            return p;
        };
    }
}
