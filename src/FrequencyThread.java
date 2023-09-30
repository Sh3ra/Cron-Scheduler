import java.util.ArrayList;

public class FrequencyThread implements Runnable {
    private String jobId;
    ArrayList<Object> jobData;

    public FrequencyThread(String jobId, ArrayList<Object> jobData) {
        this.jobId = jobId;
        this.jobData = jobData;
    }

    @Override
    public void run() {
        ExecutionThread executionThread = new ExecutionThread((String) jobData.get(2), (Long) jobData.get(0));
        Thread exec = new Thread(executionThread);
        exec.start();
        try {
            Thread.sleep((Long) jobData.get(1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        run();
    }
}
