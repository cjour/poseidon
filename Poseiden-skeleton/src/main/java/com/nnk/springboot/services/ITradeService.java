package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITradeService {

    public List<Trade> findAll();

    public Trade save (Trade trade);

    public Trade findById(Integer id);

    public void delete(Integer id);
}
