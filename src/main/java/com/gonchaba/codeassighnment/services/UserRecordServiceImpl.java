package com.gonchaba.codeassighnment.services;

import com.gonchaba.codeassighnment.domain.UserRecord;
import com.gonchaba.codeassighnment.dto.UserRecordDto;
import com.gonchaba.codeassighnment.repository.UserRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRecordServiceImpl implements UserRecordService {


    private final UserRecordRepository recordRepository;


    public UserRecordServiceImpl(UserRecordRepository recordRepository) {
        this.recordRepository = recordRepository;

    }

    @Override
    public List<UserRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public UserRecord save(UserRecord record) {
        return recordRepository.save(record);
    }

    public UserRecord getRecordById(Long recordId) {
        return recordRepository.findById(recordId).orElse(null);


    }

    @Override
    public UserRecord updateRecord(Long id, UserRecordDto record) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        UserRecord existingRecord = recordRepository.findById(id).orElse(null);
        if (existingRecord == null) {
            throw new IllegalArgumentException("Record with id " + id + " does not exist");
        }
        existingRecord.setRecordDate(record.getRecordDate());
        existingRecord.setAmount(record.getAmount());
        existingRecord.setUserBalance(record.getUserBalance());
        existingRecord.setOperationResponse(record.getOperationResponse());
        return recordRepository.save(existingRecord);
    }

    @Override
    public void deleteRecord(Long id) {

        UserRecord deletedRecord = recordRepository.findById(id)
                .orElse(null);
        recordRepository.delete(deletedRecord);
    }


}

