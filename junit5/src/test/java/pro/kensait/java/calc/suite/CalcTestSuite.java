package pro.kensait.java.calc.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import pro.kensait.java.calc.normal.CalculatorTest_1;
import pro.kensait.java.calc.normal.CalculatorTest_2;

@Suite
@SelectClasses({ CalculatorTest_1.class, CalculatorTest_2.class })
public class CalcTestSuite {
    // このクラスはテストスイートを構成するためだけに使用され、
    // 通常は追加のコードは含まれません。
}
