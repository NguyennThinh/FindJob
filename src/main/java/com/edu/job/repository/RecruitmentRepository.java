package com.edu.job.repository;

import com.edu.job.model.Recruitment;
import com.edu.job.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecruitmentRepository extends JpaRepository<Recruitment, String> {

    @Query("SELECT u FROM Recruitment u WHERE u.title like %:title%")
    public List<Recruitment> findByJob(@Param("title") String title);

    @Query("SELECT u FROM Recruitment u WHERE u.address like %:address%")
    public List<Recruitment> findByAddress(@Param("address") String address);

    @Query("SELECT u FROM Recruitment u WHERE u.company.companyName like %:companyName%")
    public List<Recruitment> findByAddCompany(@Param("companyName") String companyName);


}
