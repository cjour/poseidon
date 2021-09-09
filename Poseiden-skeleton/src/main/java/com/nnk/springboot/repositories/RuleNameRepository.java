package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rulename;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RuleNameRepository extends JpaRepository<Rulename, Integer> {
}
