package com.edu.job.controller.candidate;

import com.edu.job.model.Company;
import com.edu.job.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/company/detail/{id}")
    public String companyDetail(Model model, @PathVariable String id){

        Company company = companyRepository.findById(id).get();

        model.addAttribute("company", company);

        return "public/post-company";
    }
}
