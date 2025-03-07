package com.springboot08.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot08.model.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
    // JpaRepository provides basic CRUD operations automatically
    // We can add custom methods here if needed
}