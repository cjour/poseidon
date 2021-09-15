package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.junit.Before;
import org.junit.Ignore;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BidListController.class)
public class BidListControllerTest {

    @MockBean
    BidListService bidListService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private List<BidList> listOfBidList;

    @Before
    public void setUp() {
        listOfBidList = new ArrayList<>();
        listOfBidList.add(new BidList(1, "testAccount", "testType", 1d,1d));
        listOfBidList.add(new BidList(2, "testAccount", "testType", 1d,1d));

    }

    @Test
    @WithAnonymousUser
    public void test3() throws Exception {
        mockMvc.perform(get("/bidList/list")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void test2() throws Exception {
        mockMvc.perform(get("/bidList/list")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void test4() throws Exception {
        mockMvc.perform(get("/bidList/add")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void test5() throws Exception {
        mockMvc.perform(get("/bidList/add")).andExpect(status().isOk());
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
//        mockMvc.perform(post("/bidList/validate").flashAttr("bidList", bidList))
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser
    public void test9() throws Exception {
        BidList bidList = new BidList(1, "test", "test", 20d,20d);
        Mockito.when(bidListService.findById(anyInt())).thenReturn(bidList);
        mockMvc.perform(get("/bidList/delete/1")).andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    public void test10() throws Exception {
        BidList bidList = new BidList(1, "", "testType", 1d,1d);
        Mockito.when(bidListService.findById(anyInt())).thenReturn(bidList);
        mockMvc.perform(get("/bidList/update/1").flashAttr("bidList", bidList)).andExpect(status().isOk());
    }

}
