package com.first.meridian.service;

import com.first.meridian.repository.IpAddressRepository;
import com.first.meridian.repository.IpPoolRepository;
import com.first.meridian.util.IpConfigTestUtils;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class IpPoolReadServiceTest {

    @Mock
    private IpPoolRepository poolRepository;

    @Mock
    private IpAddressRepository addressRepository;

    @InjectMocks
    private IpPoolReadService readService;

    @Test
    public void should_return_null_when_pool_id_is_not_exist() {
        // Given
        Mockito.when(poolRepository.findById(1L)).thenReturn(Optional.empty());
        // When
        val poolList = readService.findByPoolId(1L);
        assertThat(poolList).isNull();
    }

    @Test
    public void should_return_pool_list_for_given_id() {
        // Given
        Mockito.when(poolRepository.findById(1L)).thenReturn(Optional.of(IpConfigTestUtils.ipPool()));
        Mockito.when(addressRepository.findByIdIpPoolId(1L)).thenReturn(Arrays.asList(IpConfigTestUtils.ipAddress()));
        // When
        val response = readService.findByPoolId(1L);
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getDescription()).isEqualTo("test");
        assertThat(response.getLowerBound()).isEqualTo(0L);
        assertThat(response.getUpperBound()).isEqualTo(250L);
        assertThat(response.getTotalCapacity()).isEqualTo(250L);
    }
}


