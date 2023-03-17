package com.gonchaba.codeassighnment.services;

import com.gonchaba.codeassighnment.config.OperationConfig;
import com.gonchaba.codeassighnment.domain.CalcUser;
import com.gonchaba.codeassighnment.domain.Operation;
import com.gonchaba.codeassighnment.domain.UserRecord;
import com.gonchaba.codeassighnment.dto.OperationDto;
import com.gonchaba.codeassighnment.dto.OperationResult;
import com.gonchaba.codeassighnment.dto.UserRecordDto;
import com.gonchaba.codeassighnment.enums.OperationType;
import com.gonchaba.codeassighnment.enums.Status;
import com.gonchaba.codeassighnment.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {

    private final OperationConfig operationConfig;

    private final UserService userService;
    private final OperationRepository operationRepository;

    private final UserRecordService recordService;

    public OperationServiceImpl(OperationConfig operationConfig, UserService userService, OperationRepository operationRepository, UserRecordService recordService) {
        this.operationConfig = operationConfig;
        this.userService = userService;
        this.operationRepository = operationRepository;
        this.recordService = recordService;
    }


    @Override
    public double getOperationCost(OperationType operationType, double userBalance) {
        double operationCost;
        switch (operationType) {
            case ADDITION:
                operationCost = operationConfig.getAdditionCost();
                break;
            case SUBTRACTION:
                operationCost = operationConfig.getSubtractionCost();
                break;
            case MULTIPLICATION:
                operationCost = operationConfig.getMultiplicationCost();
                break;
            case DIVISION:
                operationCost = operationConfig.getDivisionCost();
                break;
            case SQUARE_ROOT:
                operationCost = operationConfig.getSquareRootCost();
                break;
            case RANDOM_STRING:
                operationCost = operationConfig.getRandomStringCost();
                break;
            default:
                throw new IllegalArgumentException("Invalid operation type");
        }

        return operationCost;
    }


    @Override
    public String randomString(String url) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(operationConfig.getRandomStringUrl(), String.class);

        return Objects.requireNonNull(response.getBody()).trim();

    }

    @Override
    public Operation findByType(OperationType operationType) {

        return operationRepository.findByType(operationType);

    }

    @Override
    public void save(Operation operation) {
        operationRepository.save(operation);
    }

    @Override
    public OperationResult performOperation(String userName, OperationDto operationDto, OperationType operationType, UserRecordDto userRecordDto) {
        CalcUser user = userService.findByUserName(userName);

        if (user.getStatus() != Status.ACTIVE) {
            log.info("User account is inactive");
            throw new IllegalStateException("User account is inactive");
        }

        Double operationCost = getOperationCost(operationType, user.getBalance());
        if (operationCost > user.getBalance()) {
            log.info("User balance is insufficient for this operation");
            throw new IllegalStateException("Insufficient balance for this operation");
        }

        Double result = performOperationAndGetResult(operationDto, operationType);

        String operationResponse = result.toString();

        updateUserBalance(user, operationCost);

        Operation operation = createOperation(operationType, operationCost);

        UserRecord record = createUserRecord(user, operation, operationCost, operationResponse);

        UserRecordDto updatedUserRecordDto = updateUserRecordDto(user, operation, operationCost, operationResponse);

        recordService.save(record);

        return new OperationResult(result, operationResponse);
    }


    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();

    }

    @Override
    public Optional<Operation> getOperationById(Long id) {
        return operationRepository.findById(id);

    }

    @Override
    public void deleteOperation(Long id) {
        operationRepository.deleteById(id);
    }

    private Double performOperationAndGetResult(OperationDto operationDto, OperationType operationType) {
        Double result;
        switch (operationType) {
            case ADDITION:
                result = operationDto.getNum1() + operationDto.getNum2();
                break;
            case SUBTRACTION:
                result = operationDto.getNum1() - operationDto.getNum2();
                break;
            case MULTIPLICATION:
                result = operationDto.getNum1() * operationDto.getNum2();
                break;
            case DIVISION:
                if (operationDto.getNum2() == 0) {
                    log.info("Cannot divide by zero");
                }
                result = operationDto.getNum1() / operationDto.getNum2();
                break;

            case SQUARE_ROOT:
                if (operationDto.getNum1() < 0) {
                    throw new InvalidParameterException("Cannot take square root of negative number");
                }
                result = Math.sqrt(operationDto.getNum1());
                break;
            case RANDOM_STRING:
                String randomString = randomString(operationConfig.getRandomStringUrl());
                result = Double.parseDouble(randomString);
                break;
            default:
                throw new InvalidParameterException("Unsupported operation type");
        }
        return result;
    }

    private void updateUserBalance(CalcUser user, Double operationCost) {
        Double newBalance = user.getBalance() - operationCost;
        user.setBalance(newBalance);
        userService.createUser(user);
    }

    private Operation createOperation(OperationType operationType, Double operationCost) {
        Operation operation = new Operation();
        operation.setType(operationType);
        operation.setCost(operationCost);
        operationRepository.save(operation);
        return operation;
    }

    private UserRecord createUserRecord(CalcUser user, Operation operation, Double operationCost, String operationResponse) {
        UserRecord record = new UserRecord();
        record.setUser(user);
        record.setOperation(operation);
        record.setAmount(operationCost);
        record.setUserBalance(user.getBalance() - operationCost);
        record.setOperationResponse(operationResponse);
        record.setRecordDate(LocalDateTime.now());
        recordService.save(record);
        return record;
    }

    private UserRecordDto updateUserRecordDto(CalcUser user, Operation operation, Double operationCost, String operationResponse) {
        UserRecordDto userRecordDto = new UserRecordDto();
        userRecordDto.setId(user.getId());
        userRecordDto.setUserName(user.getUserName());
        userRecordDto.setOperationId(operation.getId());
        userRecordDto.setAmount(operationCost);
        userRecordDto.setUserBalance(user.getBalance() - operationCost);
        userRecordDto.setOperationResponse(operationResponse);
        userRecordDto.setRecordDate(LocalDateTime.now());
        return userRecordDto;
    }


}



