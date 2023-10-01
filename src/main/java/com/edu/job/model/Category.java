package com.edu.job.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private int idCategory;

    @Column(name = "category_name", columnDefinition = "nvarchar(255)")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private Set<Recruitment> recruitments;
}
