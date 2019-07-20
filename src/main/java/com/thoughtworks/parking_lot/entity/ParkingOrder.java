package com.thoughtworks.parking_lot.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ParkingOrder {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String name;
    private String carNumber;
    private Date creationTime;
    private Date endTime;
    private boolean status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;


    public ParkingOrder() {
    }

    public ParkingOrder(String name, String carNumber, boolean status, ParkingLot parkingLot) {
        this.name = name;
        this.carNumber = carNumber;
        this.status = status;
        this.parkingLot = parkingLot;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public boolean isStatus() {
        return status;
    }
}
