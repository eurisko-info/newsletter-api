package info.eurisko.core.repository;

import info.eurisko.core.domain.Newsletter;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class NewslettersPersistentRepository implements NewslettersRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Newsletter find(final UUID id) {
		return em.find(Newsletter.class, id);
	}

	@Override
	public void persist(final Newsletter newsletter) {
		em.persist(newsletter);
	}

	@Override
	public Collection<Newsletter> findAll() {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Newsletter> cq = cb.createQuery(Newsletter.class);
		final Root<Newsletter> pet = cq.from(Newsletter.class);
		cq.select(pet);
		final TypedQuery<Newsletter> q = em.createQuery(cq);
		return q.getResultList();
	}
}