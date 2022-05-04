package org.study.junit.basic;

public class BarBean implements Bar {
    // ビジネスメソッド
    public int doBusiness(int param1, int param2) {
        int answer = param1 / param2;
        return answer;
    }
}