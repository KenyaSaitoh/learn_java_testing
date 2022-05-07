package org.study.junit.hamcrest.dto;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class EmployeeMatcher extends TypeSafeMatcher<Employee> {

    private Employee expected;

    public EmployeeMatcher(Employee expected) {
        this.expected = expected;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }

    @Override
    protected boolean matchesSafely(Employee actual) {
        if (expected.getEmployeeId().equals(actual.getEmployeeId())
                && expected.getEmployeeName().equals(actual.getEmployeeName())) {
            return true;
        }
        return false;
    }
}
