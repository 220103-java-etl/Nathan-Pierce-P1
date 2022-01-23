package dev.pierce.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    private static ConnectionUtil connectionUtil = null;
    private static Properties dbProps;

    private ConnectionUtil() {
        dbProps = new Properties();

        InputStream props = ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties");

        try {
            dbProps.load(props);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionUtil getConnectionUtil() {
        if(connectionUtil == null) {
            connectionUtil = new ConnectionUtil();
        }
        return connectionUtil;
    }

    public static Connection getConnection() {

        Connection connection = null;
        String url = dbProps.getProperty("url");
        String username = dbProps.getProperty("username");
        String password = dbProps.getProperty("password");

        try{
            connection = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return connection;
    }

}
