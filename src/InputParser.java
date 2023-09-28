public class InputParser {
    private String exInterval;
    private String freqInterval;
    private String jobPath;
    private String jobId;

    public InputParser(String exInterval, String freqInterval, String jobPath, String jobId){
        this.exInterval=exInterval;
        this.freqInterval=freqInterval;
        this.jobPath=jobPath;
        this.jobId=jobId;
    }

    public String getJobIdt(){
        return jobId;
    }

    public String getJobPath() {
        return jobPath;
    }

    public int getExInterval() {
        return parseInterval(exInterval);
    }

    public int getfreqInterval() {
        return parseInterval(freqInterval);
    }

    private int parseInterval(String interval)
    {
        int t;
        t = Integer.parseInt(interval);
        return t;
    }

}