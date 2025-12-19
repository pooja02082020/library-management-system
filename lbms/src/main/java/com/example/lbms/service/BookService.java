package com.example.lbms.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@CacheEvict(value = "booksCache", allEntries = true)
	public Book create(Book b) {
		return repo.save(b);

	}

	@Cacheable(value = "booksCache")
	public List<Book> getAll() {
		return repo.findAll();

	}

	@Cacheable(value = "booksCache")
	public Page<Book> getAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	public Book getById(int id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book " + id + " not found"));

	}

	@CacheEvict(value = "booksCache", allEntries = true)
	public Book update(int id, Book b) {
		Book existing = getById(id);
		existing.setTitle(b.getTitle());
		existing.setAuthor(b.getAuthor());
		existing.setPages(b.getPages());
		existing.setAvaialableCopies(b.getAvaialableCopies());
		return repo.save(existing);
	}

	@CacheEvict(value = "booksCache", allEntries = true)
	public void delete(int id) {

		if (!repo.existsById(id))
			throw new ResourceNotFoundException("Book " + id + " not found");
		repo.deleteById(id);
	}

}
