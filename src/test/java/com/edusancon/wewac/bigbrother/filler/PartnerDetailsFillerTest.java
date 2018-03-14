package com.edusancon.wewac.bigbrother.filler;

import com.edusancon.wewac.bigbrother.model.Person;
import com.edusancon.wewac.bigbrother.repository.PersonDetailRepository;
import com.edusancon.wewac.bigbrother.supplier.RandomObjectSupplier;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PartnerDetailsFillerTest {

    private PersonDetailRepository repositoryMock = Mockito.mock(PersonDetailRepository.class);

    private PartnerDetailsFiller sut = new PartnerDetailsFiller(Mockito.mock(PersonDetailsFiller.class), repositoryMock);

    @Test
    void shouldObtainInfoFromBackend() {

        Person originalPerson = new RandomObjectSupplier<>(Person.class).get();
        Person expectedPartner = new RandomObjectSupplier<>(Person.class).get();

        Mockito.when(repositoryMock.getPersonDetail(originalPerson.getMarriedTo()))
                .thenReturn(CompletableFuture.completedFuture(expectedPartner));

        final CompletableFuture<Person> actualPartnerFuture = sut.obtainInfo(originalPerson);

        assertThat(actualPartnerFuture.join(), is(expectedPartner));
    }

    @Test
    void functionShouldAddPartnerToPerson() {
        Person originalPerson = new RandomObjectSupplier<>(Person.class).get();
        Person expectedPartner = new RandomObjectSupplier<>(Person.class).get();

        final UnaryOperator<Person> fillerFunction = sut.getFillerFunction(expectedPartner, originalPerson);
        fillerFunction.apply( originalPerson );

        assertThat(originalPerson.getPartner(), is(expectedPartner));
    }

    @Test
    void ifMarriedToIsNullThenConditionShouldBeFalse() {

        assertFalse(sut.conditionToExecute(new Person(1L)));
    }

    @Test
    void ifMarriedToIsNotNullThenConditionShouldBeTrue() {

        assertTrue(sut.conditionToExecute(new RandomObjectSupplier<>(Person.class).get()));
    }
}