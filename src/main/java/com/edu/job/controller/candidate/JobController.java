package com.edu.job.controller.candidate;

import com.edu.job.model.ApplyPost;
import com.edu.job.model.Recruitment;
import com.edu.job.model.SaveJob;
import com.edu.job.model.User;
import com.edu.job.repository.ApplyPostRepository;
import com.edu.job.repository.SaveJobRepository;
import com.edu.job.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JobController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SaveJobRepository jobRepository;

    @Autowired
    private ApplyPostRepository applyPostRepository;


//Danh sách công việc đã lưu
    @GetMapping("/save-job/list")
    public String listJobSave(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.getUserByEmail(auth.getName());
        List<Recruitment> jobList = new ArrayList<>();
        List<SaveJob> saveJobList = jobRepository.getSaveJobByUser(user.getIdUser());
        user.getJobs().forEach(job -> {
            jobList.add(job.getRecruitment());
        });

        model.addAttribute("jobList", jobList);
        model.addAttribute("saveJobList",saveJobList);

        return "public/list-save-job";
    }

    @GetMapping("/save-job/delete/{id}")
    public String deleteJobSave(RedirectAttributes attributes, @PathVariable int id){



        jobRepository.deleteById(id);

        attributes.addFlashAttribute("delete",true);

        return "redirect:/save-job/list";
    }

//Danh sách công việc đã nộp cv
    @GetMapping("/apply-job/list")
    public String listJobApply(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.getUserByEmail(auth.getName());

        List<ApplyPost> applyJobList = applyPostRepository.findByUser(user.getIdUser());

        model.addAttribute("applyJobList",applyJobList);

        return "public/list-apply-job";
    }
}
