package pro.kensait.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class DatabaseUtil {

    private static ResourceBundle resource;
    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream("db.properties");
        try {
            resource = new PropertyResourceBundle(is);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public static String getProperty(String key) {
        return resource.getString(key);
    }
}