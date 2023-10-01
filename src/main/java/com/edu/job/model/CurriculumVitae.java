package com.edu.job.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitae {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "file_name")
    private String fileName;



}
