import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    String driver;
    String databaseLoc;
    String username;
    String password;
    Connection conn;


    public SQLConnection() {

        driver = "com.mysql.jdbc.Driver";
        databaseLoc = "jdbc:mysql://localhost:3306/registro?noAccessToProcedureBodies=true";
        username = "root";
        password = "root";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(databaseLoc, username, password);

        } catch (ClassNotFoundException e) {
            System.err.format("SQL State: %s\n%s", e.getClass(), e.getMessage());
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }


    public SQLConnection(String user, String pssw) {

        driver = "com.mysql.jdbc.Driver";
        databaseLoc = "jdbc:mysql://localhost:3306/registro?noAccessToProcedureBodies=true";
        username = user;
        password = pssw;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(databaseLoc, username, password);

        } catch (ClassNotFoundException e) {
            System.err.format("SQL State: %s\n%s", e.getClass(), e.getMessage());
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }


}
