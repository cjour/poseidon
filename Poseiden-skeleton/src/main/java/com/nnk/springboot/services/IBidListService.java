package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBidListService {

    public List<BidList> findAll();

    public BidList save (BidList bidList);

    public BidList findById(Integer id);

    public void delete(Integer id);

}
