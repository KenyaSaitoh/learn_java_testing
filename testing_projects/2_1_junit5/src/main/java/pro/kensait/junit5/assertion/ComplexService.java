package pro.kensait.junit5.assertion;

import java.util.Random;

public class ComplexService {
    public void process() {
        try {
            Random rand = new Random();
            int randomNum = 1 + rand.nextInt(4); // 1～4までの乱数
            Thread.sleep(randomNum * 1000);
        } catch (InterruptedException ie) {
        }
    }
}
