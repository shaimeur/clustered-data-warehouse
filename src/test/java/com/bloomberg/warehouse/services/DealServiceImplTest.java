package com.bloomberg.warehouse.services;

import com.bloomberg.warehouse.dtos.DealRequestDTO;
import com.bloomberg.warehouse.dtos.DealResponseDTO;
import com.bloomberg.warehouse.entites.Deal;
import com.bloomberg.warehouse.exceptions.UniqueIdException;
import com.bloomberg.warehouse.repositories.DealRepository;
import com.bloomberg.warehouse.services.impls.DealServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DealServiceImplTest {

    @Mock
    private DealRepository dealRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DealServiceImpl dealService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldSaveAndReturnDeal_whenDealIsNew() {
        // Arrange
        DealRequestDTO requestDTO = DealRequestDTO.builder()
                .id(1L)
                .fromCurrency("USD")
                .toCurrency("EUR")
                .dealTimestamp(Instant.now())
                .amount(new BigDecimal("100.50"))
                .build();

        Deal dealEntity = Deal.builder()
                .id(requestDTO.getId())
                .fromCurrency(requestDTO.getFromCurrency())
                .toCurrency(requestDTO.getToCurrency())
                .dealTimestamp(requestDTO.getDealTimestamp())
                .amount(requestDTO.getAmount())
                .build();

        Deal savedDeal = Deal.builder()
                .id(requestDTO.getId())
                .fromCurrency(requestDTO.getFromCurrency())
                .toCurrency(requestDTO.getToCurrency())
                .dealTimestamp(requestDTO.getDealTimestamp())
                .amount(requestDTO.getAmount())
                .build();

        DealResponseDTO responseDTO = DealResponseDTO.builder()
                .id(requestDTO.getId())
                .fromCurrency(requestDTO.getFromCurrency())
                .toCurrency(requestDTO.getToCurrency())
                .dealTimestamp(requestDTO.getDealTimestamp())
                .amount(requestDTO.getAmount())
                .build();

        when(dealRepository.existsById(requestDTO.getId())).thenReturn(false);
        when(modelMapper.map(requestDTO, Deal.class)).thenReturn(dealEntity);
        when(dealRepository.save(dealEntity)).thenReturn(savedDeal);
        when(modelMapper.map(savedDeal, DealResponseDTO.class)).thenReturn(responseDTO);

        // Act
        DealResponseDTO result = dealService.create(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(requestDTO.getId(), result.getId());
        assertEquals(requestDTO.getFromCurrency(), result.getFromCurrency());
        assertEquals(requestDTO.getToCurrency(), result.getToCurrency());
        assertEquals(requestDTO.getAmount(), result.getAmount());
        assertEquals(requestDTO.getDealTimestamp(), result.getDealTimestamp());

        verify(dealRepository).existsById(requestDTO.getId());
        verify(dealRepository).save(dealEntity);
        verify(modelMapper).map(requestDTO, Deal.class);
        verify(modelMapper).map(savedDeal, DealResponseDTO.class);
    }

    @Test
    void create_shouldThrowUniqueIdException_whenDealAlreadyExists() {
        // Arrange
        DealRequestDTO requestDTO = DealRequestDTO.builder()
                .id(1L)
                .fromCurrency("USD")
                .toCurrency("EUR")
                .dealTimestamp(Instant.now())
                .amount(new BigDecimal("100.50"))
                .build();

        when(dealRepository.existsById(requestDTO.getId())).thenReturn(true);

        // Act & Assert
        UniqueIdException exception = assertThrows(UniqueIdException.class,
                () -> dealService.create(requestDTO));

        assertEquals("Deal with 1 already exists", exception.getMessage());
        verify(dealRepository).existsById(requestDTO.getId());
        verify(dealRepository, never()).save(any());
        verifyNoInteractions(modelMapper);
    }
}
