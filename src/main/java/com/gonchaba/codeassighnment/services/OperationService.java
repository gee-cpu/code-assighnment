package com.gonchaba.codeassighnment.services;

import com.gonchaba.codeassighnment.domain.Operation;
import com.gonchaba.codeassighnment.enums.OperationType;

public interface OperationService {

    double getOperationCost(OperationType operationType);

    double addition(double num1, double num2);

    double subtraction(double num1, double num2);

    double multiplication(double num1, double num2);

    double division(double num1, double num2);

    double squareRoot(double num);

    String randomString(int length);

    Operation findByType(OperationType operationType);

    void save(Operation additionOperation);


}
