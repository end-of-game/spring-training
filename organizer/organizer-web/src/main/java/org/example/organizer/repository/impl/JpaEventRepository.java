package org.example.organizer.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.example.organizer.model.Event;
import org.example.organizer.repository.EventRepository;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

public class JpaEventRepository implements EventRepository {
	@PersistenceContext
	private EntityManager em;
	
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Event> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> query = cb.createQuery(Event.class);
		query.select(query.from(Event.class));
		return em.createQuery(query).getResultList();
	}

	@Override
	public List<Event> findAll(Sort sort) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> query = cb.createQuery(Event.class);
		query.select(query.from(Event.class));
		
		//query.orderBy()
		return em.createQuery(query).getResultList();
	}

	@Override
	public Optional<Event> findOne(Long id) {
		return Optional.ofNullable(em.find(Event.class, id));
	}

	@Override
	public Event save(Event event) {
		if (event.getId() != null) {
			em.persist(event);
		} else {
			event = em.merge(event);
		}
		return event;
	}

	@Override
	public void delete(Event event) {
		em.remove(event);
	}

}
