package com.edusancon.wewac.bigbrother.task;

import com.edusancon.wewac.bigbrother.model.*;
import com.edusancon.wewac.bigbrother.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GetPersonInfo2 implements Function<Person, CompletableFuture<Person>>{


    @Override
    public CompletableFuture<Person> apply(final Person originalPerson) {

        final long personId = originalPerson.getId();

        final List<CompletableFuture<?>> futureList = Arrays.asList(
                new PersonDetailRepository().getPersonDetail(personId),
                new InsuranceRepository().getInsurances(personId),
                new MedicalInfoRepository().getMedicalInfo(personId),
                new BankAccountRepository().getBankAccounts(personId),
                new AcademicInfoRepository().getAcademicInfo(personId));

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
