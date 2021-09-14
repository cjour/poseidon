package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
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
public class BidListServiceTest {

    @Autowired
    BidListService bidListService;


    @BeforeEach
    public void setUp(){
        List<BidList> bids = bidListService.findAll();
        for (BidList bid: bids) {
            bidListService.delete(bid.getId());
        }
    }

    @Test
    public void bidList_should_be_populated_in_database(){
        BidList bidList = new BidList(3, "account", "type", 5d, 5d);
        bidListService.save(bidList);
        List<BidList> bids = bidListService.findAll();
        Assertions.assertEquals(1, bids.size());
        Assertions.assertEquals(bidList.getAccount(), bids.get(0).getAccount());
        Assertions.assertEquals(bidList.getType(), bids.get(0).getType());
        Assertions.assertEquals(bidList.getBidQuantity(), bids.get(0).getBidQuantity());
        Assertions.assertEquals(bidList.getAskQuantity(), bids.get(0).getAskQuantity());
    }
}
