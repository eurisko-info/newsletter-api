package info.eurisko.core.events.newsletters;

import info.eurisko.core.events.ReadEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AllNewslettersEvent extends ReadEvent {

	private final List<NewsletterDetails> newslettersDetails;

	public AllNewslettersEvent(final List<NewsletterDetails> newsletters) {
		this.newslettersDetails = Collections.unmodifiableList(newsletters);
	}

	public Collection<NewsletterDetails> getNewslettersDetails() {
		return this.newslettersDetails;
	}
}
