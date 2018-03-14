package com.edusancon.wewac.bigbrother.repository;

import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.supplier.FutureSupplier;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

public class PersonDetailRepository {

    public CompletableFuture<Person> getPersonDetail(final Long id) {

        return new FutureSupplier<>(
                () -> {
                    Person person = new Person(id);
                    person.setName("Persona Muy Fake");
                    person.setPassport("A12345678");
                    person.setBirthday(LocalDate.now());
                    person.setMarriedTo(123L);

                    return  person;
                }).get();
    }
}
