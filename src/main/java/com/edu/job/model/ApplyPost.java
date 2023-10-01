package com.edu.job.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "create_at")
    private String createAt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    @Column(name = "name_cv")
    private String nameCv;

    private String text;

    private int status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;
}
