package com.edusancon.wewac.bigbrother.supplier;

import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.util.ThreadHelper;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class GetPersonDetails implements Function<Long, CompletableFuture<Person>> {

    @Override
    public CompletableFuture<Person> apply(final Long id) {

        return CompletableFuture.supplyAsync( () -> {
                Person person = new Person(id);
                person.setName("Persona Muy Fake");
                person.setPassport("A12345678");
                person.setBirthday(LocalDate.now());

                return  person;
            }, ThreadHelper.APP_EXECUTOR);
    }
}
