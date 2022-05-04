package org.study.junit.hamcrest.dto;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceBean {
    public Employee findEmployee(Integer employeeId) {
        return new Employee(1, "Foo", "FooDept", 350000);
    }

    public List<Employee> findEmployeesByDept(Integer departmendId) {
        List<Employee> list = new ArrayList<Employee>() {{
            add(new Employee(3, "Baz", "BazDept", 450000));
            add(new Employee(1, "Foo", "FooDept", 350000));
            add(new Employee(2, "Bar", "BarDept", 400000));
        }};
        return list;
    }
}
