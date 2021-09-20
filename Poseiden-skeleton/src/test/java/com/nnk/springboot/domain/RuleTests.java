package com.nnk.springboot.domain;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
public class RuleTests {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Test
	public void ruleTest() {
		RuleName rule = new RuleName((Integer) 1, "Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		rule = ruleNameRepository.save(rule);
		Assert.assertNotNull(rule.getId());
		Assert.assertTrue(rule.getName().equals("Rule Name"));

		// Update
		rule.setName("Rule Name Update");
		rule = ruleNameRepository.save(rule);
		Assert.assertTrue(rule.getName().equals("Rule Name Update"));

		// Find
		List<RuleName> listResult = ruleNameRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rule.getId();
		ruleNameRepository.delete(rule);
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		Assert.assertFalse(ruleList.isPresent());
	}

	@Test
	public void getters_should_return_values_set_through_setters(){
		RuleName rulename = new RuleName();
		Integer id = 1;
		String description = "descriptionTest";
		String template = "templateTest";
		String sqlStr = "sqlStr";

		rulename.setId(id);
		rulename.setDescription(description);
		rulename.setTemplate(template);
		rulename.setSqlStr(sqlStr);

		Assertions.assertEquals(id, rulename.getId());
		Assertions.assertEquals(description, rulename.getDescription());
		Assertions.assertEquals(template, rulename.getTemplate());
		Assertions.assertEquals(sqlStr, rulename.getSqlStr());
	}
}
