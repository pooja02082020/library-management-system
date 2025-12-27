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

/**
 * REST Controller for managing Book-related operations.
 * Handles HTTP requests for creating, retrieving, updating, and deleting books.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

	// Service layer dependency for business logic
	private final BookService service;

	// Constructor-based dependency injection
	public BookController(BookService service) {
		this.service = service;
	}

	/**
	 * Create a new Book
	 * URL: POST /api/books
	 * @param product Book object received in request body
	 * @return created Book with HTTP 201 status
	 */
	@PostMapping
	public ResponseEntity<Book> create(@Valid @RequestBody Book product) {
		Book created = service.create(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	/**
	 * Get a Book by ID
	 * URL: GET /api/books/{id}
	 * @param id Book ID
	 * @return Book object
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Book> get(@PathVariable int id) {
		return ResponseEntity.ok(service.getById(id));
	}

	/**
	 * Get all Books without pagination
	 * URL: GET /api/books
	 * @return list of all books
	 */
	@GetMapping
	public ResponseEntity<List<Book>> all() {
		return ResponseEntity.ok(service.getAll());
	}

	/**
	 * Get all Books with pagination and sorting
	 * URL: GET /api/books/page
	 * Query Params:
	 *  - page: page number (default 0)
	 *  - size: number of records per page (default 10)
	 *  - sortBy: field to sort by (default id)
	 *  - order: asc or desc (default asc)
	 *
	 * @return paginated list of books
	 */
	@GetMapping("/page")
	public ResponseEntity<Page<Book>> all(
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "10") int size,
	    @RequestParam(defaultValue = "id") String sortBy,
	    @RequestParam(defaultValue = "asc") String order) {

	    // Determine sorting order
	    Sort sort = order.equalsIgnoreCase("asc")
	            ? Sort.by(sortBy).ascending()
	            : Sort.by(sortBy).descending();

	    // Create Pageable object
	    Pageable pageable = PageRequest.of(page, size, sort);

	    // Fetch paginated data from service
	    Page<Book> pageResult = service.getAll(pageable);

	    return ResponseEntity.ok(pageResult);
	}

	/**
	 * Update a Book by ID
	 * URL: PUT /api/books/{id}
	 * @param id Book ID
	 * @param product updated Book data
	 * @return updated Book
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Book> update(@PathVariable int id, @Valid @RequestBody Book product) {
		return ResponseEntity.ok(service.update(id, product));
	}

	/**
	 * Delete a Book by ID
	 * URL: DELETE /api/books/{id}
	 * @param id Book ID
	 * @return HTTP 204 No Content
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
