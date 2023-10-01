package com.edu.job.controller.candidate;

import com.edu.job.model.*;
import com.edu.job.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplyPostRepository applyPostRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SaveJobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;

    //Xem danh sách công việc
    @GetMapping("/job/list")
    public String listJob(Model model){

        List<Category> categories = categoryRepository.findAll();




        model.addAttribute("categories", categories);

        return "public/listJob";
    }

    //Xem danh sách công việc theo danh mucj
    @GetMapping("/job/list/{category_id}")
    public String listJobByCategory(Model model, @PathVariable int category_id){

        Category category = categoryRepository.findById(category_id).orElse(null);


        List<Recruitment> recruitments = category.getRecruitments().stream().toList();
        model.addAttribute("recruitments", recruitments);
        List<Company>companies = companyRepository.getCompaniesOrOrderByJob().stream().sorted(Comparator.comparingDouble(s -> s.getRecruitments().size()))
                .collect(Collectors.toList());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && !auth.getName().equals("anonymousUser")){
            User user = userRepository.getUserByEmail(auth.getName());

            List<Recruitment> list = new ArrayList<>();
            user.getApplyPosts().forEach(applyPost -> {
                list.add(applyPost.getRecruitment());
            });
            List<Recruitment> jobList = new ArrayList<>();
            user.getJobs().forEach(job -> {
                jobList.add(job.getRecruitment());
            });

            model.addAttribute("jobList", jobList);
            model.addAttribute("listApply", list);
        }

        model.addAttribute("companies", companies);


        return "public/recruitment";
    }

    //Xem chi tết công việc
    @GetMapping("/job/detail/{id}")
    public String postDetail(Model model, @PathVariable String id){

        Recruitment recruitment = recruitmentRepository.findById(id).orElse(null);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && !auth.getName().equals("anonymousUser")) {
            User user = userRepository.getUserByEmail(auth.getName());

            List<Recruitment> list = new ArrayList<>();
            user.getApplyPosts().forEach(applyPost -> {
                list.add(applyPost.getRecruitment());
            });
            List<Recruitment> jobList = new ArrayList<>();
            user.getJobs().forEach(job -> {
                jobList.add(job.getRecruitment());
            });

            model.addAttribute("jobList", jobList);
            model.addAttribute("listApply", list);
        }

        model.addAttribute("recruitment",recruitment);


        return "public/detail-job";
    }

    //Nộp cv
    @PostMapping("/user/apply-cv")
    public String applyCV(@RequestParam("user") String idUser, @RequestParam("recruitment") String idRecruitment,@RequestParam("text")String text,
                          HttpServletRequest request) throws MalformedURLException {


        URL url = new URL(request.getHeader("Referer"));
        System.out.println(url.getPath());

        User user = userRepository.findById(idUser).get();
        Recruitment recruitment = recruitmentRepository.findById(idRecruitment).get();
        ApplyPost applyPost = new ApplyPost();
        applyPost.setUser(user);
        applyPost.setText(text);
        applyPost.setRecruitment(recruitment);
        applyPost.setCreateAt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        applyPost.setNameCv(user.getCv().getFileName());

        applyPostRepository.save(applyPost);


        return  "redirect:/";
    }

    @GetMapping("/save/job/{id}")
    public String saveJob(@PathVariable String id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && !auth.getName().equals("anonymousUser")){
            User user = userRepository.getUserByEmail(auth.getName());
            Recruitment recruitment = recruitmentRepository.findById(id).get();
            SaveJob saveJob = new SaveJob();
            saveJob.setUser(user);
            saveJob.setRecruitment(recruitment);
            jobRepository.save(saveJob);
        }

        return "redirect:/job/detail/"+id;

    }

    @GetMapping("/delete/job/{id}")
    public String cancelJob(@PathVariable String id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && !auth.getName().equals("anonymousUser")){
            User user = userRepository.getUserByEmail(auth.getName());
            Recruitment recruitment = recruitmentRepository.findById(id).get();

            jobRepository.delete( jobRepository.getSaveJobByUserAndRecruitment(user.getIdUser(), id));
        }

        return "redirect:/";

    }
}
