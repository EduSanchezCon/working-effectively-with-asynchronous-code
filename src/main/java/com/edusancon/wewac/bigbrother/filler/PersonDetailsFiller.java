package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.PersonDetailRepository;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class PersonDetailsFiller extends AbstractFiller<Person, Person>{

    private final long personId;
    private final PersonDetailRepository repository;

    public PersonDetailsFiller(long personId, PersonDetailRepository repository) {
        this.personId = personId;
        this.repository = repository;
    }

    @Override
    protected CompletableFuture<Person> obtainInfo() {

        return repository.getPersonDetail(personId);
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(Person detail) {
        return  p -> {
            p.setName(detail.getName());
            p.setPassport(detail.getPassport());
            p.setBirthday(detail.getBirthday());
            p.setMarriedTo(detail.getMarriedTo());

            return p;
        };
    }
}
