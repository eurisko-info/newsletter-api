package info.eurisko.core.domain.fixtures;

import info.eurisko.core.domain.Newsletter;
import info.eurisko.core.events.newsletters.NewsletterDetails;

import java.util.Date;
import java.util.UUID;

public class NewslettersFixtures {

	public static final String YUMMY_ITEM = "yummy_core";

	public static Newsletter standardNewsletter() {
		final Newsletter newsletter = new Newsletter();

		newsletter.setKey(UUID.randomUUID());
		newsletter.setDateTimeOfSubmission(new Date());

		return newsletter;
	}

	/**
	 * Twin of the above, to improve readability
	 */
	public static NewsletterDetails standardNewsletterDetails() {
		return standardNewsletter().toNewsletterDetails();
	}

}
