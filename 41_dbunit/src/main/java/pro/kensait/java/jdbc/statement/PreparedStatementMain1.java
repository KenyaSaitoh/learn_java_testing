package pro.kensait.java.jdbc.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class PreparedStatementMain1 {

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

        String sqlStr = "SELECT * FROM EMPLOYEE WHERE DEPARTMENT_NAME = ? "
                + "AND ? <= SALARY";

        try (
                // データベースに接続し、コネクションを取得する
                Connection conn = DriverManager.getConnection(url, user, password);
                // プリペアードステートメントを生成する
                PreparedStatement pstmt = conn.prepareStatement(sqlStr);
                ) {

            // パラメータをセットする
            pstmt.setString(1, "営業部");
            pstmt.setInt(2, 300000);

            // 検索を実行する
            ResultSet rset = pstmt.executeQuery();

            // 検索結果を表示する
            while (rset.next()) {
                System.out.println(
                        rset.getInt("EMPLOYEE_ID") + ", " +
                        rset.getString("EMPLOYEE_NAME") + ", " +
                        rset.getString("DEPARTMENT_NAME") + ", " +
                        rset.getInt("SALARY"));
            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}