package com.gonchaba.codeassighnment.domain;

import com.gonchaba.codeassighnment.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
public class CalcUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String userName;

    @NotBlank
    @Email
    @Column(nullable = false, unique = false)
    private String name;

    @NotBlank
    private String password;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "user")
    private Set<Operation> operations;
    @Column
    private double balance;


    public CalcUser(String userName, String password, double balance) {
    }
}
