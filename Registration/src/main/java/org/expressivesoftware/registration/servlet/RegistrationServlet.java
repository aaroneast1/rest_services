package org.expressivesoftware.registration.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.expressivesoftware.registration.CrudInterface;
import org.expressivesoftware.registration.RegistrationException;
import org.expressivesoftware.registration.marshall.Marshaller;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class RegistrationServlet extends HttpServlet {

	private static Logger LOG = Logger.getLogger(RegistrationServlet.class);
	private static final String XML = "xml";
	private static final String JSON = "json";
	private static final String CONTENT_TYPE_JSON = "application/json";
	private static final String INTERNAL_PARAM_NAME_EMAIL = "email";

	private Marshaller marshaller;
	private CrudInterface registra;

	@Inject
	private Injector injector;

	@Override
	public void init() throws ServletException {
		this.registra = injector.getInstance(CrudInterface.class);
		this.marshaller = injector.getInstance(Marshaller.class);
		LOG.info("Initialized NotifierServlet...");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		LOG.info("Enter Post method.");
		
		if (!req.getContentType().contains(JSON)) {
			res.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		
		if(!req.getCharacterEncoding().matches("\\utf-8+")){
			res.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		
		res.setContentType(CONTENT_TYPE_JSON);
		
		try {
			InputStream is = req.getInputStream();

			if (is == null) {
				res.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			
			StringBuilder sb = new StringBuilder();
			String line;

			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

			} finally {
				is.close();
			}

			try {
				registra.add(marshaller.marshallJson(sb.toString()));
			} catch (IllegalArgumentException e) {
				LOG.error(e);
				res.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			} catch (RegistrationException e) {
				LOG.error(e);
				res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
		} catch (Throwable e) {
			LOG.error(e);
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		res.setStatus(HttpServletResponse.SC_OK);
		res.flushBuffer();
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		LOG.info("Enter Put method.");
		res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		LOG.info("Enter Delete method.");
		res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		LOG.info("Enter Get method.");
		res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	}

}
