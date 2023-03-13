package com.gonchaba.codeassighnment.dto;

import com.gonchaba.codeassighnment.enums.Status;
import lombok.Data;

@Data

public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private double balance;
    private Status status;

}


