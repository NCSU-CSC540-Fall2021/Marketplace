package server.constants;

public class DatabaseCredentials {

    // Update connection string to oracle here
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/marketplace";

    // Update your user and password info here!
    private static final String user = "root";
    private static final String password = "12345";

    // Update driver string information here!
     private static final String driverString = "com.mysql.cj.jdbc.Driver";


    public static String getJdbcURL() {
        return jdbcURL;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDriverString() {
        return driverString;
    }
}
