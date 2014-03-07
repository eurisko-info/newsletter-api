package info.eurisko.config;

import static junit.framework.TestCase.assertEquals;
import info.eurisko.core.events.newsletters.AllNewslettersEvent;
import info.eurisko.core.events.newsletters.CreateNewsletterEvent;
import info.eurisko.core.events.newsletters.NewsletterDetails;
import info.eurisko.core.events.newsletters.RequestAllNewslettersEvent;
import info.eurisko.core.services.NewsletterService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CoreConfig.class })
public class CoreDomainIntegrationTest {
	@Autowired
	private NewsletterService newsletterService;

	/**
	 * We have already asserted the correctness of the collaboration. This is to
	 * check that the wiring in CoreConfig works. We do this by inference.
	 */
	@Test
	public void addANewNewsletterToTheSystem() {
		final CreateNewsletterEvent ev = new CreateNewsletterEvent(new NewsletterDetails());

		newsletterService.createNewsletter(ev);

		final AllNewslettersEvent allNewsletters = newsletterService.requestAllNewsletters(new RequestAllNewslettersEvent());

		assertEquals(1, allNewsletters.getNewslettersDetails().size());
	}
}
