package info.eurisko.core.repository;

import info.eurisko.core.domain.Newsletter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NewslettersMemoryRepository implements NewslettersRepository {

	private static final Map<UUID, Newsletter> emptyNewsletterList = new HashMap<UUID, Newsletter>();

	private Map<UUID, Newsletter> newsletters;

	public NewslettersMemoryRepository() {
		this.newsletters = Collections.unmodifiableMap(emptyNewsletterList);
	}

	@Override
	public synchronized void persist(final Newsletter newsletter) {
		newsletter.setKey(UUID.randomUUID());

		final Map<UUID, Newsletter> modifiableNewsletters = new HashMap<UUID, Newsletter>(newsletters);
		modifiableNewsletters.put(newsletter.getKey(), newsletter);
		this.newsletters = Collections.unmodifiableMap(modifiableNewsletters);
	}

	@Override
	public Newsletter find(final UUID key) {
		return newsletters.get(key);
	}

	@Override
	public Collection<Newsletter> findAll() {
		return Collections.unmodifiableList(new ArrayList<Newsletter>(newsletters.values()));
	}
}
