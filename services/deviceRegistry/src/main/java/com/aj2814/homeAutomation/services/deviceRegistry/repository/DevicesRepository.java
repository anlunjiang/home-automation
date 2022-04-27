package com.aj2814.homeAutomation.services.deviceRegistry.repository;

import com.aj2814.homeAutomation.services.deviceRegistry.entity.DeviceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DevicesRepository extends PagingAndSortingRepository<DeviceEntity, Integer> {
    DeviceEntity findByIdentifier(int identifier);
}
