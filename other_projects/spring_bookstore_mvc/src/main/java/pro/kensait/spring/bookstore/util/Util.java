package pro.kensait.spring.bookstore.util;

public class Util {
    public static void sleepRandom(long from, long to) {
        long diff = to - from + 1;
        long sleepTime = from + (long) (Math.random() * diff);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
        }
    }
}
