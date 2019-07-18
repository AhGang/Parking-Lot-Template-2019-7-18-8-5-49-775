package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ParkingLotApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Before
    public void befroe_test(){
        parkingLotRepository.deleteAll();
    }
    @Test
    public void should_add_an_parking_lot_when_post_a_parking_lot() throws Exception {
        //Given
        ParkingLot parkingLotA = new ParkingLot("A", "zhu", 6000);
        JSONObject parkingLotJsonObject = new JSONObject(parkingLotA);
        //When
        final MvcResult mvcResult = this.mockMvc.perform(post("/parking-lots").
                contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(parkingLotJsonObject.toString())).andExpect(status().isCreated()).andReturn();
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
        //Then
        assertEquals(1,parkingLots.size());

    }

    @Test
    public void should_throw_exception_when_post_a_same_name_parking_lot() throws Exception {
        //Given
        ParkingLot parkingLotA = new ParkingLot("A", "zhu", 6000);
        JSONObject parkingLotJsonObject = new JSONObject(parkingLotA);
        this.mockMvc.perform(post("/parking-lots").contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(parkingLotJsonObject.toString())).andExpect(status().isCreated()).andReturn();
        ParkingLot parkingLotB = new ParkingLot("A", "guang", 7000);
        JSONObject parkingLotJsonObjectB = new JSONObject(parkingLotA);
        //When&Then
        Assertions.assertThrows(Exception.class, () -> {
            this.mockMvc.perform(post("/parking-lots").
                    contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(parkingLotJsonObjectB.toString())).andExpect(status().isCreated()).andReturn();
        });
    }
    @Test
    public void should_throw_exception_when_post_a_nagetive_capacity_parking_lot() throws Exception {
        //Given
        ParkingLot parkingLotA = new ParkingLot("A","zhu",-10);
        JSONObject parkingLotJsonObject = new JSONObject(parkingLotA);
        //When&Then
        Assertions.assertThrows(Exception.class, ()-> {
            this.mockMvc.perform(post("/parking-lots").
                    contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(parkingLotJsonObject.toString())).andExpect(status().isCreated()).andReturn();
        } );
    }

    @Test
    public void should_delete_a_parking_lot_when_give_a_specific__id_() throws Exception {
        //Given
        ParkingLot parkingLotA = new ParkingLot("A","zhu",200);
        ParkingLot parkingLotB = new ParkingLot("B","zhu",200);
        JSONObject parkingLotJsonObject = new JSONObject(parkingLotA);
        //When
        ParkingLot parkingLot = parkingLotRepository.save(parkingLotA);
        String specificID = parkingLot.getId();
        parkingLotRepository.save(parkingLotB);
        this.mockMvc.perform(delete("/parking-lots/"+specificID).contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(parkingLotJsonObject.toString())).andExpect(status().isOk()).andReturn();
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
        //Then
        assertEquals(1, parkingLots.size());
    }
    @Test
    public void should_check_a_parking_lot_when_give_a_specific__id_() throws Exception {
        //Given
        ParkingLot parkingLotA = new ParkingLot("A","zhu",200);
        JSONObject parkingLotJsonObject = new JSONObject(parkingLotA);
        //When
        ParkingLot parkingLot = parkingLotRepository.save(parkingLotA);
        String specificID = parkingLot.getId();
        this.mockMvc.perform(get("/parking-lots/"+specificID).contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(parkingLotJsonObject.toString())).andExpect(status().isOk()).andReturn();
        //Then
        assertEquals(parkingLotA.getName(),parkingLotRepository.findById(specificID).get().getName());
    }

    @Test
    public void should_update_a_parking_lot_when_give_a_specific__id_() throws Exception {
        //Given
        ParkingLot parkingLotA = new ParkingLot("A","zhu",200);
        //When
        ParkingLot parkingLot = parkingLotRepository.save(parkingLotA);
        parkingLot.setCapacity(500);
        String specificID = parkingLot.getId();
        parkingLotRepository.save(parkingLot);
        JSONObject parkingLotJsonObject = new JSONObject(parkingLot);
        this.mockMvc.perform(put("/parking-lots/"+specificID).contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).content(parkingLotJsonObject.toString())).andExpect(status().isOk()).andReturn();
        ParkingLot resultParkingLot = parkingLotRepository.findById(specificID).get();
        //Then
        assertEquals(parkingLot.getCapacity(),resultParkingLot.getCapacity());
    }
    @Test
    public void should_get_all_parking_lots_at_specific_pages_when_give_a_specific_pages() throws Exception {
        //Given
        List<ParkingLot> parkingLots = initTestData();
        //When
        int pages = 2;
        int pageSize =15;
        List<ParkingLot> resultParkingLots = parkingLotRepository.saveAll(parkingLots);
        resultParkingLots = parkingLots.stream().skip((pages-1) * pageSize).limit(pageSize).collect(Collectors.toList());
        final MvcResult mvcResult = this.mockMvc.perform(get("/parking-lots").param("page","2").param("pageSize","15")).andExpect(status().isOk()).andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
        //Then
        assertEquals(resultParkingLots.size(),jsonArray.length());
    }

    private List<ParkingLot> initTestData() {
        for (int i = 0 ; i < 20; i++){
            parkingLotRepository.save(new ParkingLot("A"+i,"zhu",200));
        }
        return parkingLotRepository.findAll();
    }
}

