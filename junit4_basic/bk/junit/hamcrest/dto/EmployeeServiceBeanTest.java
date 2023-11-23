package org.study.junit.hamcrest.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EmployeeServiceBeanTest {

    private EmployeeServiceBean bean;

    // テストメソッドごとに事前実行
    @Before
    public void before() {
        bean = new EmployeeServiceBean();
    }

    @Test
    public void test01() {
        Employee actual = bean.findEmployee(1);
        Employee expected = new Employee(1, "Foo", "FooDept", 350000);

        assertThat(actual, is(expected));
    }

    @Test
    public void test02() {
        Employee actual = bean.findEmployee(1);
        Employee expected = new Employee(1, "Foo", "FooDept", 350000);

        assertThat(actual, is(new EmployeeMatcher(expected)));
    }

    @Test
    public void test03() {
        List<Employee> actual = bean.findEmployeesByDept(1);
        assertThat(actual, is(new EmployeeIdListMatcher(new Integer[]{1, 2, 3})));
    }

    @Test
    public void test04() {
        List<Employee> actual = bean.findEmployeesByDept(1);
        List<Employee> expected = new ArrayList<Employee>() {{
            add(new Employee(1, "Foo", "FooDept", 350000));
            add(new Employee(2, "Bar", "BarDept", 400000));
            add(new Employee(3, "Baz", "BazDept", 450000));
        }};
        assertThat(actual, is(new EmployeeListMatcher(expected)));
    }
}
