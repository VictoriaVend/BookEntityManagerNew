package telran.book.dao;

import java.util.List;
import java.util.Optional;


import telran.book.model.Book;

public interface BookRepository {
	List<Book> findBooksByAuthorsName(String authorName);

	List<Book> findBooksByPublisher(String publisherName);


	Book save(Book book);

	boolean existsById(long isbn);

	Optional<Book> findById(long isbn);

	void deleteById(Book book);

}
