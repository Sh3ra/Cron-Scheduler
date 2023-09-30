import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JobRunner implements Runnable {

    private final String jobPath;
    private final String jobClass;

    public JobRunner(String jobPath) {
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
        Process p1 ;
        try {
            p1 = Runtime.getRuntime().exec("javac " + jobPath);
            printLines(p1.getInputStream());
            printLines(p1.getErrorStream());
            p1.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(" exitValue() " + p1.exitValue());
        Process p2 ;
        try {
            p2 = Runtime.getRuntime().exec("java -classpath jobs "+jobClass);
            printLines(p2.getInputStream());
            printLines(p2.getErrorStream());
            p2.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(" exitValue() " + p2.exitValue());
    }

    private static void printLines(InputStream ins) throws Exception {
        String line ;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(" " + line);
        }
    }
}
