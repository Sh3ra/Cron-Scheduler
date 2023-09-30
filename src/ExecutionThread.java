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
        JobRunner jobRunner = new JobRunner(jobPath);
        Thread exec = new Thread(jobRunner);
        exec.start();
        try {
            Thread.sleep(exInterval);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(exec.isAlive())
            exec.stop();
        System.out.println("job on path: " + jobPath+" ended");
    }
}
