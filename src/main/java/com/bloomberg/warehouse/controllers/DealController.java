package com.bloomberg.warehouse.controllers;

import com.bloomberg.warehouse.dtos.DealRequestDTO;
import com.bloomberg.warehouse.dtos.DealResponseDTO;
import com.bloomberg.warehouse.services.DealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/deals")
@Validated
@Slf4j
public class DealController {

    @Autowired
    private DealService dealService;


    @PostMapping
    public ResponseEntity<DealResponseDTO> createDeal(@Valid @RequestBody DealRequestDTO dealDto) {
        DealResponseDTO createdDeal = dealService.create(dealDto);
        log.info("Created deal: {}", createdDeal);
        return new ResponseEntity<>(createdDeal, HttpStatus.CREATED);
    }


}
