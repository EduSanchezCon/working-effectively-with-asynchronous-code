package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.PersonDetailRepository;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class PersonDetailsFiller_incomplete {

    private final PersonDetailRepository repository;

    public PersonDetailsFiller_incomplete(PersonDetailRepository repository) {
        this.repository = repository;
    }

    public CompletableFuture<UnaryOperator<Person>> get(final Person person){

        return repository.getPersonDetail(person.getId())
                .thenApply(det ->
                        (UnaryOperator<Person>) p -> {
                            p.setName(det.getName());
                            p.setPassport(det.getPassport());
                            p.setBirthday(det.getBirthday());

                            return p;
                        });
    }
}
