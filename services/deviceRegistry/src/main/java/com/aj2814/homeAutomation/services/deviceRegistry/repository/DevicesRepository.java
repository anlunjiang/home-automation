package com.aj2814.homeAutomation.services.deviceRegistry.repository;

import com.aj2814.homeAutomation.services.deviceRegistry.entity.Registry;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DevicesRepository extends PagingAndSortingRepository<Registry, Integer> {
    Registry findByIdentifier(int identifier);
}
