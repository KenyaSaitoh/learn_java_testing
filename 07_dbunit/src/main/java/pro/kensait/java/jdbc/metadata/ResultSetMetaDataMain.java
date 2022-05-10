package pro.kensait.java.jdbc.metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class ResultSetMetaDataMain {

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
                Statement stmt = conn.createStatement();
                    ) {

            // 検索を実行する
            String sqlStr = "SELECT * FROM EMPLOYEE";
            ResultSet rset = stmt.executeQuery(sqlStr);

            // メタ情報を表示する
            ResultSetMetaData rsmd = rset.getMetaData();
            int columnCount = rsmd.getColumnCount();
            System.out.println("===== カラム数 =====");
            System.out.println("CulumnCount => " + columnCount);
            for (int i = 0; i < columnCount; i++) {
                int index = i + 1;
                System.out.println("===== " + index + "カラム目 =====");
                System.out.println("ColumnName => " + rsmd.getColumnName(index));
                System.out.println("ColumnTypeName => " + rsmd.getColumnTypeName(index));
                System.out.println("Precision => " + rsmd.getPrecision(index));
                System.out.println("isNullable => " + rsmd.isNullable(index));
            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}