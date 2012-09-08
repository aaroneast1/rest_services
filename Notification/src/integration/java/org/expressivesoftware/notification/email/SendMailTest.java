package org.expressivesoftware.notification.email;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.expressivesoftware.notification.email.SendMail;
import org.expressivesoftware.notification.email.model.Email;
import org.expressivesoftware.notification.email.model.Server;

public class SendMailTest {

	public static void main(String[] args) throws Exception {
		
		Server server = new Server("mail.expressivesoftware.org", null, null);
		SendMail sm = new SendMail(server);
		List<String> to = new ArrayList<String>();
		List<String> cc = new ArrayList<String>();
		List<String> bcc = new ArrayList<String>();
		to.add("aaroneast1@gmail.com");
		to.add("yulich1978@yahoo.co.uk");
		cc.add("aaroneast1@gmail.com");
		cc.add("yulich1978@yahoo.co.uk");
		bcc.add("aaroneast1@gmail.com");
		bcc.add("yulich1978@yahoo.co.uk");

		Email email = new Email("info@expressivesoftware.org", to);
		email.setBody("Hello Aaron \n\n This is a test.");
		email.setSubject("This is a test");
		email.setBcc(bcc);
		email.setCc(cc);

		JSONObject jsonObject = JSONObject.fromObject(email);
		System.out.println(jsonObject.toString());

	}

}
