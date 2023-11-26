package pro.kensait.java.jdbc.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class DAOMain {

    public static void main(String[] args) throws Exception {
        // プロパティファイルよりデータベース情報を取得する
        String driver = PropertyUtil.getValue("jdbc.driver");
        String url = PropertyUtil.getValue("jdbc.url");
        String user = PropertyUtil.getValue("jdbc.user");
        String password = PropertyUtil.getValue("jdbc.password");

        try {
            // JDBCドライバをロードする
            Class.forName(driver);
        } catch(ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }

        try (
                // データベースに接続し、コネクションを取得する
                Connection conn = DriverManager.getConnection(url, user, password);
                ) {

            EmployeeDAO employeeDAO = new EmployeeDAO(conn);

            // 主キー検索
            Employee emp1 = employeeDAO.findEmployee(10001);
            System.out.println("===== 主キー検索 =====");
            System.out.println(emp1);

            // 挿入
            Employee emp2 = new Employee(10015, "Steve", "企画部",
                    new SimpleDateFormat("yyyy/MM/dd").parse("2017/10/01"), "LEADER",
                    380000);
            employeeDAO.createEmployee(emp2);
            System.out.println("===== 挿入 =====");
            
            // 条件検索
            List<Employee> empList = employeeDAO.findEmployeesBySalary(300000, 400000);
            System.out.println("===== 条件検索 =====");
            for (Employee emp : empList) {
                System.out.println(emp);
            }
            
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}