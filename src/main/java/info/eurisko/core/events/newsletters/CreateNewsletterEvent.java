package info.eurisko.core.events.newsletters;

import info.eurisko.core.events.CreateEvent;

public class CreateNewsletterEvent extends CreateEvent {
	private NewsletterDetails details;

	public CreateNewsletterEvent(final NewsletterDetails details) {
		this.details = details;
	}

	public NewsletterDetails getDetails() {
		return details;
	}
}
