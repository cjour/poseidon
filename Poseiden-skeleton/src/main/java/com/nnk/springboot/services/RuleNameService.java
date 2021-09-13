package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService implements IRuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    @Override
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName save(RuleName rulename) {
        return ruleNameRepository.save(rulename);
    }

    @Override
    public RuleName findById(Integer id) {
        Optional<RuleName> rulenameOptional = ruleNameRepository.findById(id);
        return rulenameOptional.orElse(null);
    }

    @Override
    public void delete(Integer id) {
        Optional<RuleName> rulename = ruleNameRepository.findById(id);
        if (rulename.isPresent()) {
            ruleNameRepository.delete(rulename.get());
        }
    }
}
