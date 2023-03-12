package com.gonchaba.codeassighnment.dto;

import com.gonchaba.codeassighnment.domain.Operation;
import com.gonchaba.codeassighnment.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data

public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private double balance;
    private String name;
    private Status status;
    public UserDto(Long id, String userName, double balance) {
    }
}


