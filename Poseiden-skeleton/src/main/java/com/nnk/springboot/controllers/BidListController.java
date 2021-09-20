package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {

    private static final Logger LOGGER = LogManager.getLogger(BidListController.class);

    @Autowired
    BidListService bidListService;

    public BidListController() {}

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        List<BidList> listOfBidList = bidListService.findAll();
        model.addAttribute("listOfBidList", listOfBidList);
        LOGGER.info("BidListController (home) -> " + listOfBidList.size() + "bidList(s) found");
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        LOGGER.info("BidListController (add) -> retrieving add form");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            bidListService.save(bidList);
            LOGGER.info("BidListController (save) -> bidList successfully added");
            return "redirect:/bidList/list";
        }
        LOGGER.info("BidListController (save) -> Invalid fields values");
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id);
        if(bidList != null) {
            LOGGER.info("BidListController (update) -> bidList successfully found");
            model.addAttribute("bidList", bidList);
        } else {
            LOGGER.info("BidListController (save) -> bidList unsuccessfully found");
        }
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {

        if(!result.hasErrors()) {
            bidListService.save(bidList);
            LOGGER.info("BidListController (update) -> bidList successfully update");
        } else {
            LOGGER.info("BidListController (update) -> bidList unsuccessfully updated");
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id);
        if(bidList != null) {
            bidListService.delete(bidList.getId());
            LOGGER.info("BidListController (delete) -> bidList successfully deleted");
        } else {
            LOGGER.info("BidListController (delete) -> bidList unsuccessfully deleted");
        }
        return "redirect:/bidList/list";
    }
}
