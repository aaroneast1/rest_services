package uk.co.and.comments.modules;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.BlockingQueue;

import org.junit.Test;

import uk.co.and.comments.CommentService;
import uk.co.and.comments.CommentStore;
import uk.co.and.comments.marshall.Marshaller;
import uk.co.and.comments.marshall.UnMarshaller;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ModuleTest {
	
	@Test
	public void testSuccessfulInstantiationOfSendMail() throws Exception {
		
		Injector injector = Guice.createInjector(new CommentModule());
		
		BlockingQueue queue = injector.getInstance(BlockingQueue.class);
		CommentStore store = injector.getInstance(CommentStore.class);
		
		assertNotNull(queue);
		assertNotNull(store);
		
		Marshaller marshaller = injector.getInstance(Marshaller.class);
		UnMarshaller unMarshaller = injector.getInstance(UnMarshaller.class);
		CommentService commentService = injector.getInstance(CommentService.class);
		
		assertNotNull(marshaller);
		assertNotNull(unMarshaller);
		assertNotNull(commentService);
	}

}
