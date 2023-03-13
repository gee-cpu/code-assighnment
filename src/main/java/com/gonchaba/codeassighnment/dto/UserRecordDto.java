package com.gonchaba.codeassighnment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class UserRecordDto {
    private Long id;
    private Long operationId;
    private Double amount;
    private Double userBalance;
    private String userName;
    private LocalDateTime recordDate;
    private String operationResponse;

}



