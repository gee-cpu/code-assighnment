package com.gonchaba.codeassighnment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperationResult {
    private Double result;
    private String operationResponse;
    private Double updatedBalance;

}
