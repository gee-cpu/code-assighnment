package com.gonchaba.codeassighnment.controllers;

import com.gonchaba.codeassighnment.config.OperationConfig;
import com.gonchaba.codeassighnment.domain.CalcUser;
import com.gonchaba.codeassighnment.domain.Operation;
import com.gonchaba.codeassighnment.domain.UserRecord;
import com.gonchaba.codeassighnment.dto.OperationDto;
import com.gonchaba.codeassighnment.dto.UserRecordDto;
import com.gonchaba.codeassighnment.enums.OperationType;
import com.gonchaba.codeassighnment.enums.Status;
import com.gonchaba.codeassighnment.services.OperationService;
import com.gonchaba.codeassighnment.services.UserRecordService;
import com.gonchaba.codeassighnment.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/operations")
@Slf4j
public class OperationController {


    private final OperationService operationService;
    private final UserService userService;

    private final OperationConfig operationConfig;
    private final UserRecordService recordService;

    public OperationController(OperationService operationService, UserService userService, OperationConfig operationConfig, UserRecordService recordService) {
        this.operationService = operationService;
        this.userService = userService;
        this.operationConfig = operationConfig;
        this.recordService = recordService;
    }

    @PostMapping("/operationTypes")
    public ResponseEntity<String> performOperation(@RequestParam String userName, @RequestBody OperationDto operationDto, @RequestParam OperationType operationType) {
        CalcUser user = userService.findByUserName(userName);

        if (user.getStatus() == Status.INACTIVE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User account is inactive");
        }

        Double operationCost = operationService.getOperationCost(operationType);

        if (user.getBalance() < operationCost) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient account balance");
        }

        Double result;
        String operationResponse;

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
                    return ResponseEntity.badRequest().body("Cannot divide by zero");
                }
                result = operationDto.getNum1() / operationDto.getNum2();
                break;
            case SQUARE_ROOT:
                if (operationDto.getNum1() < 0) {
                    return ResponseEntity.badRequest().body("Cannot take square root of negative number");
                }
                result = Math.sqrt(operationDto.getNum1());
                break;
            default:
                return ResponseEntity.badRequest().body("Unsupported operation type");
        }

        operationResponse = result.toString();

        Double newBalance = user.getBalance() - operationCost;
        user.setBalance(newBalance);
        userService.createUser(user);

        Operation operation = new Operation();
        operation.setType(operationType);
        operation.setCost(operationCost);
        operationService.save(operation);

        UserRecord record = new UserRecord();
        record.setUser(user);
        record.setOperation(operation);
        record.setAmount(operationCost);
        record.setUserBalance(newBalance);
        record.setOperationResponse(operationResponse);
        record.setRecordDate(LocalDateTime.now());
        recordService.save(record);

        return ResponseEntity.ok(operationResponse);
    }


    @PutMapping("/operationsUpdate")
    public ResponseEntity<String> performOperation(
            @RequestParam String userName,
            @RequestParam OperationType operationType,
            @RequestBody OperationDto operationDto) {

        CalcUser user = userService.findByUserName(userName);

        if (user.getStatus() != Status.ACTIVE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User account is inactive");
        }

        Double operationCost = operationService.getOperationCost(operationType);
        if (user.getBalance() < operationCost) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Insufficient account balance");
        }

        Double result;
        String operationResponse;
        switch (operationType) {
            case ADDITION:
                result = operationDto.getNum1() + operationDto.getNum2();
                operationResponse = result.toString();
                break;
            case SUBTRACTION:
                result = operationDto.getNum1() - operationDto.getNum2();
                operationResponse = result.toString();
                break;
            case MULTIPLICATION:
                result = operationDto.getNum1() * operationDto.getNum2();
                operationResponse = result.toString();
                break;
            case DIVISION:
                if (operationDto.getNum2() == 0) {
                    return ResponseEntity.badRequest().body("Cannot divide by zero");
                }
                result = operationDto.getNum1() / operationDto.getNum2();
                operationResponse = result.toString();
                break;

            default:
                return ResponseEntity.badRequest().body("Unsupported operation type");
        }

        Double newBalance = user.getBalance() - operationCost;
        user.setBalance(newBalance);
        userService.createUser(user);

        Operation operation = new Operation();
        operation.setType(operationType);
        operation.setCost(operationCost);
        operationService.save(operation);

        UserRecordDto userRecordDto = new UserRecordDto();
        userRecordDto.setId(user.getId());
        userRecordDto.setOperationId(operation.getId());
        userRecordDto.setAmount(operationCost);
        userRecordDto.setUserBalance(newBalance);
        userRecordDto.setOperationResponse(operationResponse);
        userRecordDto.setRecordDate(LocalDateTime.now());
        recordService.updateRecord(userRecordDto.getId(), userRecordDto);

        return ResponseEntity.ok(operationResponse);
    }

}

