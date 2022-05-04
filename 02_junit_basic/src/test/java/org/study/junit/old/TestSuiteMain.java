package org.study.junit.old;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSuiteMain extends TestCase {

    public TestSuiteMain(String name) {
        super(name);
    }

    public static Test suite() {
        // TestSuiteオブジェクトを生成する。
        TestSuite suite = new TestSuite("TestSuiteSample1");

        // TestSuiteオブジェクトにTestCaseクラスを登録する。
        suite.addTestSuite(TestCaseMain1.class);
        suite.addTestSuite(TestCaseMain2.class);

        // 生成したTestSuiteオブジェクトを返す。
        return suite;
    }
}