package pro.kensait.jdbc.company;

import static pro.kensait.jdbc.util.DatabaseUtil.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DAOMain {

    public static void main(String[] args) throws Exception {
        // プロパティファイルよりデータベース情報を取得する
        String driver = getProperty("jdbc.driver");
        String url = getProperty("jdbc.url");
        String user = getProperty("jdbc.user");
        String password = getProperty("jdbc.password");

        try {
            // JDBCドライバをロードする
            Class.forName(driver);
        } catch(ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }

        try (
                // データベースに接続し、Connectionを取得する
                Connection conn = DriverManager.getConnection(url, user, password);
                ) {

            EmployeeDAO employeeDAO = new EmployeeDAO(conn);

            // 主キー検索
            System.out.println("***** 主キー検索 *****");
            Employee emp1 = employeeDAO.selectEmployee(10001);
            System.out.println(emp1);

            // 条件検索
            System.out.println("***** 条件検索 *****");
            List<Employee> resultList = employeeDAO.selectEmployeesBySalary(300000, 400000);
            for (Employee employee : resultList) {
                System.out.println(employee);
            }

            // 挿入
            System.out.println("***** 挿入 *****");
            Employee emp2 = new Employee(10021, "Steve", "SALES", LocalDate.of(2017, 10, 1),
                    null, 380000);
            employeeDAO.insertEmployee(emp2);

            // 削除
            System.out.println("***** 削除 *****");
            employeeDAO.deleteEmployee(10004);

            // 一括更新
            System.out.println("***** 一括更新 *****");
            employeeDAO.updateSalary("SALES", 3000);

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}