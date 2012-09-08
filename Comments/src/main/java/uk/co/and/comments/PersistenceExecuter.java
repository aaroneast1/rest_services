package uk.co.and.comments;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import uk.co.and.comments.db.CommentDao;
import uk.co.and.comments.tasks.Task;

public class PersistenceExecuter {
	private static final Log LOG = LogFactory.getLog(PersistenceExecuter.class);

    private final ThreadPoolExecutor executor;

    // Static params for Thread pool config.
    private static final int corePoolSize = 1;
    private static final int maximumPoolSize = 3;
    private static final long keepAliveTime = 240;

    public PersistenceExecuter(final BlockingQueue persistenceQueue, final CommentDao dao) {
        this.executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS, persistenceQueue) {

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
            	try{
            		((Task)r).setCrudInterface(dao);
            	}catch(ClassCastException e){
            		//When find a atexTimerTask which sets the timer.
            	}
                super.beforeExecute(t, r);
            }
        };
        this.executor.prestartAllCoreThreads();
    }

    public void shutdown() {
        LOG.info("shutdown called on PersistenceExecuter");
        executor.shutdown();
    }

}
