package com.nnk.springboot.domain;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeTests {

	@Autowired
	private TradeRepository tradeRepository;

	@Test
	public void tradeTest() {
		Trade trade = new Trade(1,"Trade Account", "Type", 10d);

		// Save
		trade = tradeRepository.save(trade);
		Assert.assertNotNull(trade.getId());
		Assert.assertTrue(trade.getAccount().equals("Trade Account"));

		// Update
		trade.setAccount("Trade Account Update");
		trade = tradeRepository.save(trade);
		Assert.assertTrue(trade.getAccount().equals("Trade Account Update"));

		// Find
		List<Trade> listResult = tradeRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = trade.getId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		Assert.assertFalse(tradeList.isPresent());
	}

	@Test
	public void test() {
		String account = "Account test";
		String type = "Type test";
		Double buyQuantity = 10d;
		Double sellQuantity = 11d;
		Double buyPrice = 12d;
		Double sellPrice = 13d;
		String benchmark = "Bench test";
		String security = "Secu Test";
		String status = "STAT";
		String trader = "Trader  test";
		String book = "Book test";
		String creationName = "Creation";
		String revisionName = "Rev test";
		String dealName = "Deal test";
		String dealType = "Deal type";
		String sourceListId = "SourceId";
		String side = "Side";
		Integer id = 1;

		Trade trade = new Trade();

		trade.setAccount(account);
		trade.setType(type);
		trade.setBuyQuantity(buyQuantity);
		trade.setSellQuantity(sellQuantity);
		trade.setBuyPrice(buyPrice);
		trade.setSellPrice(sellPrice);
		trade.setBenchmark(benchmark);
		trade.setSecurity(security);
		trade.setStatus(status);
		trade.setTrader(trader);
		trade.setBook(book);
		trade.setCreationName(creationName);
		trade.setRevisionName(revisionName);
		trade.setDealName(dealName);
		trade.setDealType(dealType);
		trade.setSourceListId(sourceListId);
		trade.setSide(side);
		trade.setId(id);


		Assert.assertEquals("account", account, trade.getAccount());
		Assert.assertEquals("type", type, trade.getType());
		Assert.assertEquals("buyQuantity", buyQuantity, trade.getBuyQuantity());
		Assert.assertEquals("sellQuantity", sellQuantity, trade.getSellQuantity());
		Assert.assertEquals("buyPrice", buyPrice, trade.getBuyPrice());
		Assert.assertEquals("sellPrice", sellPrice, trade.getSellPrice());
		Assert.assertEquals("benchmark", benchmark, trade.getBenchmark());
		Assert.assertEquals("security", security, trade.getSecurity());
		Assert.assertEquals("status", status, trade.getStatus());
		Assert.assertEquals("trader", trader, trade.getTrader());
		Assert.assertEquals("book", book, trade.getBook());
		Assert.assertEquals("creationName", creationName, trade.getCreationName());
		Assert.assertEquals("revisionName", revisionName, trade.getRevisionName());
		Assert.assertEquals("dealName", dealName, trade.getDealName());
		Assert.assertEquals("dealType", dealType, trade.getDealType());
		Assert.assertEquals("sourceIdList", sourceListId, trade.getSourceListId());
		Assert.assertEquals("side", side, trade.getSide());
		Assert.assertEquals("id", id, trade.getId());


	}
}
