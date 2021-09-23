package com.first.meridian.service;

import com.first.meridian.domain.IpPool;
import com.first.meridian.repository.IpPoolRepository;
import com.first.meridian.util.IpConfigTestUtils;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class IpServiceTest {

    @Mock
    private IpPoolRepository repository;

    @InjectMocks
    private IpWriteService service;

    private ArgumentCaptor<IpPool> captor = ArgumentCaptor.forClass(IpPool.class);

    @Test
    public void should_return_isInvalidPoolIdAndRequestedPools_as_false_when_pool_id_is_null() {
        // Given
        // When
        val isValid = service.isInvalidPoolIdAndRequestedPools(null, 10L);
        assertThat(isValid).isFalse();
    }

    @Test
    public void should_return_isInvalidPoolIdAndRequestedPools_as_false_when_request_addresses_are_less() {
        // Given
        val poolId = 1L;
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(IpConfigTestUtils.ipPool()));
        // When
        val isValid = service.isInvalidPoolIdAndRequestedPools(poolId, 5L);
        assertThat(isValid).isFalse();
    }

    @Test
    public void should_return_isInvalidPoolIdAndRequestedPools_as_true_when_request_addresses_are_more() {
        // Given
        val poolId = 1L;
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(IpConfigTestUtils.ipPool()));
        // When
        val isValid = service.isInvalidPoolIdAndRequestedPools(poolId, 30L);
        assertThat(isValid).isTrue();
    }

    @Test
    public void should_create_requested_number_of_ip_addresses_for_for_new_pool_id() {
        // Given
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(repository.save(captor.capture())).thenReturn(IpConfigTestUtils.resultIpPool());
        // When
        val ipAddressResponse = service.createIpAddress(IpConfigTestUtils.command());
        assertThat(ipAddressResponse.getIpAddresses().size()).isEqualTo(1);
        assertThat(captor.getValue().getUsedCapacity()).isEqualTo(100L);
        assertThat(captor.getValue().getIpAddresses().size()).isEqualTo(10);
        assertThat(ipAddressResponse.getResponseMessage()).isEqualTo("Pool addresses saved successfully");
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void should_create_requested_number_of_ip_addresses_for_existing_pool_id() {
        // Given
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(IpConfigTestUtils.ipPool()));
        Mockito.when(repository.save(captor.capture())).thenReturn(IpConfigTestUtils.resultIpPool());
        // When
        val ipAddressResponse = service.createIpAddress(IpConfigTestUtils.command());
        assertThat(ipAddressResponse.getIpAddresses().size()).isEqualTo(1);
        assertThat(captor.getValue().getUsedCapacity()).isEqualTo(150L);
        assertThat(captor.getValue().getIpAddresses().size()).isEqualTo(5);
        assertThat(ipAddressResponse.getResponseMessage()).isEqualTo("Pool addresses saved successfully");
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }
}


