package info.eurisko.core.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import info.eurisko.core.domain.Newsletter;
import info.eurisko.core.events.newsletters.CreateNewsletterEvent;
import info.eurisko.core.events.newsletters.NewsletterDetails;
import info.eurisko.core.repository.NewslettersMemoryRepository;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class NewsletterEventHandlerUnitTest {
	private NewsletterEventHandler uut;
	private NewslettersMemoryRepository mockNewslettersMemoryRepository;

	@Before
	public void setupUnitUnderTest() {
		mockNewslettersMemoryRepository = mock(NewslettersMemoryRepository.class);
		uut = new NewsletterEventHandler(mockNewslettersMemoryRepository);
	}

	@Test
	public void addANewNewsletterToTheSystem() {
		final Newsletter newsletter = new Newsletter();
		newsletter.setKey(UUID.randomUUID());
		newsletter.setDateTimeOfSubmission(new Date());

		CreateNewsletterEvent ev = new CreateNewsletterEvent(new NewsletterDetails());

		uut.createNewsletter(ev);

		verify(mockNewslettersMemoryRepository).persist(any(Newsletter.class));
		verifyNoMoreInteractions(mockNewslettersMemoryRepository);
	}

	@Test
	public void addTwoNewNewslettersToTheSystem() {
		final Newsletter newsletter = new Newsletter();
		newsletter.setKey(UUID.randomUUID());
		newsletter.setDateTimeOfSubmission(new Date());

		CreateNewsletterEvent ev = new CreateNewsletterEvent(new NewsletterDetails());

		uut.createNewsletter(ev);
		uut.createNewsletter(ev);

		verify(mockNewslettersMemoryRepository, times(2))
			.persist(any(Newsletter.class));
		verifyNoMoreInteractions(mockNewslettersMemoryRepository);
	}
}
