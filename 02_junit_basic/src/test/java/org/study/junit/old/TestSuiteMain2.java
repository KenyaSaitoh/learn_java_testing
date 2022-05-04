package org.study.junit.old;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSuiteMain2 extends TestCase {

    public TestSuiteMain2(String name) {
        super(name);
    }

    public static Test suite() {
        // TestSuiteオブジェクトを生成する。
        TestSuite suite = new TestSuite("TestSuiteSample2");

        // TestSuiteオブジェクトにTestCaseを登録する。
        suite.addTestSuite(TestCaseMain1.class);

        // TestSuiteオブジェクトに他のTestSuiteを登録する。
        suite.addTest(TestSuiteMain1.suite());

        // TestSuiteを返す。
        return suite;
    }
}