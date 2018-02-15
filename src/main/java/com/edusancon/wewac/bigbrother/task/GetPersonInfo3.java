package com.edusancon.wewac.bigbrother.task;

import com.edusancon.wewac.bigbrother.filler.*;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.util.FunctionCollector;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class GetPersonInfo3 implements Function<Person, CompletableFuture<Person>>{


    @Override
    public CompletableFuture<Person> apply(final Person originalPerson) {

        final List<CompletableFuture<UnaryOperator<Person>>> futureList = Arrays.asList(
                new PersonDetailsFiller().get(originalPerson),
                new InsurancesFiller().get(originalPerson),
                new MedicalInfoFiller().get(originalPerson),
                new BankAccountsFiller().get(originalPerson),
                new AcademicInfoFiller().get(originalPerson));

        final CompletableFuture<UnaryOperator<Person>>[] futureArray =
                futureList.toArray(new CompletableFuture[futureList.size()]);

        CompletableFuture<List<UnaryOperator<Person>>> futureAll = CompletableFuture.allOf(futureArray)
                .thenApply(
                        v -> futureList.stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList()));

        return futureAll.thenApply(
                list -> list.stream().collect(new FunctionCollector<>(originalPerson)));

    }
}
