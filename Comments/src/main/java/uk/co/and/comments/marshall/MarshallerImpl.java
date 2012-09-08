package uk.co.and.comments.marshall;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import uk.co.and.comments.model.Comment;
import uk.co.and.comments.model.CommentImpl;
import uk.co.and.comments.model.Location;
import uk.co.and.comments.model.Person;

public class MarshallerImpl implements Marshaller {

	private static Logger LOG = Logger.getLogger(MarshallerImpl.class);
	private static SimpleDateFormat sb = new SimpleDateFormat(
			"yyyy.MM.dd G 'at' HH:mm:ss z");

	@Override
	public Comment marshall(String text, MediaType mediaType) {

		switch (mediaType) {
		case XML:
			return marshallXml(text);
		case JSON:
			return marshallJson(text);

		default:
			throw new InvalidContentTypeException();
		}
	}

	private Comment marshallJson(String text) {
		JSONObject commentJson = JSONObject.fromObject(text);
		JSONObject locationJson = JSONObject.fromObject(commentJson
				.get(LOCATION));
		JSONObject personJson = JSONObject.fromObject(commentJson.get(PERSON));
		Location location = Location.getInstance(locationJson
				.getString(ADDRESS), locationJson.getString(ADDRESS1),
				locationJson.getString(ADDRESS2), locationJson.getString(CITY),
				locationJson.getString(COUNTY),
				locationJson.getString(COUNTRY), locationJson
						.getString(POST_CODE));
		Person person = new Person(personJson.getString(FIRSTNAME), personJson
				.getString(LASTNAME));
		Date createDate;
		try {
			createDate = sb.parse(commentJson.getString(CREATE_DATE));
		} catch (ParseException e) {
			LOG.error("Unable to parse date: "
					+ commentJson.getString(CREATE_DATE), e);
			createDate = new Date();
		}
		
		try{
			Comment comment = CommentImpl.getInstance(commentJson.getString(ID), commentJson
					.getString(PARENT_ID), commentJson.getBoolean(ACTIVE),
					createDate, person, location, commentJson.getString(SUBJECT),
					commentJson.getString(COMMENT));
			
			return comment;
		}catch(JSONException e){
			throw new InvalidPostException("could not marshall object",e);
		}
		
	}

	private Comment marshallXml(String text) {
		//TODO - Write xml implementation
		return null;
	}

	private static final String LOCATION = "location";
	private static final String ADDRESS = "address";
	private static final String ADDRESS1 = "address1";
	private static final String ADDRESS2 = "address2";
	private static final String CITY = "city";
	private static final String COUNTY = "county";
	private static final String COUNTRY = "country";
	private static final String POST_CODE = "postCode";

	private static final String PERSON = "person";
	private static final String FIRSTNAME = "firstname";
	private static final String LASTNAME = "lastname";

	private static final String ID = "id";
	private static final String PARENT_ID = "parentId";
	private static final String ACTIVE = "active";
	private static final String CREATE_DATE = "createDate";
	private static final String COMMENT = "comment";
	private static final String SUBJECT = "subject";

}
