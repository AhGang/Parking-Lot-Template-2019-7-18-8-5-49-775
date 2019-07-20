package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingOrderRepository extends JpaRepository<ParkingOrder,String> {
    @Query(value = "select * from PARKING_ORDER where PARKING_LOT_NAME = ?2 and STATUS = ?1 ",nativeQuery = true)
    List<ParkingOrder > findByStatusAndParkingLotName(boolean status , String parkingLotName);

    ParkingOrder findByCarNumber(String carNumber);
}
