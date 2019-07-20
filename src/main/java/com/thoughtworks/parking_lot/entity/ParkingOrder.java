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
    private String parkingLotName;
    private String carNumber;
    private Date creationTime;
    private Date endTime;
    private boolean status;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;


    public ParkingOrder() {
    }

    public ParkingOrder(String parkingLotName, String carNumber, boolean status) {
        this.parkingLotName = parkingLotName;
        this.carNumber = carNumber;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getParkingLotName() {
        return parkingLotName;
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
    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
