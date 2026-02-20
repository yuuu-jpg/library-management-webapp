package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    // すべての書籍を取得
    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }
    
    // 書籍をIDで取得
    public Optional<Book> getBookById(Integer bookId) {
        return bookRepository.findById(bookId);
    }
    
    // ジャンルで検索
    public List<Book> searchByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
    
    // ジャンルと出版年で検索
    public List<Book> searchByGenreAndYear(String genre, Integer year) {
        return bookRepository.findByGenreAndYear(genre, year);
    }
    
    // 出版年で検索
    public List<Book> searchByYear(Integer year) {
        return bookRepository.findByYear(year);
    }
    
    // タイトルで検索
    public List<Book> searchByTitle(String keyword) {
        return bookRepository.findByTitleContaining(keyword);
    }
}
