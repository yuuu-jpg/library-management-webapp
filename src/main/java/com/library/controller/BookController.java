package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private LoanService loanService;
    
    // 書籍一覧・検索画面
    @GetMapping
    public String listBooks(
            @RequestParam(value = "genre", required = false, defaultValue = "") String genre,
            @RequestParam(value = "year", required = false, defaultValue = "0") Integer year,
            @RequestParam(value = "status", required = false, defaultValue = "all") String status,
            Model model) {
        
        List<Book> books;
        
        // 検索条件に基づいて取得
        if (!genre.isEmpty() && year > 0) {
            books = bookService.searchByGenreAndYear(genre, year);
        } else if (!genre.isEmpty()) {
            books = bookService.searchByGenre(genre);
        } else if (year > 0) {
            books = bookService.searchByYear(year);
        } else {
            books = bookService.getAllBooks();
        }

        if ("available".equals(status)) {
            books = books.stream()
                    .filter(book -> loanService.getCurrentLoansByBookId(book.getBookId()).isEmpty())
                    .collect(Collectors.toList());
        } else if ("borrowed".equals(status)) {
            books = books.stream()
                    .filter(book -> !loanService.getCurrentLoansByBookId(book.getBookId()).isEmpty())
                    .collect(Collectors.toList());
        }
        
        model.addAttribute("books", books);
        model.addAttribute("selectedGenre", genre);
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedStatus", status);
        model.addAttribute("loanService", loanService);
        
        return "books/list";
    }
    
    // 書籍詳細画面
    @GetMapping("/{id}")
    public String detailBook(@PathVariable Integer id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        
        if (book.isEmpty()) {
            return "redirect:/books";
        }
        
        model.addAttribute("book", book.get());
        model.addAttribute("loans", loanService.getLoansByBookId(id));
        
        return "books/detail";
    }
}
