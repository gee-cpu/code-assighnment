package com.gonchaba.codeassighnment.services;

import com.gonchaba.codeassighnment.domain.UserRecord;
import com.gonchaba.codeassighnment.enums.OperationType;
import com.gonchaba.codeassighnment.repository.UserRecordRepository;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    @Value("${operation.addition.cost}")
    private double additionCost;

    @Value("${operation.subtraction.cost}")
    private double subtractionCost;

    @Value("${operation.multiplication.cost}")
    private double multiplicationCost;

    @Value("${operation.division.cost}")
    private double divisionCost;

    @Value("${operation.square_root.cost}")
    private double squareRootCost;

    @Value("${operation.random_string.cost}")
    private double randomStringCost;


    private final RandomStringGenerator randomStringGenerator;


    private final UserRecordRepository recordRepository;

    public OperationServiceImpl( RandomStringGenerator randomStringGenerator, UserRecordRepository recordRepository) {
        this.randomStringGenerator = randomStringGenerator;
        this.recordRepository = recordRepository;
    }

    @Override
    public double getOperationCost(OperationType operationType) {
        switch (operationType) {
            case ADDITION:
                return additionCost;
            case SUBTRACTION:
                return subtractionCost;
            case MULTIPLICATION:
                return multiplicationCost;
            case DIVISION:
                return divisionCost;
            case SQUARE_ROOT:
                return squareRootCost;
            case RANDOM_STRING:
                return randomStringCost;
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
        return randomStringGenerator.generate(length);
    }

    @Override
    public void saveRecord(UserRecord record) {
        recordRepository.save(record);
    }
}

