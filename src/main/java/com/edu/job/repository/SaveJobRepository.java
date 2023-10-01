package com.edu.job.repository;

import com.edu.job.model.ApplyPost;
import com.edu.job.model.SaveJob;
import com.edu.job.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaveJobRepository extends JpaRepository<SaveJob,Integer> {

    @Query("SELECT u FROM SaveJob u WHERE u.user.idUser =:idU and u.recruitment.idRecruitment =:idR")
    public SaveJob getSaveJobByUserAndRecruitment(@Param("idU") String idU, @Param("idR") String idR);


    @Query("SELECT u FROM SaveJob u WHERE u.user.idUser =:idU ")
    public List<SaveJob> getSaveJobByUser(@Param("idU") String idU);
}
