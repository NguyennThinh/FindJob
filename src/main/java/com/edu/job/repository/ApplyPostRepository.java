package com.edu.job.repository;

import com.edu.job.model.ApplyPost;
import com.edu.job.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplyPostRepository extends JpaRepository<ApplyPost,Integer> {
    @Query("SELECT u FROM ApplyPost u WHERE u.recruitment.idRecruitment =:id")
    List<ApplyPost> findByRecruitment(@Param("id") String id);

    @Query("SELECT u FROM ApplyPost u WHERE u.user.idUser =:id and u.recruitment.idRecruitment =:idR")
    public ApplyPost approveCV(@Param("id") String id,@Param("idR") String idR);

    @Query("SELECT u FROM ApplyPost u WHERE u.user.idUser =:id")
    List<ApplyPost> findByUser(@Param("id") String id);
}
