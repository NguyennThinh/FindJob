package com.edu.job.repository;

import com.edu.job.model.Category;
import com.edu.job.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
