package org.springframework.samples.petclinic.feeding;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class FeedingTypeFormatter implements Formatter<FeedingType>{

    private final FeedingService service;
    
    @Autowired
    public FeedingTypeFormatter(FeedingService service){
        this.service = service;
    }

    @Override
    public String print(FeedingType object, Locale locale) {
        
        return object.getName();
    }

    @Override
    public FeedingType parse(String text, Locale locale) throws ParseException {
        Collection<FeedingType> findFeedingTypes = this.service.getAllFeedingTypes();
		for (FeedingType type : findFeedingTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
    }
    
}
