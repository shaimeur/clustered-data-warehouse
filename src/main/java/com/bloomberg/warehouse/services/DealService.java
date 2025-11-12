package com.bloomberg.warehouse.services;

import com.bloomberg.warehouse.dtos.DealRequestDTO;
import com.bloomberg.warehouse.dtos.DealResponseDTO;


public interface DealService {
    DealResponseDTO create(DealRequestDTO dealDTO);
}
