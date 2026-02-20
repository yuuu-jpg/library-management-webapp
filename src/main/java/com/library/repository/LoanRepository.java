package com.library.repository;

import com.library.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    
    // 現在貸出中の一覧（returnedAt is null）
    @Query("SELECT l FROM Loan l WHERE l.returnedAt IS NULL ORDER BY l.dueDate")
    List<Loan> findCurrentLoans();
    
    // 特定の書籍の貸出履歴
    @Query("SELECT l FROM Loan l WHERE l.bookId = :bookId ORDER BY l.loanedAt DESC")
    List<Loan> findLoansByBookId(@Param("bookId") Integer bookId);

    // 特定の書籍の現在貸出（returnedAt is null）
    @Query("SELECT l FROM Loan l WHERE l.bookId = :bookId AND l.returnedAt IS NULL")
    List<Loan> findCurrentLoansByBookId(@Param("bookId") Integer bookId);
    
    // 特定の会員の貸出履歴
    @Query("SELECT l FROM Loan l WHERE l.memberId = :memberId ORDER BY l.loanedAt DESC")
    List<Loan> findLoansByMemberId(@Param("memberId") Integer memberId);
}
