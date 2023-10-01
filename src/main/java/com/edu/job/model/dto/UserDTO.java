package com.edu.job.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String idUser;
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;

    private String description;
}
