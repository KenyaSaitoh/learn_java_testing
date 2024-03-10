package pro.kensait.jdbc.company;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * EMPLOYEEテーブルへのアクセスを担うDAO（テスト対象）
 */
public class EmployeeDAO {
    private Connection conn;

    // コンストラクタ
    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    // 主キー検索
    public Employee selectEmployee(int employeeId) {
        // PreparedStatementに渡すSQL文を定義する
        String sqlStr = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, DEPARTMENT_NAME, "
                + "ENTRANCE_DATE, JOB_NAME, SALARY FROM EMPLOYEE "
                + "WHERE EMPLOYEE_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            // パラメータをセットする
            pstmt.setInt(1, employeeId);
            // 検索を実行する
            ResultSet rset = pstmt.executeQuery();
            // 検索結果（1つ）からEmployeeを生成する
            Employee employee = null;
            if (rset.next()) {
                employee = new Employee(employeeId,
                        rset.getString("EMPLOYEE_NAME"),
                        rset.getString("DEPARTMENT_NAME"),
                        rset.getDate("ENTRANCE_DATE").toLocalDate(),
                        rset.getString("JOB_NAME"),
                        rset.getInt("SALARY"));
            }
            return employee;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    // 条件検索（月給の範囲で検索）
    public List<Employee> selectEmployeesBySalary(int lowerSalary, int upperSalary) {
        // PreparedStatementに渡すSQL文を定義する
        String sqlStr = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, DEPARTMENT_NAME, "
                + "ENTRANCE_DATE, JOB_NAME, SALARY FROM EMPLOYEE "
                + "WHERE ? <= SALARY AND SALARY <= ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            // パラメータをセットする
            pstmt.setInt(1, lowerSalary);
            pstmt.setInt(2, upperSalary);
            // 検索を実行する
            ResultSet rset = pstmt.executeQuery();
            // 検索結果（複数）からEmployeeのリストを生成する
            List<Employee> resultList = new ArrayList<Employee>();
            while (rset.next()) {
                Employee employee = new Employee(rset.getInt("EMPLOYEE_ID"),
                        rset.getString("EMPLOYEE_NAME"),
                        rset.getString("DEPARTMENT_NAME"),
                        rset.getDate("ENTRANCE_DATE").toLocalDate(),
                        rset.getString("JOB_NAME"),
                        rset.getInt("SALARY"));
                resultList.add(employee);
            }
            return resultList;
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    // 挿入
    public void insertEmployee(Employee employee) {
        // PreparedStatementに渡すSQL文を定義する
        String sqlStr = "INSERT INTO EMPLOYEE VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            // パラメータをセットする
            pstmt.setInt(1, employee.getEmployeeId());
            pstmt.setString(2, employee.getEmployeeName());
            pstmt.setString(3, employee.getDepartmentName());
            pstmt.setDate(4, Date.valueOf(employee.getEntranceDate()));
            pstmt.setString(5, employee.getJobName());
            pstmt.setInt(6, employee.getSalary());
            // 更新を実行する
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    // 削除
    public void deleteEmployee(Integer employeeId) {
        // PreparedStatementに渡すSQL文を定義する
        String sqlStr = "DELETE FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            // パラメータをセットする
            pstmt.setInt(1, employeeId);
            // 更新を実行する
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    // 更新（一括更新）
    public void updateEmployeeSalary(String departmentName, Integer increase) {
        // PreparedStatementに渡すSQL文を定義する
        String sqlStr = "UPDATE EMPLOYEE SET SALARY = SALARY + ? "
                + "WHERE DEPARTMENT_NAME = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {
            // パラメータをセットする
            pstmt.setInt(1, increase);
            pstmt.setString(2, departmentName);
            // 更新を実行する
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}