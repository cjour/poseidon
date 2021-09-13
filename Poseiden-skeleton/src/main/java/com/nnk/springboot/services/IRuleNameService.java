package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRuleNameService {

    public List<RuleName> findAll();

    public RuleName save (RuleName rulename);

    public RuleName findById(Integer id);

    public void delete(Integer id);
}
