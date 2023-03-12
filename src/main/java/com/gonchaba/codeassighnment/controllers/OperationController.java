package com.gonchaba.codeassighnment.controllers;

import com.gonchaba.codeassighnment.domain.CalcUser;
import com.gonchaba.codeassighnment.domain.UserRecord;
import com.gonchaba.codeassighnment.enums.OperationType;
import com.gonchaba.codeassighnment.services.OperationService;
import com.gonchaba.codeassighnment.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/operations")
public class OperationController {


    private final OperationService operationService;
    private final UserService userService;

    public OperationController(OperationService operationService, UserService userService) {
        this.operationService = operationService;
        this.userService = userService;
    }

    @PostMapping("/addition")
    public ResponseEntity<Double> addition(@RequestParam Double num1, @RequestParam Double num2, Principal principal) {
        CalcUser user = userService.findByUserName(principal.getName());
        double cost = operationService.getOperationCost(OperationType.ADDITION);
        if (user.getBalance() < cost) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        user.setBalance(user.getBalance() - cost);
        userService.save(user);
        double result = operationService.addition(num1, num2);
        UserRecord record = new UserRecord(OperationType.ADDITION, cost, result, user);
        operationService.saveRecord(record);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/subtraction")
    public ResponseEntity<Double> subtraction(@RequestParam Double num1, @RequestParam Double num2, Principal principal) {
        CalcUser user = userService.findByUserName(principal.getName());
        double cost = operationService.getOperationCost(OperationType.SUBTRACTION);
        if (user.getBalance() < cost) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        user.setBalance(user.getBalance() - cost);
        userService.save(user);
        double result = operationService.subtraction(num1, num2);
        UserRecord record = new UserRecord(OperationType.SUBTRACTION, cost, result, user);
        operationService.saveRecord(record);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/multiplication")
    public ResponseEntity<Double> multiplication(@RequestParam Double num1, @RequestParam Double num2, Principal principal) {
        CalcUser user = userService.findByUserName(principal.getName());
        double cost = operationService.getOperationCost(OperationType.MULTIPLICATION);
        if (user.getBalance() < cost) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        user.setBalance(user.getBalance() - cost);
        userService.save(user);
        double result = operationService.multiplication(num1, num2);
        UserRecord record = new UserRecord(OperationType.MULTIPLICATION, cost, result, user);
        operationService.saveRecord(record);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/division")
    public ResponseEntity<Double> division(@RequestParam Double num1, @RequestParam Double num2, Principal principal) {
        CalcUser user = userService.findByUserName(principal.getName());
        double cost = operationService.getOperationCost(OperationType.DIVISION);
        if (user.getBalance() < cost) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        user.setBalance(user.getBalance() - cost);
        userService.save(user);
        double result = operationService.division(num1, num2);
        UserRecord record = new UserRecord(OperationType.DIVISION, cost, result, user);
        operationService.saveRecord(record);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/square_root")
    public ResponseEntity<Double> squareRoot(@RequestParam Double num, Principal principal) {
        CalcUser user = userService.findByUserName(principal.getName());
        double cost = operationService.getOperationCost(OperationType.SQUARE_ROOT);
        if (user.getBalance() < cost) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        user.setBalance(user.getBalance() - cost);
        userService.save(user);
        double result = operationService.squareRoot(num);
        UserRecord record = new UserRecord(OperationType.SQUARE_ROOT, cost, result, user);
        operationService.saveRecord(record);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/random_string")
    public ResponseEntity<String> randomString(@RequestParam int length, Principal principal) {
        CalcUser user = userService.findByUserName(principal.getName());
        double cost = operationService.getOperationCost(OperationType.RANDOM_STRING);
        if (user.getBalance() < cost) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        user.setBalance(user.getBalance() - cost);
        userService.save(user);
        String result = operationService.randomString(length);
        UserRecord record = new UserRecord(OperationType.RANDOM_STRING, cost, result, user);
        operationService.saveRecord(record);
        return ResponseEntity.ok(result);
    }
}

