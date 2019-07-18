package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {
    @Autowired
    private  ParkingLotRepository parkingLotRepository;

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);

    }
}
