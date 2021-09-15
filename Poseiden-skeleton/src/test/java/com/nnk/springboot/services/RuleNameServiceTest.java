package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
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
public class RuleNameServiceTest {

    @Autowired
    RuleNameService ruleNameService;


    @BeforeEach
    public void setUp(){
        List<RuleName> ruleNames = ruleNameService.findAll();
        if (ruleNames.size() !=0){
            for (RuleName ruleName: ruleNames) {
                ruleNameService.delete(ruleName.getId());
            }
        }
    }

    @Test
    public void ruleName_should_be_populated_in_database(){
        RuleName ruleName = new RuleName (3, "account", "type", "e","e","e","e");
        ruleNameService.save(ruleName);
        List<RuleName> ruleNames = ruleNameService.findAll();
        Assertions.assertEquals(1, ruleNames.size());
        Assertions.assertEquals(ruleName.getName(), ruleNames.get(0).getName());
        Assertions.assertEquals(ruleName.getDescription(), ruleNames.get(0).getDescription());
    }

    @Test
    public void ruleName_should_be_found_by_id(){
        RuleName ruleName = new RuleName (3, "account", "type", "e","e","e","e");
        ruleNameService.save(ruleName);
        List<RuleName> ruleNames = ruleNameService.findAll();
        for (RuleName ruleNameCurrent: ruleNames) {
            RuleName ruleNameToRetrieve = ruleNameService.findById(ruleNameCurrent.getId());
            Assertions.assertEquals(ruleNameCurrent.getId(), ruleNameToRetrieve.getId());
        }
    }

    @Test
    public void ruleName_should_be_deleted(){
        //Populate database with a ruleName.
        RuleName ruleName = new RuleName (3, "account", "type", "e","e","e","e");
        ruleNameService.save(ruleName);

        //Retrieve all ruleNames.
        List<RuleName> ruleNames = ruleNameService.findAll();

        //Iterate through the list and delete all of them.
        for (RuleName ruleName1: ruleNames) {
            RuleName ruleName2 = ruleNameService.findById(ruleName1.getId());
            ruleNameService.delete(ruleName2.getId());

            //Assertions to verify if the current ruleName is not findable.
            Assertions.assertNull(ruleNameService.findById(ruleName2.getId()));
        }
    }

}


