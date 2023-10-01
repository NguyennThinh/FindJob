package com.edu.job.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id_user")
    private String idUser ;
    @Column(name = "full_name", columnDefinition = "nvarchar(255)")
    private String fullName;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(columnDefinition = "nvarchar(max)")
    private String address;

    @Column(columnDefinition = "nvarchar(max)")
    private String description;

    private String image;

    @Column(columnDefinition = "varchar(128)")
    private String password;

    private boolean enable =true;

    @OneToOne(mappedBy = "user")
    private CurriculumVitae cv;

    @OneToMany(mappedBy = "user")
    private Set<ApplyPost> applyPosts;

    @OneToMany(mappedBy = "user")
    private Set<FollowCompany> companies;

    @OneToMany(mappedBy = "user")
    private Set<SaveJob> jobs;

    @ManyToOne
    @JoinColumn(name = "id_company")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
