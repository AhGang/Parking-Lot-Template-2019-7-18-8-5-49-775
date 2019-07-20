package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-orders")
public class ParkingOrderController {
    @Autowired
    ParkingOrderService parkingOrderService;

    @PostMapping
    public ResponseEntity createAParkingOrder(@RequestBody ParkingOrder parkingOrder){
        boolean isCreated = parkingOrderService.addParkingOrderService(parkingOrder);
        if(isCreated){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parking Lot have no position");
        }
    }

    @PutMapping(path = "/{carNumber}")
    public  ResponseEntity updateAParkingOrder(@PathVariable String carNumber){
        parkingOrderService.updateParkingOrderService(carNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
