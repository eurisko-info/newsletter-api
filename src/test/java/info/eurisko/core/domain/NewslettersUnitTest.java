package info.eurisko.core.domain;

import static org.junit.Assert.assertEquals;
import info.eurisko.core.domain.fixtures.NewslettersFixtures;
import info.eurisko.core.repository.NewslettersMemoryRepository;

import org.junit.Before;
import org.junit.Test;

public class NewslettersUnitTest {
	private NewslettersMemoryRepository uut;

	@Before
	public void setupUnitUnderTest() {
		uut = new NewslettersMemoryRepository();
	}

	@Test
	public void addASingleNewsletterToTheNewsletters() {
		assertEquals(0, uut.findAll().size());

		uut.persist(NewslettersFixtures.standardNewsletter());

		assertEquals(1, uut.findAll().size());
	}
}
