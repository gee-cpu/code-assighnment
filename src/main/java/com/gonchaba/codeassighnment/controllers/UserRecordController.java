package com.gonchaba.codeassighnment.controllers;

import com.gonchaba.codeassighnment.domain.UserRecord;
import com.gonchaba.codeassighnment.dto.UserRecordDto;
import com.gonchaba.codeassighnment.services.UserRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class UserRecordController {


    private final UserRecordService userRecordService;

    public UserRecordController(UserRecordService userRecordService) {
        this.userRecordService = userRecordService;
    }

    @GetMapping
    public List<UserRecord> getAllRecords() {
        return userRecordService.getAllRecords();
    }

    @PutMapping("/{id}")
    public UserRecord updateRecord(@PathVariable Long id, @RequestBody UserRecordDto record) {
        return userRecordService.updateRecord(id, record);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        userRecordService.deleteRecord(id);
    }
}
