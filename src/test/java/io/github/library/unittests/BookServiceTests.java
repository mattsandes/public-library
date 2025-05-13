package io.github.library.unittests;

import io.github.library.model.Book;
import io.github.library.repositories.BookRepository;
import io.github.library.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTests {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	private Book book;

	@BeforeEach
	void setUp() {
		bookService = new BookService(bookRepository);

		book = new Book(
				UUID.randomUUID(),
				"Test Title",
				"Test subtitle",
				"Test Author",
				"Test Editora",
				LocalDate.of(2024, 5, 10)
		);
	}

	@Test
	@DisplayName("Validar que é possivel criar recuros com dados validos")
	void givenValidBook_whenCreate_thenBookIsSavedSuccessfully() {
		given(bookRepository.save(book)).willReturn(book);

		var createBook = bookService.create(book);

		assertNotNull(createBook);
		assertNotNull(createBook.getBookId());
		assertEquals("Test Title", createBook.getTitle());
		assertEquals("Test subtitle", createBook.getSubtitle());
		assertEquals("Test Author", createBook.getAuthors());
		assertEquals("Test Editora", createBook.getPublisher());
		assertEquals(LocalDate.of(2024, 5, 10), createBook.getPublishDate());
	}

	@Test
	@DisplayName("Validar que é possivel buscar por itens salvos")
	void givenCreatedBook_whenFindAll_thenCreateBookIsReturned() {
		Book anotherBook = new Book(
				UUID.randomUUID(),
				"Título 2",
				"Subtítulo 2",
				"Test editor",
				"Editora B",
				LocalDate.of(2021, 6, 15)
		);

		List<Book> books = List.of(book, anotherBook);

		given(bookRepository.findAll()).willReturn(books);

		List<Book> bookList = bookService.findAll();

		assertNotNull(bookList);
		assertEquals(2, bookList.size());
		assertNotNull(bookList.getFirst().getBookId());
	}

	@Test
	@DisplayName("Validar que é possivel encontrar livros por seus ids")
	void givenCreatedBook_whenFindById_thenCreatedBookIsReturned() {
		given(bookRepository.save(book)).willReturn(book);
		given(bookRepository.findById(book.getBookId())).willReturn(Optional.of(book));

		var createdBook = bookService.create(book);

		var foundBook = bookService.findById(book.getBookId());

		assertNotNull(foundBook);
		assertEquals(createdBook, book);
	}

	@Test
	@DisplayName("Validar que é possivel atualizar dados de livros")
	void givenCreatedBook_whenUpdate_thenCreatedBookDataIsUpdated() {
		given(bookRepository.findById(book.getBookId())).willReturn(Optional.ofNullable(book));
		given(bookRepository.save(book)).willReturn(book);

		var createdBook = bookService.create(book);

		createdBook.setTitle("Updated Test Title");

		var updateBook = bookService.update(createdBook);

		assertEquals("Updated Test Title", updateBook.getTitle());
	}

	@Test
	@DisplayName("Validar que é possivel apgar um obejto pelo id")
	void givenCreatedBook_whenDeleteById_thenGivenBookWasDeleted() {
		given(bookRepository.save(book)).willReturn(book);

		willDoNothing().given(bookRepository).deleteById(book.getBookId());

		var createBook = bookService.create(book);

		bookService.deleteById(createBook.getBookId());

		verify(bookRepository, times(1)).deleteById(createBook.getBookId());
	}
}
