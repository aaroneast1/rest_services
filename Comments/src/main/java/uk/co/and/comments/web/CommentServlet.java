package uk.co.and.comments.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import uk.co.and.comments.CommentService;
import uk.co.and.comments.InActiveCommentException;
import uk.co.and.comments.marshall.InvalidContentTypeException;
import uk.co.and.comments.marshall.InvalidPostException;
import uk.co.and.comments.marshall.Marshaller;
import uk.co.and.comments.marshall.MediaTypeInterpreter;
import uk.co.and.comments.marshall.UnMarshaller;
import uk.co.and.comments.model.Comment;
import uk.co.and.comments.model.CommentImpl;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class CommentServlet extends HttpServlet {

	private static Logger LOG = Logger.getLogger(CommentServlet.class);
	private Marshaller marshaller;
	private UnMarshaller unMarshaller;
	private CommentService commentService;
	private static final String GET_PARAM_ID = "id";
	private static final String GET_PARAM_PARENT_ID = "parentId";
	private static final String ACCEPT = "Accept";
	
	@Inject
	private Injector injector;
	
	@Override
	public void init() throws ServletException {
		this.marshaller = injector.getInstance(Marshaller.class);
		this.unMarshaller = injector.getInstance(UnMarshaller.class);
		this.commentService = injector.getInstance(CommentService.class);
		LOG.info("Initialized CommentServlet...");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doDelete(req, resp);  //TODO - implement deletes
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPut(req, resp);  //TODO - implement updates
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		try {
			String id = req.getParameter(GET_PARAM_ID);
			String parentId = req.getParameter(GET_PARAM_PARENT_ID);

			if ((StringUtils.isBlank(id) && StringUtils.isBlank(parentId)) || StringUtils.isBlank(parentId)) {
				res.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}

			Set<Comment> comments = commentService.get(parentId);

			if (comments != null) {
				PrintWriter out = res.getWriter();
				out.write(unMarshaller.unMarshall(comments,
						MediaTypeInterpreter.get(req.getHeader(ACCEPT))));
				res.setStatus(HttpServletResponse.SC_OK);
				res.flushBuffer();
				return;
			}

			Comment comment = commentService.get(CommentImpl.getIdInstance(id));

			if (comment != null) {
				PrintWriter out = res.getWriter();
				out.write(unMarshaller.unMarshall(comment, MediaTypeInterpreter
						.get(req.getHeader(ACCEPT))));
				res.setStatus(HttpServletResponse.SC_OK);
				res.flushBuffer();
				return;
			}

			res.sendError(HttpServletResponse.SC_NO_CONTENT);
		} catch (InActiveCommentException e) {
			LOG.error(HttpServletResponse.SC_FORBIDDEN+":"+e.getMessage(),e);
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
		} catch (Exception e) {
			LOG.error(HttpServletResponse.SC_SERVICE_UNAVAILABLE+":"+e.getMessage(),e);
			res.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// TODO - Refactor to use NIO, improve error response

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
				commentService.add(marshaller.marshall(sb.toString(),
						MediaTypeInterpreter.get(req.getContentType())));
			} catch(InvalidPostException e){	
				LOG.error(e);
				res.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			} catch (InvalidContentTypeException e) {
				LOG.error(e);
				res.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
				return;
			} catch (IllegalArgumentException e) {
				LOG.error(e);
				res.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		} catch (Exception e) {
			LOG.error(e);
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
