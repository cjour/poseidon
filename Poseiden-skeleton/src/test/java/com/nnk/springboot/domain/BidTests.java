package com.nnk.springboot.domain;

import com.nnk.springboot.repositories.BidListRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidTests {


	@Autowired
	private BidListRepository bidListRepository;

	@Test
	public void bidListTest() {
		BidList bid = new BidList(1,"Account Test", "Type Test", 10d, 10d);

		// Save
		bid = bidListRepository.save(bid);
		Assert.assertNotNull(bid.getId());
		Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);

		// Update
		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);

		// Find
		List<BidList> listResult = bidListRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		Assert.assertFalse(bidList.isPresent());
	}

	@Test
	public void getters_should_return_values_set_through_setters(){
		BidList bidList = new BidList();
		Integer id = 1;
		String account = "account";
		String type = "type";
		Double askQuantity = 1.0D;

		bidList.setId(id);
		bidList.setAccount(account);
		bidList.setType(type);
		bidList.setAskQuantity(askQuantity);

		Assertions.assertEquals(id, bidList.getId());
		Assertions.assertEquals(account, bidList.getAccount());
		Assertions.assertEquals(type, bidList.getType());
		Assertions.assertEquals(askQuantity, bidList.getAskQuantity());
	}
}

