import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @Test
    void getExInterval() throws Exception {
        InputParser inputParser =new InputParser("1hr","1sec","a","b");
        long r = inputParser.getExInterval();
        Assertions.assertEquals(3600000,r);
    }

    @Test
    void getFreqInterval() throws Exception {
        InputParser inputParser =new InputParser("1hr","1hr20m4444sec","a","b");
        long r = inputParser.getFreqInterval();
        Assertions.assertEquals(9244000,r);
    }
}