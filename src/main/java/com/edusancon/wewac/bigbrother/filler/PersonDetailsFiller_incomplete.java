package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.PersonDetailRepository;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class PersonDetailsFiller_incomplete {


    public CompletableFuture<UnaryOperator<Person>> get(final Person person){

        return new PersonDetailRepository().getPersonDetail(person.getId())
                .thenApply(det ->
                        (UnaryOperator<Person>) p -> {
                            p.setName(det.getName());
                            p.setPassport(det.getPassport());
                            p.setBirthday(det.getBirthday());

                            return p;
                        });
    }
}
