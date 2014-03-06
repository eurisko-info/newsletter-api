package info.eurisko.core.events.newsletters;

import info.eurisko.core.events.CreatedEvent;

import java.util.UUID;

public class NewsletterCreatedEvent extends CreatedEvent {

	private final UUID newNewsletterKey;
	private final NewsletterDetails details;

	public NewsletterCreatedEvent(final UUID newNewsletterKey, final NewsletterDetails details) {
		this.newNewsletterKey = newNewsletterKey;
		this.details = details;
	}

	public NewsletterDetails getDetails() {
		return details;
	}

	public UUID getNewNewsletterKey() {
		return newNewsletterKey;
	}
}
