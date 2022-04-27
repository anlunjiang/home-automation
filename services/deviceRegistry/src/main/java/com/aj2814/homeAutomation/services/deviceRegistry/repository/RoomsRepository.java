package com.aj2814.homeAutomation.services.deviceRegistry.repository;

import com.aj2814.homeAutomation.services.deviceRegistry.entity.RoomEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoomsRepository extends PagingAndSortingRepository<RoomEntity, Integer> {
    RoomEntity findByIdentifier(int identifier);
}
