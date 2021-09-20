package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RatingController {

    private static final Logger LOGGER = LogManager.getLogger(RatingController.class);

    @Autowired
    RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        List<Rating> ratinglist = ratingService.findAll();
        model.addAttribute("listOfRating", ratinglist);
        LOGGER.info("RatingController (home) -> " + ratinglist.size() + "rating(s) found");
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        LOGGER.info("RatingController (add) ->  rating add form");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingService.save(rating);
            LOGGER.info("RatingController (validate) ->  rating saved");
            return "redirect:/rating/list";
        } else {
            LOGGER.info("RatingController (validate) ->  rating not saved");
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id);
        if (rating != null){
           model.addAttribute("rating", rating);
            LOGGER.info("RatingController (update) ->  rating to update found");
        } else {
            LOGGER.info("RatingController (update) ->  rating not found");
        }
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (!result.hasErrors()){
            ratingService.save(rating);
            LOGGER.info("RatingController (update) ->  rating updated");
        } else {
            LOGGER.info("RatingController (update) ->  rating not updated");
        }
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id);
        if (rating != null){
            LOGGER.info("RatingController (delete) ->  rating deleted");
            ratingService.delete(id);
        } else {
            LOGGER.info("RatingController (delete) ->  rating not deleted");
        }
        return "redirect:/rating/list";
    }
}
