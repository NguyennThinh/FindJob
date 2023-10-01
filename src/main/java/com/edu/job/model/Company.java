package com.edu.job.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "Companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @Column(name = "id_company")
    private String idCompany;

    @Column(columnDefinition = "nvarchar(255)", name = "company_name")
    private String companyName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(columnDefinition = "nvarchar(max)")
    private String address;

    @Column(columnDefinition = "varchar(255)")
    private String email;
    @Column(columnDefinition = "varchar(255)")
    private String logo;

    @Column(columnDefinition = "nvarchar(max)")
    private String description;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<User> users;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Recruitment> recruitments;

    @OneToMany(mappedBy = "company")
    private Set<FollowCompany> followers;
}
