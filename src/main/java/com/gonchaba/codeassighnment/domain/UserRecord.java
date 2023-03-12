package com.gonchaba.codeassighnment.domain;

import com.gonchaba.codeassighnment.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
public class UserRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_id",nullable = false)
    private Operation operation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private CalcUser user;


    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double userBalance;

    @Column(nullable = false)
    private String operationResponse;

    @Column(nullable = false)
    private LocalDateTime recordDate;


    public UserRecord(OperationType multiplication, double cost, double result, CalcUser user) {
    }

    public UserRecord(OperationType randomString, double cost, String result, CalcUser user) {
    }
}
