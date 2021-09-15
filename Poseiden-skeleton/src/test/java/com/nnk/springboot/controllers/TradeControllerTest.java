package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
@WebMvcTest(controllers = TradeController.class)
public class TradeControllerTest {

    @MockBean
    TradeService tradeService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private List<Trade> listOfTrade;

    @Before
    public void setUp() {
        listOfTrade = new ArrayList<>();
        listOfTrade.add(new Trade(1, "","",1d));
        listOfTrade.add(new Trade(1, "","",1d));

    }

    @Test
    @WithAnonymousUser
    public void test3() throws Exception {
        mockMvc.perform(get("/trade/list")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void test2() throws Exception {
        mockMvc.perform(get("/trade/list")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void test4() throws Exception {
        mockMvc.perform(get("/trade/add")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void test5() throws Exception {
        mockMvc.perform(get("/trade/add")).andExpect(status().isOk());
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
        Trade trade = new Trade(1, "","",1d);
        Mockito.when(tradeService.findById(anyInt())).thenReturn(trade);
        mockMvc.perform(get("/trade/delete/1")).andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    public void test10() throws Exception {
        Trade trade = new Trade(1, "","",1d);
        Mockito.when(tradeService.findById(anyInt())).thenReturn(trade);
        mockMvc.perform(get("/trade/update/1").flashAttr("trade", trade)).andExpect(status().isOk());
    }
}


