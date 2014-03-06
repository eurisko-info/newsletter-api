package info.eurisko.core.domain;

import info.eurisko.core.events.newsletters.NewsletterDetails;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.beans.BeanUtils;

@Entity
public class Newsletter {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Type(type = "pg-uuid")
	private UUID key; // = UUID.randomUUID()

	private Date dateTimeOfSubmission; // dateTimeOfSubmission

	public Newsletter() {
	}

	public UUID getKey() {
		return key;
	}

	public void setKey(final UUID key) {
		this.key = key;
	}

	public Date getDateTimeOfSubmission() {
		return dateTimeOfSubmission;
	}

	public void setDateTimeOfSubmission(final Date dateTimeOfSubmission) {
		this.dateTimeOfSubmission = dateTimeOfSubmission;
	}

	public NewsletterDetails toNewsletterDetails() {
		final NewsletterDetails details = new NewsletterDetails();

		BeanUtils.copyProperties(this, details);

		return details;
	}

	public static Newsletter fromNewsletterDetails(final NewsletterDetails newsletterDetails) {
		final Newsletter newsletter = new Newsletter();

		BeanUtils.copyProperties(newsletterDetails, newsletter);

		return newsletter;
	}
}
