package com.gonchaba.codeassighnment.repository;

import com.gonchaba.codeassighnment.domain.CalcUser;
import com.gonchaba.codeassighnment.domain.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {

    List<UserRecord> findByUserUserName(String userName);
}

