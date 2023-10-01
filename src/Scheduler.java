import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scheduler {
    private HashMap<String, ArrayList<Object>> jobMap = new HashMap<>();
    private HashMap<String, Thread> jobFreqThreads = new HashMap<>();
    private final static Logger logger = Logger.getLogger(LoggerClass.class.getName());

    public void addJob(long exInterval, long freqInterval, String jobId, String jobPath) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(exInterval);
        objects.add(freqInterval);
        objects.add(jobPath);

        jobMap.put(jobId, objects);
    }

    void execJob(String jobId) {
        FrequencyThread frequencyThread = new FrequencyThread(jobId, jobMap.get(jobId));
        Thread freq = new Thread(frequencyThread);
        jobFreqThreads.put(jobId, freq);
        freq.start();
        logger.log(Level.FINEST,"Job "+jobId+" Frequency Thread Started");
    }

    void killJob(String jobId) {
        jobFreqThreads.get(jobId).stop();
        jobFreqThreads.remove(jobId);
        jobMap.remove(jobId);
        logger.log(Level.FINEST,"Job "+jobId+" killed");
    }


}
