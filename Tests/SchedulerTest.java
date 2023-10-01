import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerTest {

    @Test
    void ThreadsCount() {
        Scheduler scheduler = new Scheduler();
        scheduler.addJob(1000,2000,"Id1","jobs/Test.java");
        scheduler.addJob(1000,2000,"Id2","jobs/Test2.java");
        Assertions.assertEquals(2,java.lang.Thread.activeCount());
        scheduler.execJob("Id2");
        scheduler.execJob("Id1");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(8,java.lang.Thread.activeCount());
    }
}