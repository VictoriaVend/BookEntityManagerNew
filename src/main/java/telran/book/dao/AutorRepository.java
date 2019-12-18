package telran.book.dao;

import java.util.Optional;

import telran.book.model.Author;

public interface AutorRepository {
	Optional<Author> findById(String authorName);
	void delete(Author author);
	Author save(Author author);
	void deleteById(String authorName);

}
