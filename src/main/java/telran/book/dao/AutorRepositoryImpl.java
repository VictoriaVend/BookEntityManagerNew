package telran.book.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import telran.book.model.Author;
@Service
public class AutorRepositoryImpl  implements AutorRepository {
	@PersistenceContext
	EntityManager em;
	@Override
	public Optional<Author> findById(String authorName) {
		
		return Optional.ofNullable(em.find(Author.class, authorName));
	}

	@Override
	public void delete(Author author) {
		em.remove(author);

	}

	@Override
	public Author save(Author author) {
		em.persist(author);
		return author;
	}

	@Override
	public void deleteById(String authorName) {
	em.remove(em.find(Author.class, authorName));

	}

}
