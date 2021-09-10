package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService implements IBidListService{

    @Autowired BidListRepository bidListRepository;

    /**
     * Find all bids present in database.
     * @return List<BidList>.
     **/
    @Override
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    /**
     * Save a bidList in database.
     * @param bidList to save in database.
     * @return BidList if saved successfully.
     **/
    @Override
    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    /**
     * Find a specific bidList with its id.
     * @param id corresponding to the bidList to retrieve.
     * @return BidList if found.
     **/
    @Override
    public BidList findById(Integer id) {
        Optional<BidList> bidList = bidListRepository.findById(id);
        return bidList.orElse(null);
    }

    /**
     * Allows to delete a bid in database.
     * @param id corresponding to the bidList to delete.
     **/
    @Override
    public void delete(Integer id) {
        BidList bidList = this.findById(id);
        if (bidList != null) {
            bidListRepository.delete(bidList);
        }
    }

}
