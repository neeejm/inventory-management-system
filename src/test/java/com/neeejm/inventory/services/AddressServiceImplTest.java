package com.neeejm.inventory.services;

import com.neeejm.inventory.models.Address;
import com.neeejm.inventory.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {
    private final String ADDRESS_NOT_FOUND_MSG = "Address not found";
    @Mock
    private AddressRepository addressRepository;
    @Captor
    private ArgumentCaptor<Address> addressArgumentCaptor;
    private AddressService underTest;
    private Address address;

    @BeforeEach
    void setup() {
        underTest = new AddressServiceImpl(addressRepository);

        address = Address.builder()
                .id(UUID.randomUUID())
                .country("country")
                .city("city")
                .street("street")
                .zipCode("12345")
                .build();
    }

    @Test
    @DisplayName("Should save Address")
    void shouldAdd() {
        // When
        underTest.add(address);

        // Then
        verify(addressRepository).save(addressArgumentCaptor.capture());
        Address capturedAddress = addressArgumentCaptor.getValue();
        assertThat(capturedAddress.getId()).isEqualTo(address.getId());
    }

    @Test
    @DisplayName("Should update Address")
    void shouldUpdate() {
        // Given
        address.setCity("City 2");

        given(addressRepository.existsById(address.getId())).willReturn(true);

        // When
        underTest.update(address);

        // Then
        then(addressRepository).should().save(addressArgumentCaptor.capture());
        Address capturedAddress = addressArgumentCaptor.getValue();
        assertThat(capturedAddress).isNotNull().isEqualTo(address);
    }

    @Test
    @DisplayName("Should not update Address")
    void shouldNotUpdate() {
        // Given
        given(addressRepository.existsById(address.getId())).willReturn(false);

        // When
        // Then
        assertThatThrownBy(() -> underTest.update(address))
                .hasMessageContaining(ADDRESS_NOT_FOUND_MSG)
                .isInstanceOf(EntityNotFoundException.class);
    }
    @Test
    @DisplayName("Should delete address with given id")
    void shouldDeleteById() {
        // Given
        UUID id = UUID.randomUUID();

        given(addressRepository.existsById(id)).willReturn(true);

        // When
        underTest.deleteById(id);

        // Then
        then(addressRepository).should().deleteById(id);
    }

    @Test
    @DisplayName("Should not delete address")
    void shouldNotDeleteById() {
        // Given
        UUID id = UUID.randomUUID();

        given(addressRepository.existsById(id)).willReturn(false);

        // When
        // Then
        assertThatThrownBy(() -> underTest.deleteById(id))
                .hasMessageContaining(ADDRESS_NOT_FOUND_MSG)
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("Should find address by id")
    void shouldFindAddressById() {
        // Given
        UUID id = UUID.randomUUID();

        given(addressRepository.findById(id)).willReturn(Optional.of(address));

        // When
        underTest.findById(id);

        // Then
        then(addressRepository).should().findById(id);
    }

    @Test
    @DisplayName("Should not find address")
    void shouldNotFindAddressById() {
        // Given
        UUID id = UUID.randomUUID();

        given(addressRepository.findById(id)).willReturn(Optional.empty());

        // When
        // Then
        assertThatThrownBy(() -> underTest.findById(id))
                .hasMessageContaining(ADDRESS_NOT_FOUND_MSG)
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("Should find all addresses")
    void shouldFindAll() {
        // When
        underTest.findAll();

        // Then
        then(addressRepository).should().findAll();
    }
}