package info.eurisko.rest.functional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import info.eurisko.rest.controller.fixture.RestDataFixture;
import info.eurisko.rest.domain.Newsletter;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class NewsletterTests {
	@Test
	public void thatNewslettersCanBeAddedAndQueried() {
		final ResponseEntity<Newsletter> entity = createNewsletter();

		final String path = entity.getHeaders().getLocation().getPath();

		assertEquals(HttpStatus.CREATED, entity.getStatusCode());
		assertTrue(path.startsWith("/aggregators/newsletters/"));
		final Newsletter newsletter = entity.getBody();

		System.out.println("The Newsletter ID is " + newsletter.getKey());
		System.out.println("The Location is " + entity.getHeaders().getLocation());
	}

	@Test
	public void thatNewslettersCannotBeAddedAndQueriedWithBadUser() {
		final HttpEntity<String> requestEntity = new HttpEntity<String>(
				RestDataFixture.standardNewsletterJSON(),
				getHeaders("letsnosh:BADPASSWORD"));

		final RestTemplate template = new RestTemplate();
		try {
			final ResponseEntity<Newsletter> entity = template.postForEntity(
					"http://localhost:8080/aggregators/newsletters",
					requestEntity,
					Newsletter.class);

			fail("Request Passed incorrectly with status " + entity.getStatusCode());
		} catch (final HttpClientErrorException ex) {
			assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());
		}
	}

	@Test
	public void thatNewslettersHaveCorrectHateoasLinks() {
		final ResponseEntity<Newsletter> entity = createNewsletter();

		final Newsletter newsletter = entity.getBody();

		assertEquals(entity.getHeaders().getLocation().toString(), newsletter.getLink("self").getHref());
	}

	private ResponseEntity<Newsletter> createNewsletter() {
		final HttpEntity<String> requestEntity = new HttpEntity<String>(
				RestDataFixture.standardNewsletterJSON(),
				getHeaders("letsnosh:noshing"));

		final RestTemplate template = new RestTemplate();
		return template.postForEntity(
				"http://localhost:8080/aggregators/newsletters",
				requestEntity,
				Newsletter.class);
	}

	static HttpHeaders getHeaders(final String auth) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		final byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
		headers.add("Authorization", "Basic " + new String(encodedAuthorisation));

		return headers;
	}
}
