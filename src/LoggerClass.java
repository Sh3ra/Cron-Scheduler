import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerClass {
    private final static Logger logger = Logger.getLogger(LoggerClass.class.getName());
    private static FileHandler fh = null;

    public static void init(){
        try {
            fh=new FileHandler("logs.log", false);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        Logger l = Logger.getLogger("");
        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.ALL);
        l.addHandler(fh);
        l.setLevel(Level.ALL);
        l.setUseParentHandlers(false);
    }
}
