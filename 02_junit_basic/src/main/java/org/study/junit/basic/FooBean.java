package org.study.junit.basic;

public class FooBean implements Foo {
    // インジェクションポイント
    private Bar bar;

    // 引数なしのコンストラクタ
    public FooBean() {
    }

    // コンストラクタ
    public FooBean(Bar bar) {
        this.bar = bar;
    }

    // ビジネスメソッド
    public int doBusiness(int param1, int param2) {
        int value = bar.doBusiness(param1, param2);
        int answer = value * 2;
        return answer;
    }
}