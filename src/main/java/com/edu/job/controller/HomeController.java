package com.edu.job.controller;

import com.edu.job.model.Category;
import com.edu.job.model.Company;
import com.edu.job.model.Recruitment;
import com.edu.job.model.User;
import com.edu.job.repository.CategoryRepository;
import com.edu.job.repository.CompanyRepository;
import com.edu.job.repository.RecruitmentRepository;
import com.edu.job.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RecruitmentRepository recruitmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping(value = {"/index", "/"})
    public String index(Model model){


        List<Recruitment> recruitments = recruitmentRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        List<Company> companies = companyRepository.findAll();

        model.addAttribute("recruitments",recruitments);
        model.addAttribute("categories",categories);
        model.addAttribute("companies",companies);
        return "public/home";
    }
}
