package com.gonchaba.codeassighnment.controllers;

import com.gonchaba.codeassighnment.domain.Operation;
import com.gonchaba.codeassighnment.dto.OperationDto;
import com.gonchaba.codeassighnment.dto.OperationResult;
import com.gonchaba.codeassighnment.dto.UserRecordDto;
import com.gonchaba.codeassighnment.enums.OperationType;
import com.gonchaba.codeassighnment.services.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.*;

@RestController
@RequestMapping("/operations")
@Slf4j
public class OperationController {

    private final OperationService operationService;


    public OperationController(OperationService operationService) {
        this.operationService = operationService;

    }

    @GetMapping
    public List<Operation> getAllOperations() {
        return operationService.getAllOperations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable Long id) {
        Optional<Operation> operation = operationService.getOperationById(id);
        return operation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/operationTypes")
    public ResponseEntity<Map<String, Object>> performOperation(@RequestParam String userName, @RequestBody OperationDto operationDto, @RequestParam OperationType operationType) {
        try {
            OperationResult operationResult = operationService.performOperation(userName, operationDto, operationType, new UserRecordDto());
            String operationResponse = operationResult.getOperationResponse();
            Double updatedBalance = operationResult.getUpdatedBalance();

            Map<String, Object> response = new HashMap<>();
            response.put("operationResponse", operationResponse);
            response.put("updatedBalance", updatedBalance);

            return ResponseEntity.ok(response);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "User account does not exist"));
        } catch (UnknownError e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Insufficient account balance"));
        } catch (InvalidParameterException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Cannot perform operation. Check your user balance or ensure your account is activated"));
        }
    }

    @PutMapping("/operationsUpdate")
    public ResponseEntity<OperationResult> performOperation(
            @RequestParam("userName") String userName,
            @RequestParam("operationType") OperationType operationType,
            @RequestBody OperationDto operationDto,
            @RequestBody UserRecordDto userRecordDto) {

        try {
            OperationResult result = operationService.performOperation(userName, operationDto, operationType, new UserRecordDto());
            return ResponseEntity.ok(result);
        } catch (InvalidParameterException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOperation(@PathVariable Long id) {
        operationService.deleteOperation(id);
        return ResponseEntity.ok("Operation with id " + id + " has been deleted successfully.");
    }
}




