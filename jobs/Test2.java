import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class Test2 {
    public static void main(String[] args) {
        while(true)
        {
            System.out.println("Test2 is running");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}