package com.edusancon.wewac.bigbrother.task;

import com.edusancon.wewac.bigbrother.model.*;
import com.edusancon.wewac.bigbrother.supplier.FutureSupplier;
import com.edusancon.wewac.bigbrother.supplier.GetPersonDetails;
import com.edusancon.wewac.bigbrother.supplier.RandomListSupplier;
import com.edusancon.wewac.bigbrother.supplier.RandomObjectSupplier;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GetPersonInfo2 implements Function<Person, CompletableFuture<Person>>{


    @Override
    public CompletableFuture<Person> apply(final Person originalPerson) {

        final List<CompletableFuture<?>> futureList = Arrays.asList(
                new GetPersonDetails().apply(originalPerson.getId()),
                new FutureSupplier<>(new RandomListSupplier<>(Insurance.class)).get(),
                new FutureSupplier<>(new RandomObjectSupplier<>(MedicalInfo.class)).get(),
                new FutureSupplier<>(new RandomListSupplier<>(BankAccount.class)).get(),
                new FutureSupplier<>(new RandomObjectSupplier<>(AcademicInfo.class)).get());

        final CompletableFuture<?>[] futureArray = futureList.toArray(new CompletableFuture[futureList.size()]);

        CompletableFuture<List<?>> futureAll = CompletableFuture.allOf(futureArray)
                .thenApply(
                        v -> futureList.stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList()));

        return futureAll.thenApply(
                list -> {
                    Person personDetails = (Person)list.get(0);
                    originalPerson.setName(personDetails.getName());
                    originalPerson.setPassport(personDetails.getPassport());
                    originalPerson.setBirthday(personDetails.getBirthday());

                    originalPerson.setInsurances((List<Insurance>) list.get(1));
                    originalPerson.setBankAccounts((List<BankAccount>) list.get(3));
                    originalPerson.setAcademicInfo((AcademicInfo) list.get(4));
                    originalPerson.setMedicalInfo((MedicalInfo) list.get(2));

                    return  originalPerson;
                }
            );


    }
}
