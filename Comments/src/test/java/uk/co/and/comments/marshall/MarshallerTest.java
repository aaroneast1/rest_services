package uk.co.and.comments.marshall;

import static org.junit.Assert.*;

import java.util.Date;

import net.sf.json.JSONObject;

import org.junit.Ignore;
import org.junit.Test;

import uk.co.and.comments.model.Comment;
import uk.co.and.comments.model.CommentImpl;
import uk.co.and.comments.model.Location;
import uk.co.and.comments.model.Person;

public class MarshallerTest {
	
	@Test
	public void testSuccessfulMarshallToJson() {
		Comment comment = CommentImpl.getInstance("1234", "1", true, new Date(), MarshallerTest.getPerson(), MarshallerTest.getLocation(), "subject - nothing really", "comment - I love metro.  What a great paper!");
		JSONObject jsonObject = JSONObject.fromObject(comment);
		Marshaller marshaller = new MarshallerImpl();
		Comment marshalledComment = marshaller.marshall(jsonObject.toString(), MediaType.JSON);
		System.out.println(comment.hashCode() + " = " + marshalledComment.hashCode());
		System.out.println(comment.toString() + "\n\n" + marshalledComment.toString());
		assertEquals(comment, marshalledComment);
	}
	
	@Ignore
	public static Person getPerson(){
		return new Person("Aaron", "East");
	}
	
	public static Location getLocation() {
		return Location.getInstance("999 Pizza Avenue", null, null, "London", null, "UK", "W1 XPG");
	}

}
