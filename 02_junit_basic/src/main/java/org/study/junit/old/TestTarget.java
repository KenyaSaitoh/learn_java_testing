package org.study.junit.old;

public class TestTarget {

    public static final int value1 = 2;
    private String name;
    private int number;

    public TestTarget(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public TestTarget() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int calc(int param) {
        return number * param;
    }

    public void executeWithException(int param) throws Exception {
        if (param < 0) {
            throw new Exception();
        }
    }
}