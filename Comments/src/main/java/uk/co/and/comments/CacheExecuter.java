package uk.co.and.comments;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import uk.co.and.comments.tasks.Task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CacheExecuter {
	
	private static final Log LOG = LogFactory.getLog(CacheExecuter.class);

    private final ThreadPoolExecutor executor;

    // Static params for Thread pool config.
    private static final int corePoolSize = 1;
    private static final int maximumPoolSize = 3;
    private static final long keepAliveTime = 240;

    public CacheExecuter(final BlockingQueue cacheQueue, final CommentStore store) {
        this.executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS, cacheQueue) {

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
            	try{
            		((Task)r).setCrudInterface(store);
            	}catch(ClassCastException e){
            		//When find a atexTimerTask which sets the timer.
            	}
                super.beforeExecute(t, r);
            }
        };
        this.executor.prestartAllCoreThreads();
    }

    public void shutdown() {
        LOG.info("shutdown called on CacheExecuter");
        executor.shutdown();
    }

	
}
