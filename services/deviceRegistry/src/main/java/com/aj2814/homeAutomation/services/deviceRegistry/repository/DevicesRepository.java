package com.aj2814.homeAutomation.services.deviceRegistry.repository;

import com.aj2814.homeAutomation.services.deviceRegistry.entity.Device;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DevicesRepository extends PagingAndSortingRepository<Device, Integer> {
    Device findByIdentifier(int identifier);
}
