import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private final static Logger logger = Logger.getLogger(LoggerClass.class.getName());
    public static void main(String[] args) {
        LoggerClass.init();
        logger.log(Level.INFO,"Application Starting");
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
                logger.log(Level.INFO,"Input Parsed");;
            } catch (Exception e) {
                logger.log(Level.INFO,"Invalid Input");
                System.out.println("Invalid Input");
                continue;
            }
            //scheduler

            scheduler.addJob(exIntervalms, freqIntervalms, jobId, jobPath);
            logger.log(Level.INFO,"Job Added");
            scheduler.execJob(jobId);
        }
    }

}