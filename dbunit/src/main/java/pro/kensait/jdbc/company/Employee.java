package pro.kensait.jdbc.company;

import java.time.LocalDate;
import java.util.Arrays;

public class Employee {
    // 社員ID
    private Integer employeeId;
    // 社員名
    private String employeeName;
    // 部署名
    private String departmentName;
    // 入社年月日
    private LocalDate entranceDate;
    // 役職名
    private String jobName;
    // 月給
    private Integer salary;
    // 写真
    private byte[] photo;

    // 引数なしのコンストラクタ
    public Employee() {
    }

    // コンストラクタ
    public Employee(Integer employeeId, String employeeName, String departmentName,
            LocalDate entranceDate, String jobName, Integer salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.departmentName = departmentName;
        this.entranceDate = entranceDate;
        this.jobName = jobName;
        this.salary = salary;
    }

    // アクセサメソッド
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public LocalDate getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(LocalDate entranceDate) {
        this.entranceDate = entranceDate;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName
                + ", departmentName=" + departmentName + ", entranceDate=" + entranceDate
                + ", jobName=" + jobName + ", salary=" + salary + ", photo="
                + Arrays.toString(photo) + "]";
    }
}