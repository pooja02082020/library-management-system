package com.example.lbms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lbms.model.Book;
import com.example.lbms.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {



 
	private final BookService service;
 
	public BookController(BookService service) {
		this.service = service;
	}
 
	@PostMapping
	public ResponseEntity<Book> create(@Valid @RequestBody Book product) {
		Book created = service.create(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
 
	@GetMapping("/{id}")
	public ResponseEntity<Book> get(@PathVariable int id) {
		return ResponseEntity.ok(service.getById(id));
	}
 
	@GetMapping
	public ResponseEntity<List<Book>> all() {
		return ResponseEntity.ok(service.getAll());
	}
 
	@PutMapping("/{id}")
	public ResponseEntity<Book> update(@PathVariable int id, @Valid @RequestBody Book product) {
		return ResponseEntity.ok(service.update(id, product));
	}
 
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}