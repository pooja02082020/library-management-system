/*
 * package com.example.lbms;
 * 
 * import org.junit.jupiter.api.Test; import org.mockito.Mockito; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
 * import org.springframework.http.MediaType; import
 * org.springframework.test.context.bean.override.mockito.MockitoBean; import
 * org.springframework.test.web.servlet.MockMvc;
 * 
 * import com.example.lbms.service.BookService;
 * 
 * import tools.jackson.databind.ObjectMapper;
 * 
 * import com.example.lbms.exception.ResourceNotFoundException; import
 * com.example.lbms.model.*; import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import
 * static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 * import static org.hamcrest.Matchers.*;
 * 
 * @SpringBootTest
 * 
 * @AutoConfigureMockMvc class BookControllerTest {
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @Autowired private ObjectMapper objectMapper; // from
 * spring-boot-starter-json
 * 
 * @MockitoBean private BookService bookService; // mock service layer
 * 
 * @Test void createBook_returnsCreated() throws Exception { Book request = new
 * Book(1, "Java", "John", 300, 5); Book saved = new Book(1,"Java", "John", 300,
 * 5); saved.setId(1);
 * 
 * Mockito.when(bookService.create(Mockito.any(Book.class))).thenReturn(saved);
 * 
 * mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
 * .content(objectMapper.writeValueAsString(request))).andExpect(status().
 * isCreated())
 * .andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.title").value(
 * "Java")); }
 * 
 * 
 * @Test void getBook_whenNotFound_returns404() throws Exception {
 * Mockito.when(bookService.getById(99)).thenThrow(new
 * ResourceNotFoundException("Book 99 not found"));
 * 
 * mockMvc.perform(get("/api/books/99")).andExpect(status().isNotFound())
 * .andExpect(jsonPath("$.message").value("Book 99 not found")); } }
 */