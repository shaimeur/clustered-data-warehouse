package com.bloomberg.warehouse.repositories;

import com.bloomberg.warehouse.entites.Deal;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DealRepository extends JpaRepository<Deal, Long> {
}
