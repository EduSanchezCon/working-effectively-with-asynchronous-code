package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.PersonDetailRepository;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class PartnerDetailsFiller extends AbstractNestedFiller<Person, Person, Person>{

    private final PersonDetailRepository repository;

    public PartnerDetailsFiller(Filler<Person, Person> previousFiller, PersonDetailRepository repository) {
        super(previousFiller);
        this.repository = repository;
    }

    @Override
    protected CompletableFuture<Person> obtainInfo(Person previousResponse) {
        return repository.getPersonDetail(previousResponse.getMarriedTo());
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(Person detail, Person previousResponse) {
        return  p -> {
            p.setPartner(detail);
            return p;
        };
    }

    @Override
    protected boolean conditionToExecute(Person previousResult) {

        return previousResult.getMarriedTo() != null;
    }
}
