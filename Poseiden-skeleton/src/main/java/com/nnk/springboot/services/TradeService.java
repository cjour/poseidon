package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService implements ITradeService {

    @Autowired
    TradeRepository tradeRepository;

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public Trade findById(Integer id) {
        Optional <Trade> trade = tradeRepository.findById(id);
        return trade.orElse(null);
    }

    @Override
    public void delete(Integer id) {
        Optional <Trade> trade = tradeRepository.findById(id);
        if (trade.isPresent()){
            tradeRepository.delete(trade.get());
        }
    }
}
