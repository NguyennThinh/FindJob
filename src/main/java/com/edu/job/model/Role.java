package com.edu.job.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private  int idRole;

    @Column(name = "role_name", columnDefinition = "nvarchar(255)")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
