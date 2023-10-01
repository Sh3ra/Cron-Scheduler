import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobRunner implements Runnable {
    private final static Logger logger = Logger.getLogger(LoggerClass.class.getName());

    private final String jobPath;
    private final String jobId;
    private final String jobClass;

    public JobRunner(String jobId, String jobPath) {
        this.jobId = jobId;
        this.jobPath = jobPath;
        jobClass = getNewClassName(jobPath.substring(0, jobPath.length() - 5));
    }

    private String getNewClassName(String substring) {
        String res = "";
        for (int i = substring.length() - 1; i >= 0; i--) {
            if (substring.charAt(i) != '/')
                res = substring.charAt(i) + res;
            else break;
        }
        return res;
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Job " + jobId + " Job execution Started");
        long startTime = System.currentTimeMillis();
        Process p1;
        try {
            p1 = Runtime.getRuntime().exec("javac " + jobPath);
            printLines(p1.getInputStream());
            printLines(p1.getErrorStream());
            p1.waitFor();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Job " + jobId + " Job Compilation failed!");
            throw new RuntimeException(e);
        }
        logger.log(Level.INFO, "Job " + jobId + " Job Compilation with exitValue: " + p1.exitValue());
        Process p2;
        try {
            p2 = Runtime.getRuntime().exec("java -classpath jobs " + jobClass);
            printLines(p2.getInputStream());
            printLines(p2.getErrorStream());
            p2.waitFor();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Job " + jobId + " Job Running failed!");
            throw new RuntimeException(e);
        }
        logger.log(Level.INFO, "Job " + jobId + " Job ran with exitValue: " + p2.exitValue());
        long endTime = System.currentTimeMillis();
        logger.log(Level.INFO, "Job " + jobId + " Job Execution Time: " + (endTime - startTime)+"ms");
        if (p2.exitValue() == 0) logger.log(Level.INFO, "Job " + jobId + " Job is Executed");
    }

    private void printLines(InputStream ins) throws Exception {
        String line;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            logger.log(Level.INFO, "Job " + jobId + " outPut: " + line);
        }
    }
}
