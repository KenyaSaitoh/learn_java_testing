package org.study.junit.old;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSuiteMain1 extends TestCase {

    public TestSuiteMain1(String name) {
        super(name);
    }

    public static Test suite() {
        // TestSuiteオブジェクトを生成する。
        TestSuite suite = new TestSuite("TestSuiteSample1");

        // TestSuiteオブジェクトにTestCaseを登録する。
        suite.addTestSuite(TestCaseMain1.class);
        suite.addTestSuite(TestCaseMain2.class);

        // TestSuiteを返す。
        return suite;
    }
}