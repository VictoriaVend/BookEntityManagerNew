package telran.book.dao;

import java.util.List;
import java.util.Optional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import telran.book.model.Publisher;
@Service
public class PublisherRepositoryImpl implements PublisherRepository {
	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	public Optional<Publisher> findById(String publisherName) {
		
		return Optional.ofNullable(entityManager.find(Publisher.class, publisherName));
	}

	@Override
	public Publisher save(Publisher publisher) {
		entityManager.persist(publisher);
		return publisher;
	}

	@Override
	public List<String> findPublishersByAuthorsName(String authorName) {
		TypedQuery<String> query=entityManager.createQuery("select b.publisher.publisherName from Book b join b.authors a where a.name=?1",String.class);
		query.setParameter(1,authorName );
		return query.getResultList();
	}

}
