package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {

    private static final Logger LOGGER = LogManager.getLogger(TradeController.class);

    @Autowired
    TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        List<Trade> listOfTrade = tradeService.findAll();
        model.addAttribute("listofTrade", listOfTrade);
        LOGGER.info("Trade (home) -> " + listOfTrade.size() + "trade(s) found");
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        LOGGER.info("Trade (add) -> trade add form");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.save(trade);
            LOGGER.info("Trade (validate) -> trade saved");
            return "redirect:/trade/list";
        } else {
            LOGGER.info("Trade (validate) -> trade not saved");
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id);
        if (trade != null){
            LOGGER.info("Trade (update) -> trade to update found");
            model.addAttribute("trade", trade);
        } else {
            LOGGER.info("Trade (update) -> trade to update not found");
        }
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if(!result.hasErrors()){
            tradeService.save(trade);
            LOGGER.info("Trade (update) -> trade updated");
        } else {
            LOGGER.info("Trade (update) -> trade couldn't be updated");
        }
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id);
        if (trade != null){
            tradeService.delete(id);
            LOGGER.info("Trade (delete) -> trade deleted");
        } else {
            LOGGER.info("Trade (update) -> trade couldn't be deleted");
        }
        return "redirect:/trade/list";
    }
}
