package com.library.repository;

import com.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    
    // すべての会員を取得
    @Query("SELECT m FROM Member m ORDER BY m.name")
    List<Member> findAllMembers();
}
