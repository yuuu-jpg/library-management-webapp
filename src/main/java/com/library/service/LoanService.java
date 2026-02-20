package com.library.service;

import com.library.model.Loan;
import com.library.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LoanService {
    
    @Autowired
    private LoanRepository loanRepository;
    
    // 現在貸出中の一覧を取得
    public List<Loan> getCurrentLoans() {
        return loanRepository.findCurrentLoans();
    }
    
    // 特定の書籍の貸出履歴を取得
    public List<Loan> getLoansByBookId(Integer bookId) {
        return loanRepository.findLoansByBookId(bookId);
    }

    // 特定の書籍の現在貸出を取得
    public List<Loan> getCurrentLoansByBookId(Integer bookId) {
        return loanRepository.findCurrentLoansByBookId(bookId);
    }
    
    // 特定の会員の貸出履歴を取得
    public List<Loan> getLoansByMemberId(Integer memberId) {
        return loanRepository.findLoansByMemberId(memberId);
    }
    
    // 返却期限を過ぎているか判定
    public boolean isOverdue(String dueDate) {
        if (dueDate == null || dueDate.isEmpty()) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate due = LocalDate.parse(dueDate, formatter);
            return due.isBefore(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }
}
