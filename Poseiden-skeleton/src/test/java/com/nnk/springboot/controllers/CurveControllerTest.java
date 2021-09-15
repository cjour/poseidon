package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CurveController.class)
public class CurveControllerTest {

    @MockBean
    CurvePointService curvePointService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private List<CurvePoint> listOfCurvePoint;

    @Before
    public void setUp() {
        listOfCurvePoint = new ArrayList<>();
        listOfCurvePoint.add(new CurvePoint(1, 1,2d,2d));
        listOfCurvePoint.add(new CurvePoint(1, 1,2d,2d));

    }

    @Test
    @WithAnonymousUser
    public void test3() throws Exception {
        mockMvc.perform(get("/curvePoint/list")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void test2() throws Exception {
        mockMvc.perform(get("/curvePoint/list")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void test4() throws Exception {
        mockMvc.perform(get("/curvePoint/add")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void test5() throws Exception {
        mockMvc.perform(get("/curvePoint/add")).andExpect(status().isOk());
    }

//    @Ignore
//    @Test
//    @WithAnonymousUser
//    public void test8() throws Exception {
//        BidList bidList = new BidList(1, "testAccount", "testType", 1d,1d);
//        mockMvc.perform(post("/bidList/validate").flashAttr("bidList", bidList)).andExpect(status().isOk());
//    }
//
//    @Ignore
//    @Test
//    @WithMockUser
//    public void test7() throws Exception {
//        BidList bidList = new BidList(1, "", "testType", 1d,1d);
//        mockMvc.perform(post("/bidList/validate").flashAttr("ruleName", bidList))
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser
    public void test9() throws Exception {
        CurvePoint curvePoint = new CurvePoint(1, 1,2d,2d);
        Mockito.when(curvePointService.findById(anyInt())).thenReturn(curvePoint);
        mockMvc.perform(get("/curvePoint/delete/1")).andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    public void test10() throws Exception {
        CurvePoint curvePoint = new CurvePoint(1, 1,2d,2d);
        Mockito.when(curvePointService.findById(anyInt())).thenReturn(curvePoint);
        mockMvc.perform(get("/curvePoint/update/1").flashAttr("curvePoint", curvePoint)).andExpect(status().isOk());
    }
}

