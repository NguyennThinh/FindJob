package com.edu.job.repository;

import com.edu.job.model.CurriculumVitae;
import com.edu.job.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CvRepositoy extends JpaRepository<CurriculumVitae, Integer> {

    @Query("SELECT c FROM CurriculumVitae c WHERE c.user.idUser =:id")
    public CurriculumVitae findByUser(@Param("id") String id);
}
