package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public ParkingLot updateAParkingLot(String id) {
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
        List<ParkingLot> resultParkingLotList = parkingLots.stream().filter(e ->(e.getId() == id )).collect(Collectors.toList());
        ParkingLot parkingLot = resultParkingLotList.get(0);
        parkingLot.setCapacity(500);
        parkingLotRepository.save(parkingLot);

        return parkingLot;
    }
}
