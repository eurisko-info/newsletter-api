package info.eurisko.rest.controller.fixture;

import static info.eurisko.rest.controller.fixture.RestDataFixture.customKeyNewsletterDetails;
import info.eurisko.core.events.newsletters.NewsletterCreatedEvent;
import info.eurisko.core.events.newsletters.NewsletterDetailsEvent;

import java.util.UUID;

public class RestEventFixtures {
	public static NewsletterDetailsEvent newsletterDetailsNotFound(final UUID key) {
		return NewsletterDetailsEvent.notFound(key);
	}

	public static NewsletterDetailsEvent newsletterDetailsEvent(final UUID key) {
		return new NewsletterDetailsEvent(key, customKeyNewsletterDetails(key));
	}

	public static NewsletterCreatedEvent newsletterCreated(final UUID key) {
		return new NewsletterCreatedEvent(key, customKeyNewsletterDetails(key));
	}
}
