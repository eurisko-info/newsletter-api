package info.eurisko.rest.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import info.eurisko.core.events.newsletters.NewsletterDetails;
import info.eurisko.rest.controller.NewsletterQueriesController;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

/**
 * This is added so that we can do jaxb serialisation.
 * This type of annotation is fine here, as this Newsletter implementation is made for integration with things like this.
 */
@SuppressWarnings("serial")
@XmlRootElement
public class Newsletter extends ResourceSupport implements Serializable {
	private Date dateTimeOfSubmission;

	private UUID key;

	public Date getDateTimeOfSubmission() {
		return dateTimeOfSubmission;
	}

	public UUID getKey() {
		return key;
	}

	public void setDateTimeOfSubmission(final Date dateTimeOfSubmission) {
		this.dateTimeOfSubmission = dateTimeOfSubmission;
	}

	public void setKey(UUID key) {
		this.key = key;
	}

	public NewsletterDetails toNewsletterDetails() {
		final NewsletterDetails details = new NewsletterDetails();

		details.setKey(key);
		details.setDateTimeOfSubmission(dateTimeOfSubmission);

		return details;
	}

	public static Newsletter fromNewsletterDetails(final NewsletterDetails newsletterDetails) {
		final Newsletter newsletter = new Newsletter();

		newsletter.dateTimeOfSubmission = newsletterDetails.getDateTimeOfSubmission();
		newsletter.key = newsletterDetails.getKey();

		// Adding the library, the above extends ResourceSupport and
		// this section is all that is actually needed in our model to add hateoas support.

		// Much of the rest of the framework is helping deal with the blending of domains that happens in many spring apps.
		// We have explicitly avoided that.
		newsletter.add(linkTo(NewsletterQueriesController.class).slash(newsletter.key).withSelfRel());

		return newsletter;
	}
}
