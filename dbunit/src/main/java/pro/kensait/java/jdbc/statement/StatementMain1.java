package pro.kensait.java.jdbc.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class StatementMain1 {

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

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        try {
            // データベースに接続し、コネクションを取得する
            conn = DriverManager.getConnection(url, user, password);

            // ステートメントを生成する
            stmt = conn.createStatement();

            // 検索を実行する
            String sqlStr = "SELECT * FROM EMPLOYEE";
            rset = stmt.executeQuery(sqlStr);

            // 検索結果を表示する
            while (rset.next()) {
                System.out.println(rset.getInt("EMPLOYEE_ID") + " => "
                        + rset.getString(2));
            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);

        } finally {
            // リソースをクローズする
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqle) {
                    // Connectionをクローズさせるために、意図的に例外を握る
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqle) {
                    throw new RuntimeException(sqle);
                }
            }
        }
    }
}