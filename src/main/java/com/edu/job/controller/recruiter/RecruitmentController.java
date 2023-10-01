package com.edu.job.controller.recruiter;

import com.edu.job.model.ApplyPost;
import com.edu.job.model.Category;
import com.edu.job.model.Recruitment;
import com.edu.job.model.User;
import com.edu.job.model.dto.RecruitmentDTO;
import com.edu.job.model.mapper.RecruitmentMapper;
import com.edu.job.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.UUID;

@Controller
public class RecruitmentController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RecruitmentRepository recruitmentRepository;
    @Autowired
    private ApplyPostRepository applyPostRepository;





    //Xem chi tiết bài tuyển dụng
    @GetMapping("/recruitment/detail/{id}")
    public String postDetail(Model model, @PathVariable String id){

        Recruitment recruitment = recruitmentRepository.findById(id).orElse(null);

        List<ApplyPost> applyPosts =  applyPostRepository.findByRecruitment(id);


        model.addAttribute("recruitment",recruitment);

        model.addAttribute("applyPosts",applyPosts);


        return "public/detail-recruitment";
    }

    //Danh sách bài tuyển dụng theo công ty của người tuyển dụng
    @GetMapping("/recruitment/list")
    public String listRecruitment(Model model, RedirectAttributes attributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUserByEmail(auth.getName());

        attributes.addFlashAttribute("success", true);
        model.addAttribute("company", user.getCompany());



        return "public/post-list";
    }



    //Trang tạo bài tuyển dụng mới
    @GetMapping("/recruitment/post")
    public String postPage(Model model){

        List<Category> categories =categoryRepository.findAll();

        model.addAttribute("categories",categories);

        return "public/post-job";
    }



    //Tạo bài tuyển dụng
    @PostMapping("/recruitment/add")
    public String postRecruitment(RedirectAttributes attributes, @ModelAttribute("recruit")RecruitmentDTO recruitment_dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUserByEmail(auth.getName());


        Category category = categoryRepository.findById(recruitment_dto.getCategory_id()).orElse(null);

        Recruitment recruitment = RecruitmentMapper.toRecruitment(recruitment_dto);

        recruitment.setIdRecruitment(UUID.randomUUID().toString());
        recruitment.setCompany(user.getCompany());
        recruitment.setCategory(category);

        recruitmentRepository.save(recruitment);

        attributes.addAttribute("success", true);


        return "redirect:/recruitment/list";
    }

    //Trang câp nhật bài tuyển dụng
    @GetMapping("/recruitment/edit/{id}")
    public String editPageRecruitment(Model model, @PathVariable String id){

        Recruitment recruitment = recruitmentRepository.findById(id).orElse(null);

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("recruitment",recruitment);
        model.addAttribute("categories",categories);




        return "public/edit-job";
    }


    //Câp nhật bài tuyển dụng
    @PostMapping("/recruitment/edit")
    public String editRecruitment(RedirectAttributes model, @ModelAttribute("job") RecruitmentDTO  dto, @RequestParam("id") String id){


        System.out.println(dto.getQuantity());

        Recruitment recruitment = recruitmentRepository.findById(id).orElse(null);
        recruitment.setAddress(dto.getAddress());
        recruitment.setCategory(categoryRepository.findById(dto.getCategory_id()).orElse(null));
        recruitment.setDeadline(dto.getDeadline());
        recruitment.setDescription(dto.getDescription());
        recruitment.setTitle(dto.getTitle());
        recruitment.setRank(dto.getRank());
        recruitment.setExperience(dto.getExperience());
        recruitment.setSalary(dto.getSalary());
        recruitment.setType(dto.getType());
        recruitment.setQuantity(dto.getQuantity());


        recruitmentRepository.save(recruitment);
        model.addFlashAttribute("success",true);

        return "redirect:/recruitment/edit/"+recruitment.getIdRecruitment();
    }

    //Xoá bài đăng
    @PostMapping("recruitment/delete")
    public String deleteRecruitment(@RequestParam String id, RedirectAttributes attributes){

        recruitmentRepository.deleteById(id);

        attributes.addFlashAttribute("delete", true);

        return "redirect:/recruitment/list";
    }

    //Duyệt post
    @GetMapping("/user/approve/{idUser}/{idRecruitment}")
    public String approveCV( RedirectAttributes attributes, @PathVariable("idUser") String idU, @PathVariable("idRecruitment")String idR){

        ApplyPost postApply = applyPostRepository.approveCV(idU, idR);

        postApply.setStatus(1);

        applyPostRepository.save(postApply);
        attributes.addFlashAttribute("approve", true);

        return "redirect:/recruitment/detail/"+idR;
    }
}
