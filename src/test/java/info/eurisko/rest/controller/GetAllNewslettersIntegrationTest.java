package info.eurisko.rest.controller;

import static info.eurisko.rest.controller.fixture.RestDataFixture.allNewsletters;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import info.eurisko.core.events.newsletters.RequestAllNewslettersEvent;
import info.eurisko.core.services.NewsletterService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

public class GetAllNewslettersIntegrationTest {
	private MockMvc mockMvc;

	@InjectMocks
	private NewsletterQueriesController controller;

	@Mock
	private NewsletterService newsletterService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = standaloneSetup(controller).build();

		when(newsletterService.requestAllNewsletters(any(RequestAllNewslettersEvent.class)))
			.thenReturn(allNewsletters());
	}

	@Test
	public void thatGetNewslettersRendersAsJson() throws Exception {
		this.mockMvc
			.perform(get("/aggregators/newsletters")
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
