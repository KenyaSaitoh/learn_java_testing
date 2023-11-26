package pro.kensait.java.jdbc.cursol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class CursolMain1 {

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
                // データベースに接続し、コネクションを取得する
                Connection conn = DriverManager.getConnection(url, user, password);
                // ステートメントを生成する
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                ) {

            // 検索を実行する
            String sqlStr = "SELECT * FROM EMPLOYEE";
            ResultSet rset = stmt.executeQuery(sqlStr);

            // 指定されたカーソル位置に移動する
            rset.next();
            System.out.println(rset.getRow() + " => " + rset.getString("EMPLOYEE_ID"));
            rset.absolute(4);
            System.out.println(rset.getRow() + " => " + rset.getString("EMPLOYEE_ID"));
            rset.absolute(2);
            System.out.println(rset.getRow() + " => " + rset.getString("EMPLOYEE_ID"));
            rset.last();
            System.out.println(rset.getRow() + " => " + rset.getString("EMPLOYEE_ID"));
            rset.beforeFirst();
            System.out.println(rset.getRow() + " => 空");

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}