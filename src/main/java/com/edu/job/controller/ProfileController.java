package com.edu.job.controller;

import com.edu.job.model.Company;
import com.edu.job.model.CurriculumVitae;
import com.edu.job.model.User;
import com.edu.job.model.dto.CompanyDTO;
import com.edu.job.model.dto.UserDTO;
import com.edu.job.model.mapper.UserMapper;
import com.edu.job.repository.CompanyRepository;
import com.edu.job.repository.CvRepositoy;
import com.edu.job.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CvRepositoy cvRepositoy;

    @GetMapping("/profile/{id}")
    public String profilePage(@PathVariable String id, Model model) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null)
            model.addAttribute("user", user);

        if(user.getRole().getIdRole() ==1){
            return "public/profile_find_job";
        }
        return "public/profile";
    }

    @PostMapping("/profile/confirm-account")
    public String confirmAccount(@RequestParam("email") String email, Model model) {
        User user = userRepository.getUserByEmail(email);

        return "redirect:/profile/" + user.getIdUser();
    }


    @PostMapping("/user/update-profile")
    public String updateInfoUser(@ModelAttribute("user")UserDTO dto, RedirectAttributes attributes){

            User user = userRepository.findById(dto.getIdUser()).orElse(null);
            user.setEmail(dto.getEmail());
            user.setAddress(dto.getAddress());
            user.setFullName(dto.getFullName());
            user.setDescription(dto.getDescription());
            user.setPhoneNumber(dto.getPhoneNumber());

            userRepository.save(user);
        attributes.addFlashAttribute("result", true);
        return "redirect:/profile/"+user.getIdUser();
    }

    @PostMapping("/user/update-company")
    public String updateCompany(@ModelAttribute("company") CompanyDTO dto,@RequestParam("idUser") String userId ,RedirectAttributes attributes){

        Company company = companyRepository.findById(dto.getIdCompany()).orElse(null);

        if(company ==null){
            company = new Company();
            company.setIdCompany(UUID.randomUUID().toString());
            company.setEmail(dto.getEmail());
            company.setAddress(dto.getAddress());
            company.setCompanyName(dto.getCompanyName());
            company.setDescription(dto.getDescription());
            company.setPhoneNumber(dto.getPhoneNumber());
            companyRepository.save(company);
            User user = userRepository.findById(userId).get();
            user.setCompany(company);
            userRepository.save(user);
        }else {
            company.setEmail(dto.getEmail());
            company.setAddress(dto.getAddress());
            company.setCompanyName(dto.getCompanyName());
            company.setDescription(dto.getDescription());
            company.setPhoneNumber(dto.getPhoneNumber());
            companyRepository.save(company);
        }

        attributes.addFlashAttribute("result", true);

        return "redirect:/profile/"+userId;
    }
    @PostMapping("/profile/upload-image")
    public String updateProfile(@RequestParam("file") MultipartFile file, @RequestParam("email") String email, @RequestParam("type") String type) {

        System.out.println(type);
        if (type.equals("image-user")) {
            saveUserImage(file, email);
        } else {
            saveLogoCompany(file, email);
        }


        return "redirect:/";
    }

    private void saveUserImage(MultipartFile file, String email) {
        ClassPathResource resource = new ClassPathResource("/static/assets/images");
        String imgPath;
        try {
            imgPath = resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("Cannot get image path", e);
        }
        System.out.println("Img path:" + imgPath);

        Path filepath = Paths.get(imgPath, email + "_" + new SimpleDateFormat("mm_ss").format(new Date()) + file.getOriginalFilename());
        try {
            file.transferTo(filepath);
        } catch (IOException e) {
            throw new RuntimeException("Cannot save file", e);
        }

        User user = userRepository.getUserByEmail(email);
        user.setImage(filepath.getFileName().toString());
        userRepository.save(user);
    }

    private void saveLogoCompany(MultipartFile file, String email) {
        ClassPathResource resource = new ClassPathResource("/static/assets/images");
        String filePath;
        try {
            filePath = resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("Cannot get image path", e);
        }
        System.out.println("Img path:" + filePath);

        Path filepath = Paths.get(filePath, email + "_" + new SimpleDateFormat("mm_ss").format(new Date()) + file.getOriginalFilename());
        try {
            file.transferTo(filepath);
        } catch (IOException e) {
            throw new RuntimeException("Cannot save file", e);
        }
        Company company = companyRepository.getCompanyByEmail(email);

        company.setLogo(filepath.getFileName().toString());

        companyRepository.save(company);

    }

    @PostMapping("/profile/upload-cv")
    public String updateCV_pdf(@RequestParam("file") MultipartFile file, @RequestParam("email") String email) {

        uploadCV(file, email);
        return "redirect:/";
    }

    private void uploadCV(MultipartFile file, String email) {
        ClassPathResource resource = new ClassPathResource("/static/assets/images");
        String filePath;
        try {
            filePath = resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("Cannot get image path", e);
        }
        System.out.println("Img path:" + filePath);

        Path filepath = Paths.get(filePath, email + "_" + new SimpleDateFormat("mm_ss").format(new Date()) + file.getOriginalFilename());
        try {
            file.transferTo(filepath);
        } catch (IOException e) {
            throw new RuntimeException("Cannot save file", e);
        }

        User user = userRepository.getUserByEmail(email);
        CurriculumVitae cv = cvRepositoy.findByUser(user.getIdUser());
        if(cv == null){
            cv = new CurriculumVitae();
        }

        cv.setFileName(filepath.getFileName().toString());


        cv.setUser(user);
        user.setCv(cv);

        cvRepositoy.save(cv);
        userRepository.save(user);

    }
}
