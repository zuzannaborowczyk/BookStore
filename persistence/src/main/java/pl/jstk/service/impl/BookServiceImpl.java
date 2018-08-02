package pl.jstk.service.impl;



import java.util.List;
import java.util.stream.Collectors;

import pl.jstk.entity.BookEntity;
import pl.jstk.mapper.BookMapper;
import pl.jstk.repository.BookRepository;
import pl.jstk.service.BookService;
import pl.jstk.to.BookTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookTo> findAllBooks() {
        return BookMapper.map2To(bookRepository.findAll());
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
        return BookMapper.map2To(bookRepository.findBookByTitle(title));
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
        return BookMapper.map2To(bookRepository.findBookByAuthor(author));
    }

    @Override
    @Transactional
    public BookTo saveBook(BookTo book) {
        BookEntity entity = BookMapper.map(book);
        entity = bookRepository.save(entity);
        return BookMapper.map(entity);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);

    }


	@Override
	public BookTo findBookById(Long id) {
		BookEntity entity = bookRepository.findBookById(id);
		
		return BookMapper.map(entity);
	}

	@Override
	public List<BookTo> searchBooksByAuthorOrTitle(String author, String title) {
		List<BookTo> books = BookMapper.map2To(bookRepository.findAll());
		return books
				.stream()
                .filter(e -> StringUtils.isEmpty(author) ||e.getAuthors().equals(author))
                .filter(e -> StringUtils.isEmpty(title) || e.getTitle().equals(title))
                .collect(Collectors.toList());

	}
}
