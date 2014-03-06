package info.eurisko.core.repository;

import info.eurisko.core.domain.Newsletter;

import java.util.Collection;
import java.util.UUID;

//TODO make this event based again, with persistence integration events.
public interface NewslettersRepository {
	void persist(final Newsletter newsletter);

	Newsletter find(final UUID key);

	Collection<Newsletter> findAll();
}
