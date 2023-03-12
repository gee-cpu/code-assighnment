package com.gonchaba.codeassighnment.repository;

import com.gonchaba.codeassighnment.domain.Operation;
import com.gonchaba.codeassighnment.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}

