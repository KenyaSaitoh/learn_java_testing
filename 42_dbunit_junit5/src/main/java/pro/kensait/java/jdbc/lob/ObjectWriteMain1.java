package pro.kensait.java.jdbc.lob;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class ObjectWriteMain1 {

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

        // バイナリデータとしてListを生成する
        List<String> list = Arrays.asList("Foo", "Bar", "Baz");

        // DB格納前のArrayListオブジェクト
        System.out.println("===== DB格納前のList =====");
        System.out.println(list.toString());

        String sqlStr = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";

        try (// データベースに接続し、コネクションを取得する
                Connection conn = DriverManager.getConnection(url, user, password);
                // プリペアードステートメントを生成する
                PreparedStatement pstmt = conn.prepareStatement(sqlStr,
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ) {

            // ストリームにオブジェクト（List）を書き出す
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(list);
            } catch(IOException ioe) {
                throw new RuntimeException(ioe);
            }

            int len = baos.size();
            InputStream is = new ByteArrayInputStream(baos.toByteArray());

            // ストリームからBLOB型カラムにバイナリデータを書き出す
            pstmt.setInt(1, 10016);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            rset.updateBinaryStream("PHOTO", is, len);
            rset.updateRow();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}