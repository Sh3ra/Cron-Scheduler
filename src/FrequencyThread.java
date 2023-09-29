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
        //run execution thread
        System.out.println("executing job" + jobId);
        try {
            Thread.sleep((Long) jobData.get(1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        run();
    }
}
