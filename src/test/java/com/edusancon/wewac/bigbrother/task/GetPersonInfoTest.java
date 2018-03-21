package com.edusancon.wewac.bigbrother.task;

import com.edusancon.wewac.bigbrother.model.Person;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class GetPersonInfoTest {

    @Test
    public void getPerson() throws ExecutionException, InterruptedException {

        final Person originalPerson = new Person(10L);

        CompletableFuture<Person> personFuture = new GetPersonInfo4().apply(originalPerson);

        Person person = personFuture.get();

        System.out.println(person);
    }
}