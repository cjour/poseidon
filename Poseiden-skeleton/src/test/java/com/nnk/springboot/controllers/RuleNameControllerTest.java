package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
@WebMvcTest(controllers = RuleNameController.class)
public class RuleNameControllerTest {

    @MockBean
    RuleNameService ruleNameService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private List<RuleName> listOfRuleName;

    @Before
    public void setUp() {
        listOfRuleName = new ArrayList<>();
        listOfRuleName.add(new RuleName(1, "testAccount", "testType", "testType","testType","testType","testType"));
        listOfRuleName.add(new RuleName(2, "testAccount", "testType", "testType","testType","testType","testType"));

    }

    @Test
    @WithAnonymousUser
    public void test3() throws Exception {
        mockMvc.perform(get("/ruleName/list")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void test2() throws Exception {
        mockMvc.perform(get("/ruleName/list")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void test4() throws Exception {
        mockMvc.perform(get("/ruleName/add")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void test5() throws Exception {
        mockMvc.perform(get("/ruleName/add")).andExpect(status().isOk());
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
        RuleName ruleName = new RuleName(2, "testAccount", "testType", "testType","testType","testType","testType");
        Mockito.when(ruleNameService.findById(anyInt())).thenReturn(ruleName);
        mockMvc.perform(get("/ruleName/delete/1")).andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    public void test10() throws Exception {
        RuleName ruleName = new RuleName(2, "testAccount", "testType", "testType","testType","testType","testType");
        Mockito.when(ruleNameService.findById(anyInt())).thenReturn(ruleName);
        mockMvc.perform(get("/ruleName/update/1").flashAttr("ruleName", ruleName)).andExpect(status().isOk());
    }
}
