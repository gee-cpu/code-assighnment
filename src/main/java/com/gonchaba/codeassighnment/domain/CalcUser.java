package com.gonchaba.codeassighnment.domain;

import com.gonchaba.codeassighnment.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
public class CalcUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Email
    @Column(nullable = false, unique = true)
    private String userName;


    @NotBlank
    private String password;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column
    private double balance;

}
