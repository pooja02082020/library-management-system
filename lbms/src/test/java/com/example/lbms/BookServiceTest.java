/*
 * package com.example.lbms;
 * 
 * import java.util.Optional;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks;
 * import org.mockito.Mock; import org.mockito.Mockito;
 * 
 * import com.example.lbms.model.Book; import
 * com.example.lbms.repository.BookRepository; import
 * com.example.lbms.service.BookService; import
 * com.example.lbms.exception.ResourceNotFoundException;
 * 
 * import static org.junit.jupiter.api.Assertions.*; import
 * org.mockito.junit.jupiter.MockitoExtension;
 * 
 * @ExtendWith(MockitoExtension.class) class BookServiceTest {
 * 
 * @Mock private BookRepository repo;
 * 
 * @InjectMocks private BookService service;
 * 
 * @Test void create_savesBook() { Book b = new Book(1, "Java", "John", 300, 5);
 * Book saved = new Book(1, "Java", "John", 300, 5); saved.setId(1);
 * 
 * Mockito.when(repo.save(b)).thenReturn(saved);
 * 
 * Book result = service.create(b);
 * 
 * assertEquals(1, result.getId()); Mockito.verify(repo).save(b); }
 * 
 * @Test void getById_whenMissing_throws() {
 * Mockito.when(repo.findById(99)).thenReturn(Optional.empty());
 * assertThrows(ResourceNotFoundException.class, () -> service.getById(99)); } }
 */