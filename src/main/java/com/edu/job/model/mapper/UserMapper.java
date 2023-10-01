package com.edu.job.model.mapper;

import com.edu.job.model.Recruitment;
import com.edu.job.model.User;
import com.edu.job.model.dto.RecruitmentDTO;
import com.edu.job.model.dto.UserDTO;

public class UserMapper {
    public static User toUser(UserDTO dto){
        User user =new User();
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setDescription(dto.getDescription());
        user.setFullName(dto.getFullName());
        user.setPhoneNumber(dto.getPhoneNumber());


        return user;
    }
}
