public class ExecutionThread implements Runnable{
    private final String jobPath;
    private final long exInterval;

    public ExecutionThread(String jobPath,long exInterval){
        this.exInterval=exInterval;
        this.jobPath=jobPath;
    }
    @Override
    public void run() {
        //exec job
        System.out.println("executing job on path: " + jobPath);

        try {
            Thread.sleep(exInterval);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //kill job
        System.out.println("job on path: " + jobPath+" ended");
    }
}
