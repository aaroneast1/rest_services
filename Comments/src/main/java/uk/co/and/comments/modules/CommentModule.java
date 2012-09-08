package uk.co.and.comments.modules;


import uk.co.and.comments.CommentService;
import uk.co.and.comments.CommentServiceImpl;
import uk.co.and.comments.marshall.Marshaller;
import uk.co.and.comments.marshall.MarshallerImpl;
import uk.co.and.comments.marshall.UnMarshaller;
import uk.co.and.comments.marshall.UnMarshallerImpl;

import com.google.inject.AbstractModule;

public class CommentModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new CacheModule());
		bind(Marshaller.class).to(MarshallerImpl.class);
		bind(UnMarshaller.class).to(UnMarshallerImpl.class);
		bind(CommentService.class).to(CommentServiceImpl.class);
	}


}
