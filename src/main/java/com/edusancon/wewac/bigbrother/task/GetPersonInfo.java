package com.edusancon.wewac.bigbrother.task;

import com.edusancon.wewac.bigbrother.model.*;
import com.edusancon.wewac.bigbrother.repository.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class GetPersonInfo implements Function<Person, CompletableFuture<Person>>{


    @Override
    public CompletableFuture<Person> apply(final Person originalPerson) {

        final long personId = originalPerson.getId();

        CompletableFuture<Person> personFuture =
                new PersonDetailRepository().getPersonDetail(personId);
        CompletableFuture<List<Insurance>> insurancesFuture =
                new InsuranceRepository().getInsurances(personId);
        CompletableFuture<MedicalInfo> medicalInfoFuture =
                new MedicalInfoRepository().getMedicalInfo(personId);
        CompletableFuture<List<BankAccount>> bankAccountsFuture =
                new BankAccountRepository().getBankAccounts(personId);
        CompletableFuture<AcademicInfo> academicInfoFuture =
                new AcademicInfoRepository().getAcademicInfo(personId);

        final CompletableFuture<Void> allOf = CompletableFuture.allOf(
                personFuture, insurancesFuture, medicalInfoFuture, bankAccountsFuture, academicInfoFuture);

        return allOf.thenApply(v -> {

                Person personDetails = personFuture.join();
                originalPerson.setName(personDetails.getName());
                originalPerson.setPassport(personDetails.getPassport());
                originalPerson.setBirthday(personDetails.getBirthday());
                originalPerson.setMarriedTo(personDetails.getMarriedTo());

                originalPerson.setInsurances(insurancesFuture.join());
                originalPerson.setBankAccounts(bankAccountsFuture.join());
                originalPerson.setAcademicInfo(academicInfoFuture.join());
                originalPerson.setMedicalInfo(medicalInfoFuture.join());

                return  originalPerson;
            });
    }
}
