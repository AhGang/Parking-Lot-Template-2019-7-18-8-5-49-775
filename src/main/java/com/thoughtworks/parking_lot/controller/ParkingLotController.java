package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;
    @PostMapping
    public ResponseEntity createAParkingLot(@RequestBody ParkingLot parkingLot){
        parkingLotService.addParkingLot(parkingLot);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteAParkingLot(@PathVariable String id){
        parkingLotService.deleteAParkingLot(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping
    public ResponseEntity checkAllParkingLots(){
        List<ParkingLot> parkingLots = parkingLotService.checkAllParkingLots();
        return ResponseEntity.status(HttpStatus.OK).body(parkingLots);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity checkAParkingLot(@PathVariable String id){
        ParkingLot parkingLot = parkingLotService.checkSpecificParkingLot(id);
        return ResponseEntity.status(HttpStatus.OK).body(parkingLot);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity updateAParkingLot(@RequestBody ParkingLot parkingLot){
        ParkingLot parkingLotA = parkingLotService.updateAParkingLot(parkingLot);
        return ResponseEntity.status(HttpStatus.OK).body(parkingLotA);
    }

}
