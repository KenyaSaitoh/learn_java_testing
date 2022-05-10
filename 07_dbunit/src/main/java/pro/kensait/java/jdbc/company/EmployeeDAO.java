package pro.kensait.java.jdbc.company;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection conn;

    // コンストラクタ
    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    // 検索（主キーから）
    public Employee findEmployee(int employeeId) {
        String sqlStr = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, DEPARTMENT_NAME, "
                + "ENTRANCE_DATE, JOB_NAME, SALARY FROM EMPLOYEE "
                + "WHERE EMPLOYEE_ID=?";
        try (
                // プリペアードステートメントを生成する
                PreparedStatement pstmt = conn.prepareStatement(sqlStr);
                ) {

            pstmt.setInt(1, employeeId);
            ResultSet rset = pstmt.executeQuery();
            Employee employee = null;
            if (rset.next()) {
                employee = new Employee(employeeId,
                        rset.getString("EMPLOYEE_NAME"),
                        rset.getString("DEPARTMENT_NAME"),
                        rset.getDate("ENTRANCE_DATE"),
                        rset.getString("JOB_NAME"),
                        rset.getInt("SALARY"));
            }
            return employee;

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    // 検索（月給の範囲で検索）
    public List<Employee> findEmployeesBySalary(int lowerSalary,
            int upperSalary) {
        String sqlStr = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, DEPARTMENT_NAME, "
                + "ENTRANCE_DATE, JOB_NAME, SALARY FROM EMPLOYEE "
                + "WHERE ? <= SALARY AND SALARY <= ?";
            try (
                    // プリペアードステートメントを生成する
                    PreparedStatement pstmt = conn.prepareStatement(sqlStr);
                    ) {
                pstmt.setInt(1, lowerSalary);
                pstmt.setInt(2, upperSalary);
                ResultSet rset = pstmt.executeQuery();
                List<Employee> list = new ArrayList<Employee>();
                while (rset.next()) {
                    Employee employee = new Employee(rset.getInt("EMPLOYEE_ID"),
                            rset.getString("EMPLOYEE_NAME"),
                            rset.getString("DEPARTMENT_NAME"),
                            rset.getDate("ENTRANCE_DATE"),
                            rset.getString("JOB_NAME"),
                            rset.getInt("SALARY"));
                    list.add(employee);
                }
                return list;

            } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    // 挿入
    public void createEmployee(Employee employee) {
        String sqlStr = "INSERT INTO EMPLOYEE VALUES(?, ?, ?, ?, ?, ?)";
        try (
                // プリペアードステートメントを生成する
                PreparedStatement pstmt = conn.prepareStatement(sqlStr);
                ) {
            pstmt.setInt(1, employee.getEmployeeId());
            pstmt.setString(2, employee.getEmployeeName());
            pstmt.setString(3, employee.getDepartmentName());
            pstmt.setDate(4, new Date(employee.getEntranceDate().getTime()));
            pstmt.setString(5, employee.getJobName());
            pstmt.setInt(6, employee.getSalary());
            pstmt.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}