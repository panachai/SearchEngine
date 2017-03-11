
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

    private String database;
    private String driver;
    private String url;
    private Connection conn;
    private Statement stm;
    private ResultSet rs;
    private String sql;

    public DBHelper() {
        database = "servDb";
        driver = "org.apache.derby.jdbc.ClientDriver";
        url = "jdbc:derby:" + database + ";create=true";
        try {
            //step 1 load driver
            Class.forName(driver).newInstance();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("cannot load driver");
            System.exit(-1);
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ix) {
        }

        try {
            //step 2 connect to database
            conn = DriverManager.getConnection(url, "", ""); //user password
        } catch (SQLException ex) {
            System.out.println("cannot load database");
            System.exit(-1);
        }

        try {
            //step 3
            stm = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("cannot create statement");
            System.exit(-1);
        }

        try {
            //step 4 create sql command and execute
/*  create table
                sql = "CREATE TABLE person(name VARCHAR(15),lastname VARCHAR(15))";
                stm.execute(sql);
             */
 /*  insert
            sql = "INSERT INTO person VALUES('Somchai5','Jaidee1')";
            stm.executeUpdate(sql);
             */
 /* update
            sql = "UPDATE person SET name = 'Somkid' WHERE name = 'Somchai'";
            stm.executeUpdate(sql);
             */
 /*
            sql = "DELETE FROM person WHERE name = 'Somkid'";
            stm.executeUpdate(sql);
             */

            sql = "SELECT * FROM person";
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t\t" + rs.getString(2));
            }

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

    }

    public static void main(String[] args) {

    }

}
