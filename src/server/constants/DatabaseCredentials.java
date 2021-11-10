package server.constants;

public class DatabaseCredentials {

    // Update connection string to oracle here
    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";

    // Update your user and password info here!
    private static final String user = "vmuthuk3";
    private static final String password = "abcd1234";

    // Update driver string information here!
     private static final String driverString = "oracle.jdbc.OracleDriver";


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
