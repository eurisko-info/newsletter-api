package info.eurisko.core.services;

import info.eurisko.core.events.newsletters.AllNewslettersEvent;
import info.eurisko.core.events.newsletters.CreateNewsletterEvent;
import info.eurisko.core.events.newsletters.NewsletterCreatedEvent;
import info.eurisko.core.events.newsletters.NewsletterDetailsEvent;
import info.eurisko.core.events.newsletters.RequestAllNewslettersEvent;
import info.eurisko.core.events.newsletters.RequestNewsletterDetailsEvent;

/**
 * This is an event driven service used to interact with the core domain.
 * All methods are guaranteed to return something, null will never be returned.
 */
public interface NewsletterService {
	public AllNewslettersEvent requestAllNewsletters(final RequestAllNewslettersEvent requestAllCurrentNewslettersEvent);

	public NewsletterDetailsEvent requestNewsletterDetails(final RequestNewsletterDetailsEvent requestNewsletterDetailsEvent);

	public NewsletterCreatedEvent createNewsletter(final CreateNewsletterEvent event);
}
