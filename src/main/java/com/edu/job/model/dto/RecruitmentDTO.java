package com.edu.job.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentDTO {
    @Column(columnDefinition = "nvarchar(max)")
    private String address;
    @Column(columnDefinition = "nvarchar(max)")
    private String description;

    @Column(columnDefinition = "nvarchar(max)")
    private String experience;

    @Column(columnDefinition = "nvarchar(255)")
    private String rank;
    private double salary;

    @Column(columnDefinition = "nvarchar(255)")
    private String title;

    @Column(columnDefinition = "nvarchar(255)")
    private String type;

    private String deadline;

    private int category_id;
    private int quantity;
}
