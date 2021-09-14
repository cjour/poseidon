package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Trade;
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
public class TradeServiceTest {

    @Autowired
    TradeService tradeService;


    @BeforeEach
    public void setUp(){
        List<Trade> trades = tradeService.findAll();
        for (Trade trade: trades) {
            tradeService.delete(trade.getId());
        }
    }

    @Test
    public void trade_should_be_populated_in_database(){
        Trade trade = new Trade(3, "account", "type", 5d);
        tradeService.save(trade);
        List<Trade> trades = tradeService.findAll();
        Assertions.assertEquals(1, trades.size());
        Assertions.assertEquals(trade.getAccount(), trades.get(0).getAccount());
        Assertions.assertEquals(trade.getType(), trades.get(0).getType());
        Assertions.assertEquals(trade.getBuyQuantity(), trades.get(0).getBuyQuantity());
    }

    @Test
    public void trade_should_be_found_by_id(){
        Trade trade = new Trade(3, "account", "type", 5d);
        tradeService.save(trade);
        List<Trade> trades = tradeService.findAll();
        for (Trade trade1: trades) {
            Trade tradeToRetrieve = tradeService.findById(trade1.getId());
            Assertions.assertEquals(trade1.getId(), tradeToRetrieve.getId());
        }
    }

    @Test
    public void trade_should_be_deleted(){
        //Populate database with a trade.
        Trade trade = new Trade(3, "account", "type", 5d);
        tradeService.save(trade);

        //Retrieve all trades
        List<Trade> trades = tradeService.findAll();

        //Iterate through the list and delete all of them.
        for (Trade trade1: trades) {
            Trade trade2 = tradeService.findById(trade1.getId());
            tradeService.delete(trade2.getId());

            //Assertions to verify if the current trade is not findable.
            Assertions.assertNull(tradeService.findById(trade2.getId()));
        }
    }

}

