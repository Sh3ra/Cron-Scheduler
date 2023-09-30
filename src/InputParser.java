import java.util.regex.Pattern;

public class InputParser {
    private final String exInterval;
    private final String freqInterval;
    private final String jobPath;
    private final String jobId;

    public InputParser(String exInterval, String freqInterval, String jobPath, String jobId) {
        this.exInterval = exInterval;
        this.freqInterval = freqInterval;
        this.jobPath = jobPath;
        this.jobId = jobId;
    }

    public String getJobIdt() {
        return jobId;
    }

    public String getJobPath() {
        return jobPath;
    }

    public long getExInterval() throws Exception {
        return parseInterval(exInterval);
    }

    public long getFreqInterval() throws Exception {
        return parseInterval(freqInterval);
    }

    //1hr
    //1m-1min
    //1sec-1s
    //1hr2sec
    //1hr1min232s
    private long parseInterval(String interval) throws Exception {
        Exception invalidInputException = new Exception();
        if (!validateInterval(interval)) throw invalidInputException;
        long ms = 0;
        StringBuilder temp = new StringBuilder();
        long tms = 0;
        for (char c : interval.toCharArray()) {
            if (isValidChar(c)) {
                temp.append(c);
            } else if (isNumber(c)) {
                if (!temp.isEmpty()) {
                    ms += calculateMS(tms, temp);
                    temp = new StringBuilder();
                    tms = 0;
                }

                tms *= (10);
                tms += (c - '0');

            } else throw invalidInputException;
        }
        ms += calculateMS(tms, temp);
        return ms;
    }

    private long calculateMS(long tms, StringBuilder temp) {
        if (Pattern.matches("^hr$|^h$", temp)) {
            tms *= (1000 * 60 * 60);
        } else if (Pattern.matches("^m$|^min$", temp)) {
            tms *= (1000 * 60);
        } else tms *= 1000;
        return tms;
    }

    private boolean validateInterval(String interval) {
        return Pattern.matches("^([\\d]+(hr|h|min|m|sec|s)){1,3}$", interval);
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isValidChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

}