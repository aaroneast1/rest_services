package uk.co.and.comments.marshall;

import org.apache.commons.lang.StringUtils;

public class MediaTypeInterpreter {
	
	public static MediaType get(String source){
		if(StringUtils.isBlank(source)){
			throw new InvalidContentTypeException("invalid content type");
		}
		
		if(StringUtils.contains(source, "application/json")){
			return MediaType.JSON;
		}
		
		if(StringUtils.contains(source, "application/xml")){
			return MediaType.XML;
		}
		
		if(StringUtils.contains(source, "text")){
			return MediaType.TEXT;
		}
		
		throw new InvalidContentTypeException("invalid content type");
		
	}

}
