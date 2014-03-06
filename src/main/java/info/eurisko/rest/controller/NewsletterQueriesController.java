package info.eurisko.rest.controller;

import info.eurisko.core.events.newsletters.NewsletterDetails;
import info.eurisko.core.events.newsletters.NewsletterDetailsEvent;
import info.eurisko.core.events.newsletters.RequestAllNewslettersEvent;
import info.eurisko.core.events.newsletters.RequestNewsletterDetailsEvent;
import info.eurisko.core.services.NewsletterService;
import info.eurisko.rest.domain.Newsletter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aggregators/newsletters")
public class NewsletterQueriesController {

    @Autowired
    private NewsletterService newsletterService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Newsletter> getAllNewsletters() {
    	final List<Newsletter> newsletters = new ArrayList<Newsletter>();
        for (final NewsletterDetails detail : newsletterService.requestAllNewsletters(new RequestAllNewslettersEvent()).getNewslettersDetails()) {
        	newsletters.add(Newsletter.fromNewsletterDetails(detail));
        }
        return newsletters;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Newsletter> viewNewsletter(final @PathVariable String id) {
    	final NewsletterDetailsEvent details = newsletterService.requestNewsletterDetails(new RequestNewsletterDetailsEvent(UUID.fromString(id)));

        if (!details.isEntityFound()) {
            return new ResponseEntity<Newsletter>(HttpStatus.NOT_FOUND);
        }

        final Newsletter newsletter = Newsletter.fromNewsletterDetails(details.getNewsletterDetails());

        return new ResponseEntity<Newsletter>(newsletter, HttpStatus.OK);
    }
}
