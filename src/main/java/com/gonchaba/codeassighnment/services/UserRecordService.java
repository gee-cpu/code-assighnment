package com.gonchaba.codeassighnment.services;

import com.gonchaba.codeassighnment.domain.UserRecord;
import com.gonchaba.codeassighnment.dto.UserRecordDto;

import java.util.List;
import java.util.Optional;

public interface UserRecordService {
       List<UserRecord> getAllRecords();
       List<UserRecord> getRecordsByUserName(String userName);


       UserRecord createRecord(UserRecord record);

       UserRecord updateRecord(Long id, UserRecordDto record);

       void deleteRecord(Long id);


}
