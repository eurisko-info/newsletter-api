package info.eurisko.rest.domain;

import static org.junit.Assert.assertEquals;
import info.eurisko.core.events.newsletters.NewsletterDetails;
import info.eurisko.rest.controller.fixture.RestDataFixture;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class NewsletterTests {
	@Before
	public void setup() {
		final MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	@Test
	public void thatNewsletterCanConvertToNewsletterDetails() {
		final Newsletter newsletter = RestDataFixture.standardNewsletter();

		final NewsletterDetails details = newsletter.toNewsletterDetails();

		assertEquals(newsletter.getKey(), details.getKey());
		assertEquals(newsletter.getDateTimeOfSubmission(), details.getDateTimeOfSubmission());
	}

	@Test
	public void thatNewsletterCanConvertFromNewsletterDetails() {
		final NewsletterDetails details = RestDataFixture.standardNewsletterDetails();

		final Newsletter newsletter = Newsletter.fromNewsletterDetails(details);

		assertEquals(newsletter.getKey(), details.getKey());
		assertEquals(newsletter.getDateTimeOfSubmission(), details.getDateTimeOfSubmission());
	}
}
