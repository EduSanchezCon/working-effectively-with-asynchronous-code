package com.edusancon.wewac.bigbrother.task;

import com.edusancon.wewac.bigbrother.model.*;
import com.edusancon.wewac.bigbrother.supplier.FutureSupplier;
import com.edusancon.wewac.bigbrother.supplier.GetPersonDetails;
import com.edusancon.wewac.bigbrother.supplier.RandomListSupplier;
import com.edusancon.wewac.bigbrother.supplier.RandomObjectSupplier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class GetPersonInfo implements Function<Person, CompletableFuture<Person>>{


    @Override
    public CompletableFuture<Person> apply(final Person originalPerson) {

        CompletableFuture<Person> personFuture =
                new GetPersonDetails().apply(originalPerson.getId());
        CompletableFuture<List<Insurance>> insurancesFuture =
                new FutureSupplier<>(new RandomListSupplier<>(Insurance.class)).get();
        CompletableFuture<MedicalInfo> medicalInfoFuture =
                new FutureSupplier<>(new RandomObjectSupplier<>(MedicalInfo.class)).get();
        CompletableFuture<List<BankAccount>> bankAccountsFuture =
                new FutureSupplier<>(new RandomListSupplier<>(BankAccount.class)).get();
        CompletableFuture<AcademicInfo> academicInfoFuture =
                new FutureSupplier<>(new RandomObjectSupplier<>(AcademicInfo.class)).get();

        final CompletableFuture<Void> allOf = CompletableFuture.allOf(
                personFuture, insurancesFuture, medicalInfoFuture, bankAccountsFuture, academicInfoFuture);

        return allOf.thenApply(v -> {

                Person personDetails = personFuture.join();
                originalPerson.setName(personDetails.getName());
                originalPerson.setPassport(personDetails.getPassport());
                originalPerson.setBirthday(personDetails.getBirthday());

                originalPerson.setInsurances(insurancesFuture.join());
                originalPerson.setBankAccounts(bankAccountsFuture.join());
                originalPerson.setAcademicInfo(academicInfoFuture.join());
                originalPerson.setMedicalInfo(medicalInfoFuture.join());

                return  originalPerson;
            });
    }
}
