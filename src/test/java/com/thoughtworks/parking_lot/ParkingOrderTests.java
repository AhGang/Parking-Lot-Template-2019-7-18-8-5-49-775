package com.thoughtworks.parking_lot;



import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ParkingOrderTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired

    private ParkingOrderRepository parkingOrderRepository;
    @Before
    public void befroe_test(){
        parkingOrderRepository.deleteAll();
    }
    @Test
    public void should_add_an_parking_order_when_post_a_parking_order() throws Exception{
        //Given
        ParkingLot parkingLot = new ParkingLot("LotA","ZHA",10);
        ParkingOrder parkingOrder = new ParkingOrder("OrderA","A1001",true,parkingLot);
        JSONObject parkingOrderJSONObject = new JSONObject(parkingOrder);
        //When

        this.mockMvc.perform(post("/parking-orders").
                contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(parkingOrderJSONObject.toString())).andExpect(status().isCreated()).andReturn();
        List<ParkingOrder> parkingOrders = parkingOrderRepository.findAll();
        //Then
        assertEquals(1,parkingOrders.size());
    }
}
