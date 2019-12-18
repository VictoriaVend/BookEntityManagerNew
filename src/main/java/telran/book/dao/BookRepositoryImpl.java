package telran.book.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import telran.book.model.Book;
@Service
public class BookRepositoryImpl implements BookRepository {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Book> findBooksByAuthorsName(String authorName) {
		TypedQuery<Book> query=entityManager.createQuery("select b from Book b join b.authors a where a.name=?1",Book.class);
		query.setParameter(1,authorName );
		return query.getResultList();
	}
@Override
	public List<Book> findBooksByPublisher(String publisherName) {
	TypedQuery<Book> query=entityManager.createQuery("select b from Book b  where b.publisherName=?1",Book.class);
	query.setParameter(1,publisherName );
		return query.getResultList();
	}

	@Override
	public Book save(Book book) {
		entityManager.persist(book);
		return book;
	}

	@Override
	public boolean existsById(long isbn) {
	return entityManager.find(Book.class, isbn)!=null;
		 
	}

	@Override
	public Optional<Book> findById(long isbn) {
		return Optional.ofNullable(entityManager.find(Book.class, isbn));
	}

	@Override
	public void deleteById(Book book) {
	 entityManager.remove(book);

	}


	

}
