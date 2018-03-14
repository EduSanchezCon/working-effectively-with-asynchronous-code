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

public class GetPersonInfo4 implements Function<Person, CompletableFuture<Person>>{


    @Override
    public CompletableFuture<Person> apply(final Person originalPerson) {

        final long personId = originalPerson.getId();

        final PersonDetailsFiller personDetailsFiller = new PersonDetailsFiller(personId, new PersonDetailRepository());

        final List<CompletableFuture<UnaryOperator<Person>>> futureList = Arrays.asList(
                personDetailsFiller.get(),
                new InsurancesFiller(personId, new InsuranceRepository()).get(),
                new MedicalInfoFiller(personId, new MedicalInfoRepository()).get(),
                new BankAccountsFiller(personId, new BankAccountRepository()).get(),
                new AcademicInfoFiller(personId, new AcademicInfoRepository()).get(),
                new PartnerDetailsFiller(personDetailsFiller, new PersonDetailRepository()).get());

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
