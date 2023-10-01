package com.edu.job.repository;

import com.edu.job.model.Company;
import com.edu.job.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query("SELECT c FROM Company c WHERE c.email =:email")
    public Company getCompanyByEmail(@Param("email") String email);

    @Query("SELECT c FROM Company c ")
    public List<Company> getCompaniesOrOrderByJob();
}
