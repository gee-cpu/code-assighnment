package com.gonchaba.codeassighnment.repository;

import com.gonchaba.codeassighnment.domain.CalcUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CalcUser, Long> {
    CalcUser findByUserName(String userName);
}

