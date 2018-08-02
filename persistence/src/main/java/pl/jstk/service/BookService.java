package pl.jstk.service;

import java.util.List;

import pl.jstk.to.BookTo;

public interface BookService {

    List<BookTo> findAllBooks();
    List<BookTo> findBooksByTitle(String title);
    List<BookTo> findBooksByAuthor(String author);
    BookTo findBookById(Long id);
    BookTo saveBook(BookTo book);
    void deleteBook(Long id);
    List<BookTo> searchBooksByAuthorOrTitle(String author, String title);
}
