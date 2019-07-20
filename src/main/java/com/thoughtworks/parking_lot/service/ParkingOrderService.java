package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParkingOrderService {
    @Autowired
    ParkingOrderRepository parkingOrderRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    public boolean addParkingOrderService(ParkingOrder parkingOrder) {
        ParkingLot parkingLot = parkingLotRepository.findByName(parkingOrder.getParkingLotName());
        List<ParkingOrder> parkingOrders = parkingOrderRepository.findByStatusAndParkingLotName(true,parkingOrder.getParkingLotName());
        if(parkingOrders.size()< parkingLot.getCapacity()){
            parkingOrderRepository.save(parkingOrder);
            return true;
        }else{
            return false;
        }
    }

    public void updateParkingOrderService(String carNumber) {
        ParkingOrder parkingOrder = parkingOrderRepository.findByCarNumber(carNumber);
        parkingOrder.setEndTime(new Date());
        parkingOrder.setStatus(false);
        parkingOrderRepository.save(parkingOrder);
    }
}
