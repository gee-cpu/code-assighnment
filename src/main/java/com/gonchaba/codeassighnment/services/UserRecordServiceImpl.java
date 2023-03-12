package com.gonchaba.codeassighnment.services;

import com.gonchaba.codeassighnment.domain.UserRecord;
import com.gonchaba.codeassighnment.dto.UserRecordDto;
import com.gonchaba.codeassighnment.enums.OperationType;
import com.gonchaba.codeassighnment.repository.UserRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<UserRecord> getRecordsByUserName(String userName) {
        return recordRepository.findByUserUserName(userName);
    }

    @Override
    public UserRecord createRecord(UserRecord record) {
        return recordRepository.save(record);
    }

    @Override
    public UserRecord updateRecord(Long id, UserRecordDto record) {
        UserRecord existingRecord = recordRepository.findById(id).orElse(null);
        assert existingRecord != null;
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

