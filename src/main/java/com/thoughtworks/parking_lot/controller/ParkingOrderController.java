package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking-orders")
public class ParkingOrderController {
    @Autowired
    ParkingOrderService parkingOrderService;

    @PostMapping
    public ResponseEntity createAParkingOrder(@RequestBody ParkingOrder parkingOrder){
        parkingOrderService.addParkingOrderService(parkingOrder);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
