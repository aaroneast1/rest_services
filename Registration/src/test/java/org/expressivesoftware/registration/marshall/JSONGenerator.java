package org.expressivesoftware.registration.marshall;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;

import org.expressivesoftware.registration.model.ModelGenerator;
import org.junit.Ignore;
import org.junit.Test;

public class JSONGenerator {
	
	private static SimpleDateFormat DF = new SimpleDateFormat(UserMarshaller.DATE_FORMAT);
	
	@Test
	public void testSuccessfulCreationOfJSONString() throws Exception {
		JSONObject jsonObject = JSONObject.fromObject(ModelGenerator.getUser());
		assertNotNull(jsonObject.toString());
		System.out.println(jsonObject.toString());
	}
	
	@Ignore()
	public static String getNewUserAsJSON(){
		JSONObject jsonObject = JSONObject.fromObject(ModelGenerator.getUser());
		return jsonObject.toString();
	}
	
	@Ignore()
	public static String getExistingUserAsJSON() {
		JSONObject jsonObject = JSONObject.fromObject(ModelGenerator.getExistingUser());
		JSONObject personJSON = jsonObject.getJSONObject("person");
		personJSON.put("dob", DF.format(ModelGenerator.getExistingUser().getPerson().getDob()));
		return jsonObject.toString();
	}
	
	@Ignore()
	public static String getAddress() {
		JSONObject jsonObject = JSONObject.fromObject(ModelGenerator.getAddress());
		return jsonObject.toString();
	}
	
	@Ignore()
	public static String getPerson() {
		JSONObject jsonObject = JSONObject.fromObject(ModelGenerator.getPerson());
		jsonObject.put("dob", DF.format(ModelGenerator.getPerson().getDob()));
		return jsonObject.toString();
	}
	
	@Ignore()
	public static String getPhone() {
		JSONObject jsonObject = JSONObject.fromObject(ModelGenerator.getPhone());
		return jsonObject.toString();
	}
	
	@Ignore()
	public static String getContactDetails() {
		JSONObject jsonObject = JSONObject.fromObject(ModelGenerator.getContactDetails());
		return jsonObject.toString();
	}
	
	@Ignore()
	public static void main(String[] args) throws Exception {
		System.out.println(getNewUserAsJSON());
	}
	

}
