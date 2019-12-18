package telran.book.servise;

import telran.book.dto.AuthorDto;
import telran.book.dto.BookDto;

public interface BookServise {
	boolean addBook(BookDto bookDto);

	BookDto findBookByIsbn(long isbn);

	BookDto removeBook(long isbn);

	Iterable<BookDto> findBooksByAuthor(String authorName);

	Iterable<BookDto> findBooksByPublisher(String publisherName);

	Iterable<AuthorDto> findBookAuthor(Long isbn);

	Iterable<String> findPublishersByAuthor(String authorName);

	AuthorDto removeAuthor(String authorName);

	BookDto updateBook(Long isbn, String titel);

}
