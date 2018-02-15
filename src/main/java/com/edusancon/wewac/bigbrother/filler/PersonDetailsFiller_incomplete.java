package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.supplier.GetPersonDetails;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class PersonDetailsFiller_incomplete {


    public CompletableFuture<UnaryOperator<Person>> get(final Person person){

        return new GetPersonDetails().apply(person.getId())
                .thenApply(det ->
                        (UnaryOperator<Person>) p -> {
                            p.setName(det.getName());
                            p.setPassport(det.getPassport());
                            p.setBirthday(det.getBirthday());

                            return p;
                        });
    }
}
