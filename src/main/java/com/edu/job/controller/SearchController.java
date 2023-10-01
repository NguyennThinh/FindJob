package com.edu.job.controller;

import com.edu.job.model.Recruitment;
import com.edu.job.repository.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @GetMapping("/job")
    public String searchJob(Model model, @RequestParam(value = "keySearch", required = false, defaultValue = "") String keySearch){

        List<Recruitment> recruitments = null;

        if (keySearch.equals("")){
            recruitments = recruitmentRepository.findAll();
        }else {
             recruitments = recruitmentRepository.findByJob(keySearch);
        }
        model.addAttribute("recruitments",recruitments);

        return "public/result-search";
    }

    @GetMapping("/address")
    public String searchAddress(Model model, @RequestParam(value = "keySearch", required = false, defaultValue = "") String keySearch){

        List<Recruitment> recruitments = null;

        if (keySearch.equals("")){
            recruitments = recruitmentRepository.findAll();
        }else {
            recruitments = recruitmentRepository.findByAddress(keySearch);
        }
        model.addAttribute("recruitments",recruitments);

        return "public/result-search";
    }
    @GetMapping("/company")
    public String searchCompany(Model model, @RequestParam(value = "keySearch", required = false, defaultValue = "") String keySearch){

        List<Recruitment> recruitments = null;

        if (keySearch.equals("")){
            recruitments = recruitmentRepository.findAll();
        }else {
            recruitments = recruitmentRepository.findByAddCompany(keySearch);
        }
        model.addAttribute("recruitments",recruitments);

        return "public/result-search";
    }
}
