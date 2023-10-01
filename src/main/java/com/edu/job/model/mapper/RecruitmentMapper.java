package com.edu.job.model.mapper;

import com.edu.job.model.Recruitment;
import com.edu.job.model.dto.RecruitmentDTO;

public class RecruitmentMapper {

    public static Recruitment toRecruitment(RecruitmentDTO dto){
        Recruitment recruitment =new Recruitment();
        recruitment.setAddress(dto.getAddress());
        recruitment.setDeadline(dto.getDeadline());

        recruitment.setDescription(dto.getDescription());
        recruitment.setRank(dto.getRank());
        recruitment.setExperience(dto.getExperience());
        recruitment.setSalary(dto.getSalary());

        recruitment.setTitle(dto.getTitle());
        recruitment.setQuantity(dto.getQuantity());
        recruitment.setType(dto.getType());

        return recruitment;
    }
}
