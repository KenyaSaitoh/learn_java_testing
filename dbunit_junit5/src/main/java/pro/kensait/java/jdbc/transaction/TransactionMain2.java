package pro.kensait.java.jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class TransactionMain2 {

    public static void main(String[] args) {
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
                // データベースに接続し、トランザクション用コネクションを取得する
                Connection conn1 = DriverManager.getConnection(url, user, password);
                // データベースに接続し、別コネクションを取得する
                Connection conn2 = DriverManager.getConnection(url, user, password);
                // ステートメントを生成する
                Statement stmt = conn1.createStatement();
                ) {

            // トランザクションを開始する
            conn1.setAutoCommit(false);

            // 更新前の情報を表示する
            System.out.println("===== BEFORE TRANSACTION =====");
            showEmployeeTable(conn2);

            // SELECT FOR UPDATE文を実行する
            System.out.println("===== SELECT FOR UPDATE文実行 =====");
            stmt.executeQuery("SELECT * FROM EMPLOYEE " +
                    "WHERE EMPLOYEE_ID = '10005' FOR UPDATE ");
            showEmployeeTable(conn2);

            // UPDATE文を実行する
            System.out.println("===== UPDATE文実行 =====");
            stmt.executeUpdate("UPDATE EMPLOYEE SET SALARY = 444444 " +
                    "WHERE EMPLOYEE_ID = 10005");
            showEmployeeTable(conn2);

            // トランザクションをコミットする
            conn1.commit();
            System.out.println("===== COMMIT =====");
            showEmployeeTable(conn2);

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    // トランザクションとは別コネクションでテーブルを参照する
    private static void showEmployeeTable(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            String sqlStr = "SELECT * FROM EMPLOYEE";
            ResultSet rset = stmt.executeQuery(sqlStr);
            while (rset.next()) {
                System.out.println(
                        rset.getInt("EMPLOYEE_ID") + ", " +
                        rset.getString("EMPLOYEE_NAME") + ", " +
                        rset.getString("JOB_NAME") + ", " +
                        rset.getInt("SALARY"));
            }
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}