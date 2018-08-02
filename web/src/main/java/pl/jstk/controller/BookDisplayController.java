package pl.jstk.controller;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import pl.jstk.service.BookService;
import pl.jstk.to.BookTo;
import pl.jstk.to.UserTo;

@Controller
public class BookDisplayController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ModelAndView displayAllBooks() {
		ModelAndView displayBooks = new ModelAndView();

		return displayBooks.addObject("bookList", bookService.findAllBooks());

	}

	@RequestMapping(value = "/books/book{id}", method = RequestMethod.GET)
	public ModelAndView displayBookDetails(@RequestParam Long id) {
		ModelAndView newMav = new ModelAndView("book");
		return newMav.addObject("book", bookService.findBookById(id));

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/books/delete{id}", method = RequestMethod.GET)
	public ModelAndView deleteBook(@RequestParam Long id) {
		ModelAndView newMav = new ModelAndView("delete");

		bookService.deleteBook(id);
		return newMav;

	}

	// podlaczanie strony z formularza addBook
	@RequestMapping(value = "/books/add", method = RequestMethod.GET)
	public ModelAndView addBook() {
		ModelAndView newMav = new ModelAndView("addBook");
		return newMav.addObject("newBook", new BookTo());
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.POST)
	public ModelAndView createBook(@ModelAttribute("newBook") BookTo newBookTo) {

		if (newBookTo.getAuthors().isEmpty() && newBookTo.getTitle().isEmpty()) {
			ModelAndView newMav = new ModelAndView("403");
			return newMav;
		}
		ModelAndView newMav = new ModelAndView("congratulations");
		return newMav.addObject(bookService.saveBook(newBookTo));

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView showSearchButton() {
		ModelAndView newMav = new ModelAndView("search");
		return newMav.addObject("newBook", new BookTo());

	}

	@RequestMapping(value = "/search/bookdetails{authors}{title}", method = RequestMethod.GET)
	public ModelAndView searchByAuthorOrTitle(@RequestParam String authors, @RequestParam String title) {

		if (StringUtils.isEmpty(authors) && StringUtils.isEmpty(title)) {
			ModelAndView emptySearchView = new ModelAndView("emptySearch");
			return emptySearchView;
		/*} else if (!bookService.searchBooksByAuthorOrTitle(authors, title).contains(authors)
				|| !bookService.searchBooksByAuthorOrTitle(authors, title).contains(title)) {
			ModelAndView emptySearchView = new ModelAndView("noSuchBookToSearch");
			return emptySearchView;*/
		} else {
			ModelAndView newMaV = new ModelAndView("books");
			return newMaV.addObject("bookList", bookService.searchBooksByAuthorOrTitle(authors, title));
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage() {
		ModelAndView newMav = new ModelAndView("login");
		return newMav;

	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView showErrorPage() {
		ModelAndView newMav = new ModelAndView("403");
		newMav.addObject("error", "Access denied!");
		return newMav;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView showHomePageAfterLogin() {
		ModelAndView newMav = new ModelAndView("/");
		newMav.setViewName("redirect:/");
		return newMav;

	}
}
