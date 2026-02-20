package com.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanId;

    private Integer bookId;

    private Integer memberId;

    @Column(name = "loaned_at")
    private LocalDate loanedAt;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "returned_at")
    private LocalDate returnedAt;
}
