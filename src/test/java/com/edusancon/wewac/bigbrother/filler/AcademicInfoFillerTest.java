package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.AcademicInfo;
import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.AcademicInfoRepository;
import com.edusancon.wewac.bigbrother.supplier.RandomObjectSupplier;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class AcademicInfoFillerTest {

    private AcademicInfoRepository repositoryMock = Mockito.mock(AcademicInfoRepository.class);
    private long personId = 1234L;

    private AcademicInfoFiller sut = new AcademicInfoFiller(personId, repositoryMock);

    @Test
    void shouldObtainInfoFromBackend() {

        AcademicInfo expectedInfo = new RandomObjectSupplier<>(AcademicInfo.class).get();
        Mockito.when(repositoryMock.getAcademicInfo(personId)).thenReturn(CompletableFuture.completedFuture(expectedInfo));

        final CompletableFuture<AcademicInfo> actualInfoFuture = sut.obtainInfo();

        assertThat(actualInfoFuture.join(), is(expectedInfo));
    }

    @Test
    void functionShouldAddAcademicInfoToPerson() {

        Person originalPerson = new RandomObjectSupplier<>(Person.class).get();
        AcademicInfo expectedInfo = new RandomObjectSupplier<>(AcademicInfo.class).get();

        final UnaryOperator<Person> fillerFunction = sut.getFillerFunction(expectedInfo);
        fillerFunction.apply( originalPerson );

        assertThat(originalPerson.getAcademicInfo(), is(expectedInfo));
    }
}