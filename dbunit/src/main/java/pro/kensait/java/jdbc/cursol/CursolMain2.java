package pro.kensait.java.jdbc.cursol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class CursolMain2 {

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
                Statement stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ) {

            // 検索を実行する
            String sqlStr = "SELECT * FROM EMPLOYEE";
            ResultSet rset = stmt.executeQuery(sqlStr);

            // 指定されたカーソル位置に移動する
            rset.absolute(5);

            // 更新実行前の情報の表示する
            System.out.println("===== 更新実行前情報の出力 =====");
            System.out.println(rset.getRow() + " => SALARY: " + rset.getInt("SALARY"));

            // 給与を更新する
            System.out.println("===== 給与を更新更 =====");
            rset.updateInt("SALARY", 333333);
            System.out.println(rset.getRow() + " => SALARY: " + rset.getInt("SALARY"));

            // 更新をキャンセルする
            System.out.println("===== 更新をキャンセル =====");
            rset.cancelRowUpdates();
            System.out.println(rset.getRow() + " => SALARY: " + rset.getInt("SALARY"));

            // 給与を再度更新する
            System.out.println("===== 給与を再度更新 =====");
            rset.updateInt("SALARY", 444444);
            System.out.println(rset.getRow() + " => SALARY: " + rset.getInt("SALARY"));

            // 更新を確定する
            System.out.println("===== 更新を確定 =====");
            rset.updateRow();
            System.out.println(rset.getRow() + " => SALARY: " + rset.getInt("SALARY"));

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}