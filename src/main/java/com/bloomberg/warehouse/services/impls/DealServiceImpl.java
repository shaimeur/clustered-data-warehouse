package com.bloomberg.warehouse.services.impls;

import com.bloomberg.warehouse.dtos.DealRequestDTO;
import com.bloomberg.warehouse.dtos.DealResponseDTO;
import com.bloomberg.warehouse.entites.Deal;
import com.bloomberg.warehouse.exceptions.UniqueIdException;
import com.bloomberg.warehouse.repositories.DealRepository;
import com.bloomberg.warehouse.services.DealService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DealServiceImpl  implements DealService {

    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private ModelMapper modelMapper;

    public DealResponseDTO create(DealRequestDTO deal) {
        if(dealRepository.existsById(deal.getId()))
            throw new UniqueIdException("Deal with " + deal.getId() + " already exists");
        Deal newDeal = modelMapper.map(deal, Deal.class);
        Deal savedDeal = dealRepository.save(newDeal);
        return modelMapper.map(savedDeal, DealResponseDTO.class);
    }



}
