package com.gonchaba.codeassighnment.dto;

import com.gonchaba.codeassighnment.enums.OperationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class UserRecordDto {
    private Long id;
    private OperationType operationType;
    private Double amount;
    private Double userBalance;
    private String userName;
    private LocalDateTime recordDate;
    private String operationResponse;

}



