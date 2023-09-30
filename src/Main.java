import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        InputParser inputParser;
        Scheduler scheduler = new Scheduler();
        Scanner sc = new Scanner(System.in);
        while(true) {
            String exInterval = sc.nextLine();
            String freqInterval = sc.nextLine();
            String jobPath = sc.nextLine();
            String jobId = sc.nextLine();

            inputParser = new InputParser(exInterval, freqInterval, jobPath, jobId);

            long exIntervalms;
            long freqIntervalms;
            try {
                exIntervalms = inputParser.getExInterval();
                freqIntervalms = inputParser.getFreqInterval();
            } catch (Exception e) {
                throw new RuntimeException("Invalid Input");
            }
            //scheduler

            scheduler.addJob(exIntervalms, freqIntervalms, jobId, jobPath);
            scheduler.execJob(jobId);


        }
    }

}