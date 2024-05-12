package univie.pad.bms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univie.pad.bms.entities.Book;
import univie.pad.bms.services.BookService;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list"; // Assuming there's a Thymeleaf template named "list.html" in "books" directory
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id)));
        return "books/book"; // Assuming there's a Thymeleaf template named "detail.html" in "books" directory
    }

    @PostMapping("/")
    public String createBook(@ModelAttribute("book") Book book) {
        bookService.createBook(book);
        return "redirect:/books/";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable("id") String id, @ModelAttribute("book") Book book) {
        Long bookId;
        try {
            bookId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            // Handle conversion error
            return "redirect:/books";
        }
        book.setId(bookId); // Set the ID property of the Book object
        bookService.updateBook(bookId, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books/";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable long id,
                               Model model) throws Exception {
        Optional<Book> eBook = bookService.getBookById(id);
        if (eBook.isPresent()) {
            model.addAttribute("book", eBook.get());
            return "books/book-form";
        }
        throw new Exception("No such book id");
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/new-book";
    }
}