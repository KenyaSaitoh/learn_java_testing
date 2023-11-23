package org.study.junit.hamcrest.dto;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class EmployeeIdListMatcher extends TypeSafeMatcher<List<Employee>> {

    private Integer[] expected;

    public EmployeeIdListMatcher(Integer[] expected) {
        this.expected = expected;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }

    @Override
    protected boolean matchesSafely(List<Employee> actual) {
        SortedSet<Integer> actualSet = new TreeSet<Integer>();
        for (Employee e : actual) {
            actualSet.add(e.getEmployeeId());
        }
        int index = 0;
        for (Integer employeeId : actualSet) {
            if (! employeeId.equals(expected[index])) {
                return false;
            }
            index++;
        }
        return true;
    }
}
