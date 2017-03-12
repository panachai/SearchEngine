
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextField;

public class DBHelper {

    private String database;
    private String driver;
    private String url;
    private Connection conn;
    private Statement stm;
    private ResultSet rs;
    private String sql;

    public DBHelper() {

        database = "SearchEngineDb";
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
/*
        try {
            //step 4 create table
            sql = "CREATE TABLE profile(name VARCHAR(15),lastname VARCHAR(15),address VARCHAR(50))";
            stm.execute(sql);
            
        } catch (SQLException ex) {
            System.out.println("cannot create table");
            System.exit(-1);
        }
*/
    }

    public void insertProfile(String name, String lastname, String address) throws SQLException {
        sql = "INSERT INTO profile VALUES('" + name + "','" + lastname + "','" + address + "')";
        stm.executeUpdate(sql);

    }

    public void selectAllProfile() throws SQLException {
        sql = "SELECT * FROM profile";
        rs = stm.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2)+ "\t\t" + rs.getString(3));
        }
    }

    public void searchProfile(String table, String msg) throws SQLException {
        sql = "SELECT * FROM profile where " + table + " like '" + msg + "'";
        rs = stm.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t\t" + rs.getString(2));
        }
    }

    public void deleteProfile(String field, String msg) throws SQLException {
        sql = "DELETE FROM profile WHERE " + field + " = '" + msg + "'";
        stm.executeUpdate(sql);
    }

    public void updateProfile() throws SQLException {
        sql = "UPDATE profile SET name = 'Somkid' WHERE name = 'Somchai'";
        stm.executeUpdate(sql);
    }

    public static void main(String[] args) {

    }

}
