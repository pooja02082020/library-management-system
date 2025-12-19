package com.example.lbms.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
 
	//for post
	@PostMapping
	public ResponseEntity<Book> create(@Valid @RequestBody Book product) {
		Book created = service.create(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
 
	//FOR GETTING BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Book> get(@PathVariable int id) {
		return ResponseEntity.ok(service.getById(id));
	}
 
	//gETTING ALL 
	@GetMapping
	public ResponseEntity<List<Book>> all() {
		return ResponseEntity.ok(service.getAll());
	}
	
	//ADDED PAGINATION
	@GetMapping("/page")
	public ResponseEntity<Page<Book>> all(
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "10") int size,
	    @RequestParam(defaultValue = "id") String sortBy,
	    @RequestParam(defaultValue = "asc") String order) {

	    Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
	    Pageable pageable = PageRequest.of(page, size, sort);
	    Page<Book> pageResult = service.getAll(pageable);
	    return ResponseEntity.ok(pageResult);
	}
	
	//UPDATING BY ID
	@PutMapping("/{id}")
	public ResponseEntity<Book> update(@PathVariable int id, @Valid @RequestBody Book product) {
		return ResponseEntity.ok(service.update(id, product));
	}
 
	//DELETING BY ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}