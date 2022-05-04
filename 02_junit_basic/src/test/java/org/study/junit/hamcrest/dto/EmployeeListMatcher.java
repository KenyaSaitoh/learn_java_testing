package org.study.junit.hamcrest.dto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class EmployeeListMatcher extends TypeSafeMatcher<List<Employee>> {

    private List<Employee> expected;

    public EmployeeListMatcher(List<Employee> expected) {
        this.expected = expected;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }

    @Override
    protected boolean matchesSafely(List<Employee> actual) {
        // 実際に取得されたリストをソートする。
        System.out.println(actual);
        Collections.sort(actual, new Comparator<Employee>() {
            public int compare(Employee e1, Employee e2) {
                return e1.getEmployeeId() - e2.getEmployeeId();
            }
        });
        System.out.println(actual);

        // 期待されるリストと、ソート済みの取得リストをマッチングする。
        int index = 0;
        for (Employee employee : expected) {
            EmployeeMatcher matcher = new EmployeeMatcher(employee);
            if (! matcher.matchesSafely(actual.get(index))) {
                return false;
            };
            index++;
        }
        return true;
    }
}
