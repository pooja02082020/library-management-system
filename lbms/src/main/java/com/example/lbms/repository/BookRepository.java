package com.example.lbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lbms.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	
}
