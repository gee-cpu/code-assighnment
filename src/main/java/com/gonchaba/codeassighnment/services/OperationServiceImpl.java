package com.gonchaba.codeassighnment.services;

import com.gonchaba.codeassighnment.config.OperationConfig;
import com.gonchaba.codeassighnment.domain.Operation;
import com.gonchaba.codeassighnment.enums.OperationType;
import com.gonchaba.codeassighnment.repository.OperationRepository;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationConfig operationConfig;
    private final RandomStringGenerator randomStringGenerator;

    private final UserService userService;
    private final OperationRepository operationRepository;


    public OperationServiceImpl(OperationConfig operationConfig, RandomStringGenerator randomStringGenerator, UserService userService, OperationRepository operationRepository) {
        this.operationConfig = operationConfig;
        this.randomStringGenerator = randomStringGenerator;

        this.userService = userService;
        this.operationRepository = operationRepository;
    }


    @Override
    public double getOperationCost(OperationType operationType) {
        switch (operationType) {
            case ADDITION:
                return operationConfig.getAdditionCost();
            case SUBTRACTION:
                return operationConfig.getSubtractionCost();
            case MULTIPLICATION:
                return operationConfig.getMultiplicationCost();
            case DIVISION:
                return operationConfig.getDivisionCost();
            case SQUARE_ROOT:
                return operationConfig.getSquareRootCost();
            case RANDOM_STRING:
                return operationConfig.getRandomStringCost();
            default:
                throw new IllegalArgumentException("Invalid operation type");
        }
    }

    @Override
    public double addition(double num1, double num2) {
        return num1 + num2;
    }

    @Override
    public double subtraction(double num1, double num2) {
        return num1 - num2;
    }

    @Override
    public double multiplication(double num1, double num2) {
        return num1 * num2;
    }

    @Override
    public double division(double num1, double num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return num1 / num2;
    }

    @Override
    public double squareRoot(double num) {
        if (num < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(num);
    }

    @Override
    public String randomString(int length) {
        return null;
    }


    @Override
    public Operation findByType(OperationType operationType) {

        return operationRepository.findByType(operationType);

    }

    @Override
    public void save(Operation operation) {
        operationRepository.save(operation);
    }


}

