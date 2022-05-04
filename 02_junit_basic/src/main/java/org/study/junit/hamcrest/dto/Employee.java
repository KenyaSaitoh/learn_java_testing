package org.study.junit.hamcrest.dto;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    private Integer employeeId;
    private String employeeName;
    private String departmentName;
    private Integer monthlySalary;
    private Date lastUpdateTime;

    // 引数なしのコンストラクタ
    public Employee() {
    }

    // コンストラクタ
    public Employee(Integer employeeId, String employeeName,
            String departmentName, Integer monthlySalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.departmentName = departmentName;
        this.monthlySalary = monthlySalary;
    }

    // 従業員番号へのアクセサメソッド
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    // 従業員名へのアクセサメソッド
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    // 部門名へのアクセサメソッド
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    // 月給へのアクセサメソッド
    public Integer getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Integer monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    // 最終更新時刻へのアクセスメソッド
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", employeeName="
                + employeeName + ", departmentName=" + departmentName
                + ", monthlySalary=" + monthlySalary + ", lastUpdateTime="
                + lastUpdateTime + "]";
    }


    /*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((departmentName == null) ? 0 : departmentName.hashCode());
        result = prime * result
                + ((employeeId == null) ? 0 : employeeId.hashCode());
        result = prime * result
                + ((employeeName == null) ? 0 : employeeName.hashCode());
        result = prime * result
                + ((monthlySalary == null) ? 0 : monthlySalary.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (departmentName == null) {
            if (other.departmentName != null)
                return false;
        } else if (!departmentName.equals(other.departmentName))
            return false;
        if (employeeId == null) {
            if (other.employeeId != null)
                return false;
        } else if (!employeeId.equals(other.employeeId))
            return false;
        if (employeeName == null) {
            if (other.employeeName != null)
                return false;
        } else if (!employeeName.equals(other.employeeName))
            return false;
        if (monthlySalary == null) {
            if (other.monthlySalary != null)
                return false;
        } else if (!monthlySalary.equals(other.monthlySalary))
            return false;
        return true;
    }
    */
}