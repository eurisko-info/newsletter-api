package info.eurisko.rest.controller;

import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemPropertiesController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Properties getSystemProperties() {
    	return System.getProperties();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{key}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getSystemProperty(final @PathVariable("key") String key) {
    	return System.getProperty(key);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{key}/{value}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String setSystemProperty(final @PathVariable("key") String key, final @PathVariable("value") String value) {
    	return System.setProperty(key, value);
    }
}
