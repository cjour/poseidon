package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements IRatingsService {

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public List<Rating> findAll() { return ratingRepository.findAll(); }

    @Override
    public Rating save(Rating rating) { return ratingRepository.save(rating); }

    @Override
    public Rating findById(Integer id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        return rating.orElse(null);
    }

    @Override
    public void delete(Integer id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isPresent()) {
            ratingRepository.delete(rating.get());
        }
    }
}
