package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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

    @Autowired
    BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        List<BidList> listOfBidList = bidListService.findAll();
        model.addAttribute("listOfBidList", listOfBidList);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {
        //Recheck interest of BindingResult class.
        if (!result.hasErrors()) {
            bidListService.save(bidList);
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id);
        if(bidList != null) {
            model.addAttribute("bidList", bidList);
        }
        return "bidList/update";
    }

    //TODO:find out why it can't retrieve correctly the ID
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {

        System.out.println(bidList.getBidListId());
        System.out.println(bidList.getAccount());


        if(!result.hasErrors()) {
            bidListService.save(bidList);
        } else {
            return "bidList";
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id);
        if(bidList != null) {
            bidListService.delete(bidList.getBidListId());
        }
        return "redirect:/bidList/list";
    }
}
