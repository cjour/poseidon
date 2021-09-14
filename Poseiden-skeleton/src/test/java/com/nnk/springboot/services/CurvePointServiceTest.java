package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CurvePointServiceTest {

    @Autowired
    CurvePointService curvePointService;

    @BeforeEach
    public void setUp(){
        List<CurvePoint> curvePoints = curvePointService.findAll();
        for (CurvePoint curvePoint: curvePoints) {
            curvePointService.delete(curvePoint.getId());
        }
    }

    @Test
    public void curvePoint_should_be_populated_in_database(){
        CurvePoint curvePoint = new CurvePoint(1,1,10d,10d);
        curvePointService.save(curvePoint);
        List<CurvePoint> curvePoints = curvePointService.findAll();
        Assertions.assertEquals(1, curvePoints.size());
        Assertions.assertEquals(curvePoint.getCurveId(), curvePoints.get(0).getCurveId());
        Assertions.assertEquals(curvePoint.getValue(), curvePoints.get(0).getValue());
        Assertions.assertEquals(curvePoint.getTerm(), curvePoints.get(0).getTerm());
    }

    @Test
    public void curvePoint_should_be_found_by_id(){
        CurvePoint curvePointToInsert = new CurvePoint(1,1,10d,10d);
        curvePointService.save(curvePointToInsert);
        List<CurvePoint> curvePoints = curvePointService.findAll();
        for (CurvePoint curvePoint: curvePoints) {
            CurvePoint curvePointToRetrieve = curvePointService.findById(curvePoint.getId());
            Assertions.assertEquals(curvePoint.getId(), curvePointToRetrieve.getId());
        }
    }

    @Test
    public void curvePoint_should_be_deleted(){
        //Populate database with a curvePoint.
        CurvePoint curvePointToInsert = new CurvePoint(1,1,10d,10d);
        curvePointService.save(curvePointToInsert);

        //Retrieve all curvePoints
        List<CurvePoint> curvePoints = curvePointService.findAll();

        //Iterate through the list and delete all of them.
        for (CurvePoint curvePoint: curvePoints) {
            CurvePoint curvePointToDelete = curvePointService.findById(curvePoint.getId());
            curvePointService.delete(curvePointToDelete.getId());

            //Assertions to verify if the current curvePoint is not findable.
            Assertions.assertNull(curvePointService.findById(curvePoint.getId()));
        }
    }
}

