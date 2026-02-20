package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    
    // ジャンルで検索
    List<Book> findByGenre(String genre);
    
    // ジャンルと出版年で検索
    @Query("SELECT b FROM Book b WHERE b.genre = :genre AND b.publishedYear >= :year ORDER BY b.title")
    List<Book> findByGenreAndYear(@Param("genre") String genre, @Param("year") Integer year);
    
    // 出版年で検索
    @Query("SELECT b FROM Book b WHERE b.publishedYear >= :year ORDER BY b.title")
    List<Book> findByYear(@Param("year") Integer year);
    
    // タイトルで検索（部分一致）
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword% ORDER BY b.title")
    List<Book> findByTitleContaining(@Param("keyword") String keyword);
    
    // すべての書籍を取得
    @Query("SELECT b FROM Book b ORDER BY b.title")
    List<Book> findAllBooks();
}
