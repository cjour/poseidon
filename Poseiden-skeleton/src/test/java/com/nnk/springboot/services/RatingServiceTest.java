package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RatingServiceTest {

    @Autowired
    RatingService ratingService;

    @BeforeEach
    public void setUp(){
        List<Rating> ratings = ratingService.findAll();
        for (Rating curvePoint: ratings) {
            ratingService.delete(curvePoint.getId());
        }
    }

    @Test
    public void rating_should_be_populated_in_database(){
        Rating rating = new Rating(1,"test", "test","test",5);
        ratingService.save(rating);
        List<Rating> ratings = ratingService.findAll();
        Assertions.assertEquals(1, ratings.size());
        Assertions.assertEquals(rating.getMoodysRating(), ratings.get(0).getMoodysRating());
        Assertions.assertEquals(rating.getFitchRating(), ratings.get(0).getFitchRating());
        Assertions.assertEquals(rating.getOrderNumber(), ratings.get(0).getOrderNumber());
    }

    @Test
    public void rating_should_be_found_by_id(){
        Rating rating = new Rating(1,"test", "test","test",5);
        ratingService.save(rating);
        List<Rating> ratings = ratingService.findAll();
        for (Rating currentRating: ratings) {
            Rating curvePointToRetrieve = ratingService.findById(currentRating.getId());
            Assertions.assertEquals(currentRating.getId(), curvePointToRetrieve.getId());
        }
    }

    @Test
    public void rating_should_be_deleted(){
        //Populate database with a rating.
        Rating rating = new Rating(1,"test", "test","test",5);
        ratingService.save(rating);

        //Retrieve all ratings.
        List<Rating> curvePoints = ratingService.findAll();

        //Iterate through the list and delete all of them.
        for (Rating curvePoint: curvePoints) {
            Rating curvePointToDelete = ratingService.findById(curvePoint.getId());
            ratingService.delete(curvePointToDelete.getId());

            //Assertions to verify if the current rating is not findable.
            Assertions.assertNull(ratingService.findById(curvePoint.getId()));
        }
    }
}

