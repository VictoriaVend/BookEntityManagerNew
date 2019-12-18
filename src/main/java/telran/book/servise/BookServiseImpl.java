package telran.book.servise;

import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.book.dao.AutorRepository;
import telran.book.dao.BookRepository;
import telran.book.dao.PublisherRepository;
import telran.book.dto.AuthorDto;
import telran.book.dto.BookDto;
import telran.book.model.Author;
import telran.book.model.Book;
import telran.book.model.Publisher;

@Service
public class BookServiseImpl implements BookServise {
	@Autowired
	PublisherRepository publisherRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	AutorRepository authorRepository;

	@Override
	@Transactional
	public boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		//Publisher
		String publisherName = bookDto.getPublisher();
		Publisher publisher = publisherRepository.findById(publisherName)
				.orElseGet(() -> publisherRepository.save(new Publisher(publisherName)));
		//Set<Author>
		Set<AuthorDto> authorDtos = bookDto.getAuthors();
		Set<Author> authors = authorDtos.stream()
				.map(a -> authorRepository.findById(a.getName()).orElseGet(() -> authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet());
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors,
				publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	public BookDto findBookByIsbn(long isbn) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if (book == null) {
			return null;
		}
		return bookToBookDto(book);
	}

	@Override
	@Transactional
	public BookDto removeBook(long isbn) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if (book == null) {
			return null;
		}
		bookRepository.deleteById(book);
		return bookToBookDto(book);
	}
	
	private BookDto bookToBookDto(Book book) {
		Set<AuthorDto> authors = book.getAuthors().stream()
				.map(this::authorToAuthorDto)
				.collect(Collectors.toSet());
		return new BookDto(book.getIsbn(), book.getTitle(), authors, book.getPublisher().getPublisherName());
	}
	
	private AuthorDto authorToAuthorDto(Author author) {
		return new AuthorDto(author.getName(), author.getBirthDate());
	}

	@Override
	public Iterable<BookDto> findBooksByAuthor(String authorName) {
		return bookRepository.findBooksByAuthorsName(authorName)
				.stream()
				.map(this::bookToBookDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<BookDto> findBooksByPublisher(String publisherName) {
		return bookRepository.findBooksByPublisher(publisherName)
				.stream()
				.map(this::bookToBookDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<AuthorDto> findBookAuthor(Long isbn) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if (book == null) {
			return new LinkedList<>();
		}
		return book.getAuthors()
				.stream()
				.map(this::authorToAuthorDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<String> findPublishersByAuthor(String authorName) {
		return publisherRepository.findPublishersByAuthorsName(authorName);
	}

	@Override
	@Transactional
	public AuthorDto removeAuthor(String authorName) {
		Author author = authorRepository.findById(authorName).orElse(null);
		if (author == null) {
			return null;
		}
		bookRepository.findBooksByAuthorsName(authorName).forEach(b -> bookRepository.deleteById(b));
		authorRepository.delete(author);
		return authorToAuthorDto(author);
	}

	@Override
	@Transactional
	public BookDto updateBook(Long isbn, String title) {
		Book book = bookRepository.findById(isbn).orElse(null);
		if (book == null) {
			return null;
		}
		book.setTitle(title);
		return bookToBookDto(book);
	}

}
