package univie.pad.bms.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import univie.pad.bms.entities.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void createBook_ValidBook_SuccessfullyAddsToDatabase() {

        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");

        Book createdBook = bookService.createBook(book);

        assertNotNull(createdBook.getId());
        assertEquals(book.getTitle(), createdBook.getTitle());
        assertEquals(book.getAuthor(), createdBook.getAuthor());
    }

    @Test
    void updateBook_ValidBook_SuccessfullyUpdatesDatabase() {

        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book = bookService.createBook(book); // Create the book first
        book.setTitle("New Title");
        book.setAuthor("New Author");

        Book updatedBook = bookService.updateBook(book.getId(), book);

        assertEquals(book.getId(), updatedBook.getId());
        assertEquals(book.getTitle(), updatedBook.getTitle());
        assertEquals(book.getAuthor(), updatedBook.getAuthor());
    }

    @Test
    void deleteBook_ValidBookId_SuccessfullyDeletesFromDatabase() {
        int booksCount = bookService.getAllBooks().size();

        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book = bookService.createBook(book);
        assertEquals(booksCount, bookService.getAllBooks().size() - 1);

        bookService.deleteBook(book.getId());

        assertEquals(booksCount, bookService.getAllBooks().size());
    }
}