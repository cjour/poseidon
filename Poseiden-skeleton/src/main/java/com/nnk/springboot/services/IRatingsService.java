package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface IRatingsService {

    public List<Rating> findAll();

    public Rating save (Rating rating);

    public Rating findById(Integer id);

    public void delete(Integer id);
}
