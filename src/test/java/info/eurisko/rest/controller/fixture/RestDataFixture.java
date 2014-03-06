package info.eurisko.rest.controller.fixture;

import info.eurisko.core.events.newsletters.AllNewslettersEvent;
import info.eurisko.core.events.newsletters.NewsletterDetails;
import info.eurisko.rest.domain.Newsletter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Use of test data fixture classes is considered good practice.
 * The majority of tests aren't testing data edge cases, they are testing logical flows and what happens to a generic set of data.
 * For these, use a small, standardized set of fixtures.
 * For anything more esoteric, create a new fixture in the test class.
 */
public class RestDataFixture {
	public static final String YUMMY_ITEM = "yummy1";

	public static AllNewslettersEvent allNewsletters() {
		final List<NewsletterDetails> newsletters = new ArrayList<NewsletterDetails>();

		newsletters.add(standardNewsletterDetails());
		newsletters.add(standardNewsletterDetails());
		newsletters.add(standardNewsletterDetails());

		return new AllNewslettersEvent(newsletters);
	}

	public static Newsletter standardNewsletter() {
		final Newsletter newsletter = new Newsletter();

		return newsletter;
	}

	public static NewsletterDetails customKeyNewsletterDetails(final UUID key) {
		final NewsletterDetails newsletterDetails = new NewsletterDetails(key);

		return newsletterDetails;
	}

	public static NewsletterDetails standardNewsletterDetails() {
		return customKeyNewsletterDetails(UUID.randomUUID());
	}

	public static String standardNewsletterJSON() {
		return "{ }";
	}
}
