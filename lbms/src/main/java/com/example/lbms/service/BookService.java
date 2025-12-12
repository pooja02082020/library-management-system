package com.example.lbms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lbms.exception.ResourceNotFoundException;
import com.example.lbms.model.Book;
import com.example.lbms.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository repo;

	public BookService(BookRepository repo) {
		this.repo = repo;
	}

	public Book create(Book b) {
		return repo.save(b);

	}

	public List<Book> getAll() {
		return repo.findAll();

	}

	public Book getById(int id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book " + id + " not found"));

	}
	
	public Book update(int id,Book b) {
		Book existing = getById(id);
		existing.setTitle(b.getTitle());
		existing.setAuthor(b.getAuthor());
		existing.setPages(b.getPages());
		existing.setAvaialableCopies(b.getAvaialableCopies());
		return repo.save(existing);
	}
	
	public void delete(int id) {
		
		if(!repo.existsById(id)) throw new ResourceNotFoundException("Book " + id + " not found");
		repo.deleteById(id);
	}

}
