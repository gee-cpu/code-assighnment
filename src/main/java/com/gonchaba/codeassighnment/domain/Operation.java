package com.gonchaba.codeassighnment.domain;

import com.gonchaba.codeassighnment.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data

public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private OperationType type;
    private Double cost;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private CalcUser user;
    @OneToMany(mappedBy = "operation")
    private Set<UserRecord> records;
}
