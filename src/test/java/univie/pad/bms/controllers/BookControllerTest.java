package univie.pad.bms.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import univie.pad.bms.entities.Book;
import univie.pad.bms.services.BookService;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BookControllerTest {
    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks() {
        // Mock data
        when(bookService.getAllBooks()).thenReturn(List.of(new Book(), new Book()));

        // Invoke controller method
        String viewName = bookController.getAllBooks(model);

        // Verify interactions
        verify(bookService, times(1)).getAllBooks();
        verify(model, times(1)).addAttribute(eq("books"), anyList());

        // Check result
        assertEquals("books/list", viewName);
    }

    @Test
    void getBookById() {
        // Mock data
        Book book = new Book();
        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));

        // Invoke controller method
        String viewName = bookController.getBookById(1L, model);

        // Verify interactions
        verify(bookService, times(1)).getBookById(1L);
        verify(model, times(1)).addAttribute(eq("book"), eq(book));

        // Check result
        assertEquals("books/book", viewName);
    }

    @Test
    void createBook() {
        // Arrange
        Book book = new Book();

        // Act
        String viewName = bookController.createBook(book);

        // Assert
        assertEquals("redirect:/books/", viewName);
        verify(bookService, times(1)).createBook(book);
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void showEditForm_ValidBookId_ReturnsBookForm() throws Exception {
        // Arrange
        long bookId = 1L;
        Book expectedBook = new Book();
        expectedBook.setId(bookId);
        expectedBook.setTitle("Title");
        expectedBook.setAuthor("Author");
        when(bookService.getBookById(bookId)).thenReturn(Optional.of(expectedBook));
        Model model = mock(Model.class);

        // Act
        String viewName = bookController.showEditForm(bookId, model);

        // Assert
        assertEquals("books/book-form", viewName);
        verify(model, times(1)).addAttribute(eq("book"), eq(expectedBook));
    }

    @Test
    void showEditForm_InvalidBookId_ThrowsException() {
        // Arrange
        long invalidBookId = 999L;
        when(bookService.getBookById(invalidBookId)).thenReturn(Optional.empty());
        Model model = mock(Model.class);

        // Act and Assert
        assertThrows(Exception.class, () -> bookController.showEditForm(invalidBookId, model));
    }

    @Test
    public void testShowAddForm() {
        Model model = mock(Model.class);

        String viewName = bookController.showAddForm(model);

        assertEquals("books/new-book", viewName);
        verify(model, times(1)).addAttribute(eq("book"), any(Book.class));
    }
}