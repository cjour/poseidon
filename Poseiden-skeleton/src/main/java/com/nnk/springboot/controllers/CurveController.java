package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
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
public class CurveController {

    private static final Logger LOGGER = LogManager.getLogger(CurveController.class);

    @Autowired
    CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        List<CurvePoint> listOfCurvePoint = curvePointService.findAll();
        model.addAttribute("listOfCurvePoint", listOfCurvePoint);
        LOGGER.info("CurvePointController (home) -> " + listOfCurvePoint.size() + "curvePoint(s) found");
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        LOGGER.info("CurvePointController (add) -> add form retrieved");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            curvePointService.save(curvePoint);
            LOGGER.info("CurvePointController (validate) -> curvePoint saved");
            return "redirect:/curvePoint/list";
        } else {
            LOGGER.info("CurvePointController (validate) -> invalid fields values");
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id);
        if (curvePoint != null){
            model.addAttribute("curvePoint", curvePoint);
            LOGGER.info("CurvePointController (update) -> curvePoint found");
        } else {
            LOGGER.info("CurvePointController (update) -> curvePoint couldn't be found");
        }
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if (!result.hasErrors()){
            curvePointService.save(curvePoint);
            LOGGER.info("CurvePointController (validate) -> curvePoint updated");
        } else {
            LOGGER.info("CurvePointController (validate) -> curvePoint couldn't be updated");
        }
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id);
        if (curvePoint != null) {
            curvePointService.delete(curvePoint.getId());
            LOGGER.info("CurvePointController (delete) -> curvePoint deleted");
        } else {
            LOGGER.info("CurvePointController (delete) -> curvePoint couldn't be deleted");
        }
        return "redirect:/curvePoint/list";
    }
}
