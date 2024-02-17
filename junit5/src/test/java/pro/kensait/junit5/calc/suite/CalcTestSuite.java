package pro.kensait.junit5.calc.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import pro.kensait.junit5.calc.normal.CalculatorTest;
import pro.kensait.junit5.calc.normal.CalculatorTest_2;

@Suite
@SelectClasses({ CalculatorTest.class, CalculatorTest_2.class })
public class CalcTestSuite {
    // このクラスはテストスイートを構成するためだけに使用され、
    // 通常は追加のコードは含まれません。
}
