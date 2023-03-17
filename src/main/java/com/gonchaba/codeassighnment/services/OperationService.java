package com.gonchaba.codeassighnment.services;

import com.gonchaba.codeassighnment.domain.Operation;
import com.gonchaba.codeassighnment.dto.OperationDto;
import com.gonchaba.codeassighnment.dto.OperationResult;
import com.gonchaba.codeassighnment.dto.UserRecordDto;
import com.gonchaba.codeassighnment.enums.OperationType;

import java.util.List;
import java.util.Optional;

public interface OperationService {

    double getOperationCost(OperationType operationType, double userBalance);


    String randomString(String url);

    Operation findByType(OperationType operationType);

    void save(Operation additionOperation);
    OperationResult performOperation(String userName, OperationDto operationDto, OperationType operationType, UserRecordDto userRecordDto);
    List<Operation> getAllOperations();
    Optional<Operation> getOperationById(Long id);

    void deleteOperation(Long id);
}
