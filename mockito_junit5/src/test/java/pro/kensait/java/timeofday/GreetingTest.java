package pro.kensait.java.timeofday;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * Greetingを対象にしたテストクラス
 */
public class GreetingTest {

    @Test
    public void testGetMessage_1() {
        TimeManagerIF mock = new MockTimeManager(TimeOfDay.MORNING);
        Greeting greeting = new Greeting(mock);
        assertEquals("おはよう", greeting.getMessage_1());
    }
}