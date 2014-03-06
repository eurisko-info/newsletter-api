package info.eurisko.core.services;

import info.eurisko.core.domain.Newsletter;
import info.eurisko.core.events.newsletters.AllNewslettersEvent;
import info.eurisko.core.events.newsletters.CreateNewsletterEvent;
import info.eurisko.core.events.newsletters.NewsletterCreatedEvent;
import info.eurisko.core.events.newsletters.NewsletterDetails;
import info.eurisko.core.events.newsletters.NewsletterDetailsEvent;
import info.eurisko.core.events.newsletters.RequestAllNewslettersEvent;
import info.eurisko.core.events.newsletters.RequestNewsletterDetailsEvent;
import info.eurisko.core.repository.NewslettersRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NewsletterEventHandler implements NewsletterService {

	private final NewslettersRepository newslettersRepository;

	public NewsletterEventHandler(final NewslettersRepository newslettersRepository) {
		this.newslettersRepository = newslettersRepository;
	}

	@Override
	public NewsletterCreatedEvent createNewsletter(final CreateNewsletterEvent createNewsletterEvent) {
		final Newsletter newsletter = Newsletter.fromNewsletterDetails(createNewsletterEvent.getDetails());

		newslettersRepository.persist(newsletter);

		return new NewsletterCreatedEvent(newsletter.getKey(), newsletter.toNewsletterDetails());
	}

	@Override
	public AllNewslettersEvent requestAllNewsletters(final RequestAllNewslettersEvent requestAllCurrentNewslettersEvent) {
		final List<NewsletterDetails> generatedDetails = new ArrayList<NewsletterDetails>();
		for (final Newsletter newsletter : newslettersRepository.findAll()) {
			generatedDetails.add(newsletter.toNewsletterDetails());
		}
		return new AllNewslettersEvent(generatedDetails);
	}

	@Override
	public NewsletterDetailsEvent requestNewsletterDetails(final RequestNewsletterDetailsEvent requestNewsletterDetailsEvent) {
		final Newsletter newsletter = newslettersRepository.find(requestNewsletterDetailsEvent.getKey());

		if (newsletter == null) {
			return NewsletterDetailsEvent.notFound(requestNewsletterDetailsEvent.getKey());
		}

		return new NewsletterDetailsEvent(requestNewsletterDetailsEvent.getKey(), newsletter.toNewsletterDetails());
	}
}
