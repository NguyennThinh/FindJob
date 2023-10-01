package com.edu.job.model.mapper;

import com.edu.job.model.Company;
import com.edu.job.model.User;
import com.edu.job.model.dto.CompanyDTO;
import com.edu.job.model.dto.UserDTO;

public class CompanyMapper {

    public static Company toUser(CompanyDTO dto){
        Company company =new Company();

        company.setIdCompany(dto.getIdCompany());
        company.setAddress(dto.getAddress());
        company.setEmail(dto.getEmail());
        company.setDescription(dto.getDescription());
        company.setCompanyName(dto.getCompanyName());
        company.setPhoneNumber(dto.getPhoneNumber());


        return company;
    }
}
