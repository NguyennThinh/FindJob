package com.edu.job.model;

import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recruitment {

    @Id
    @Column(name = "id_recruitment")
    private String idRecruitment;

    @Column(columnDefinition = "nvarchar(max)")
    private String address;
    @Column(name = "create_at")
    private String createAt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
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

    private int status =1;
    private int quantity;
    private int views =0;

    @OneToMany(mappedBy = "recruitment")
    private Set<ApplyPost> applyNumber;

    @OneToMany(mappedBy = "recruitment")
    private Set<SaveJob> saveUsers;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
