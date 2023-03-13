package com.gonchaba.codeassighnment.services;

import com.gonchaba.codeassighnment.domain.UserRecord;
import com.gonchaba.codeassighnment.dto.UserRecordDto;

import java.util.List;
import java.util.Optional;

public interface UserRecordService {
       List<UserRecord> getAllRecords();

       UserRecord save(UserRecord record);

       UserRecord getRecordById(Long recordId);

       UserRecord updateRecord(Long id, UserRecordDto record);
       void deleteRecord(Long id);


}
