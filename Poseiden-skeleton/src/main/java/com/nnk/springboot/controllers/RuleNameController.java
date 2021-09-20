package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
public class RuleNameController {

    private static final Logger LOGGER = LogManager.getLogger(RuleNameController.class);

    @Autowired
    RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        List<RuleName> listOfRuleName = ruleNameService.findAll();
        model.addAttribute("listOfRuleName", listOfRuleName);
        LOGGER.info("RuleName (home) -> " + listOfRuleName.size() + "rulename(s) found");
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        LOGGER.info("RuleName (add) -> rulename add form");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()){
            ruleNameService.save(ruleName);
            LOGGER.info("RuleName (validate) -> rulename saved");
            return "redirect:/ruleName/list";
        } else {
            LOGGER.info("RuleName (validate) -> incorrect field values");
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id);
        if (ruleName != null){
            model.addAttribute("ruleName", ruleName);
            LOGGER.info("RuleName (update) -> rulename to update found");
        } else {
            LOGGER.info("RuleName (update) -> rulename to update not found");
        }
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (!result.hasErrors()){
            ruleNameService.save(ruleName);
            LOGGER.info("RuleName (update) -> rulename updated");
        } else {
            LOGGER.info("RuleName (update) -> rulename couldn't be updated");
        }
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id);
        if (ruleName != null){
            ruleNameService.delete(id);
            LOGGER.info("RuleName (delete) -> rulename deleted");
        } else {
            LOGGER.info("RuleName (update) -> rulename couldn't be deleted");
        }
        return "redirect:/ruleName/list";
    }
}
