package com.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 200, unique = true)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(name = "joined_at")
    private LocalDate joinedAt;
}
