package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotService {
    @Autowired
    private  ParkingLotRepository parkingLotRepository;

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);

    }

    public List<ParkingLot> checkAllParkingLots() {
        List<ParkingLot> parkingLots = new ArrayList<>();
        return parkingLotRepository.findAll();

    }
    public ParkingLot checkSpecificParkingLot(String id) {
       return parkingLotRepository.findById(id).get();

    }
    public void deleteAParkingLot(String id) {
        parkingLotRepository.deleteById(id);
    }
    public ResponseEntity putAParkingLot(String id) {
        return null;
    }
}
