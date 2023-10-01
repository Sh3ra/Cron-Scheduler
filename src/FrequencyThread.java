import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrequencyThread implements Runnable {
    private String jobId;
    ArrayList<Object> jobData;
    private final static Logger logger = Logger.getLogger(LoggerClass.class.getName());
    public FrequencyThread(String jobId, ArrayList<Object> jobData) {
        this.jobId = jobId;
        this.jobData = jobData;
    }

    @Override
    public void run() {
        ExecutionThread executionThread = new ExecutionThread(jobId,(String) jobData.get(2), (Long) jobData.get(0));
        Thread exec = new Thread(executionThread);
        logger.log(Level.INFO,"Job "+jobId+" Execution Thread Created");
        exec.start();
        logger.log(Level.INFO,"Job "+jobId+" Execution Thread Started");
        try {
            Thread.sleep((Long) jobData.get(1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        run();
    }
}
