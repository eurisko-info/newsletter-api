package info.eurisko.core.events.newsletters;

import info.eurisko.core.events.RequestReadEvent;

import java.util.UUID;

public class RequestNewsletterDetailsEvent extends RequestReadEvent {
	private UUID key;

	public RequestNewsletterDetailsEvent(final UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}
}
