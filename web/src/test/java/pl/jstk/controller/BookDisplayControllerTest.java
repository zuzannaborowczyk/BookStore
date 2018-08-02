package pl.jstk.controller;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.jstk.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDisplayControllerTest {
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	@Mock
	BookService bookservice;
	
	@Autowired
	BookDisplayController bookDisplayController;
	
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new BookDisplayController()).build();
        MockitoAnnotations.initMocks(bookservice);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        ReflectionTestUtils.setField(bookDisplayController, "bookService", bookservice);
    }
    
    // apply(springSecurity()) - do mockowania userow
    @Test
    public void shouldDisplayBooksPage() throws Exception {
        // given when
        ResultActions resultActions = mockMvc.perform(get("/books"));
        // then
        resultActions.andExpect(status().isOk())
                     .andExpect(view().name("books"));
    }
    
    @Test
    public void shouldDisplayBookDetails() throws Exception {
    	// given when
    	ResultActions resultActions = mockMvc.perform(get("/books/book"));
    }
    @Test
    public void shouldDeleteBookIfActionIsExecutedByAdmin() throws Exception {
    	// given when
    	ResultActions resultActions = mockMvc.perform(get("/books/book"));
    }
    @Test
    public void shouldAddBook() throws Exception {
    	// given when
    	ResultActions resultActions = mockMvc.perform(get("/books/book"));
    }
    @Test
    public void shouldDisplayErrorPageIfUserWantsToDeleteBook() throws Exception {
    	// given when
    	ResultActions resultActions = mockMvc.perform(get("/books/book"));
    }
    @Test
    public void shouldDisplayMessegeAfterAddingBook() throws Exception {
    	// given when
    	ResultActions resultActions = mockMvc.perform(get("/books/book"));
    }
    @Test
    public void shouldDisplayErrorPageWhileAddingBookWithEmptyFields() throws Exception {
    	// given when
    	ResultActions resultActions = mockMvc.perform(get("/books/book"));
    }
    @Test
    public void shouldDisplayBookListAfterSearchingByExistingTitle() throws Exception {
    	// given when
    	ResultActions resultActions = mockMvc.perform(get("/books/book"));
    }
    @Test
    public void shouldDisplayErrorPageAfterSearchingNonExistingBook() throws Exception {
    	// given when
    	ResultActions resultActions = mockMvc.perform(get("/books/book"));
    }
    @Test
    public void shouldDisplayHomePageAfterSuccessfulLogIn() throws Exception {
    	// given when
    	ResultActions resultActions = mockMvc.perform(get("/books/book"));
    }
    @Test
    public void shouldNotDisplayLogOutButtonAfterLoggingOut() throws Exception {
    	// given when
    	ResultActions resultActions = mockMvc.perform(get("/books/book"));
    }
    @Test
    public void shouldDisplayErrorAfterLoggingInWithWrongPassword() throws Exception {
    	// given when
    	ResultActions resultActions = mockMvc.perform(get("/books/book"));
    }

    
}
