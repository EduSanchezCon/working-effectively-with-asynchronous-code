package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.PersonDetailRepository;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class PersonDetailsFiller extends AbstractFiller<Person, Person>{

    @Override
    protected CompletableFuture<Person> obtainInfo(Person person) {
        return new PersonDetailRepository().getPersonDetail(person.getId());
    }

    @Override
    protected UnaryOperator<Person> getFillerFunction(Person detail) {
        return  p -> {
            p.setName(detail.getName());
            p.setPassport(detail.getPassport());
            p.setBirthday(detail.getBirthday());

            return p;
        };
    }
}
