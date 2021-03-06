package com.edusancon.wewac.bigbrother.task;

import com.edusancon.wewac.bigbrother.filler.*;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.*;
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

        final long personId = originalPerson.getId();

        final List<CompletableFuture<UnaryOperator<Person>>> futureList = Arrays.asList(
                new PersonDetailsFiller(personId, new PersonDetailRepository()).get(),
                new InsurancesFiller(personId, new InsuranceRepository()).get(),
                new MedicalInfoFiller(personId, new MedicalInfoRepository()).get(),
                new BankAccountsFiller(personId, new BankAccountRepository()).get(),
                new AcademicInfoFiller(personId, new AcademicInfoRepository()).get());

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
