package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingOrderService {
    @Autowired
    ParkingOrderRepository parkingOrderRepository;
    public void addParkingOrderService(ParkingOrder parkingOrder) {
        parkingOrderRepository.save(parkingOrder);
    }
}
