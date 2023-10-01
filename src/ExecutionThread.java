import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutionThread implements Runnable {
    private final String jobPath;
    private final String jobId;
    private final long exInterval;
    private final static Logger logger = Logger.getLogger(LoggerClass.class.getName());

    public ExecutionThread(String jobId, String jobPath, long exInterval) {
        this.jobId = jobId;
        this.exInterval = exInterval;
        this.jobPath = jobPath;
    }

    @Override
    public void run() {
        //exec job
        JobRunner jobRunner = new JobRunner(jobId, jobPath);
        Thread exec = new Thread(jobRunner);
        logger.log(Level.INFO, "Job " + jobId + " Job Runner Created");
        long startTime = System.currentTimeMillis();
        exec.start();
        logger.log(Level.INFO, "Job " + jobId + " Job Runner Started");
        try {
            Thread.sleep(exInterval);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (exec.isAlive())
        {
            exec.stop();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "Job " + jobId + " Job Execution Time: " + (endTime - startTime)+"ms");
        }
        logger.log(Level.INFO, "Job " + jobId + " has Ended");
    }
}
