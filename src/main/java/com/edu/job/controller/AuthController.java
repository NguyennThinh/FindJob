package com.edu.job.controller;

import com.edu.job.model.Role;
import com.edu.job.model.User;
import com.edu.job.repository.RoleRepository;
import com.edu.job.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login-page")
    public String loginPage(){

        return "public/login";
    }

    @PostMapping("/register")
    public String Register(RedirectAttributes attributes,
            @RequestParam("email") String email, @RequestParam("fullName") String fullName,
                           @RequestParam("password") String password, @RequestParam("role_id") int roleId){

        Role role = roleRepository.getReferenceById(roleId);


        User user = new User();
        user.setIdUser(UUID.randomUUID().toString());
        user.setEmail(email);
        user.setFullName(fullName);

        String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt(12));

        user.setPassword(pw_hash);
        user.setRole(role);

        User register = userRepository.getUserByEmail(email);

        if(register != null){
            attributes.addFlashAttribute("msg_register_error", true);
            return "redirect:/auth/login-page";
        }

        userRepository.save(user);
        attributes.addFlashAttribute("msg_register_success", true);
        return "redirect:/auth/login-page";


    }


}
