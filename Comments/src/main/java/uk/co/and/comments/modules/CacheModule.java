package uk.co.and.comments.modules;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import uk.co.and.comments.CommentStore;
import uk.co.and.comments.CommentStoreImpl;

import com.google.inject.AbstractModule;

public class CacheModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BlockingQueue.class).to(LinkedBlockingQueue.class).asEagerSingleton();
		bind(CommentStore.class).to(CommentStoreImpl.class).asEagerSingleton();
	}

}
