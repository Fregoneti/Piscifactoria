package Utils;

import Controller.AppController;
import Model.Connection;
import Model.ConnectionsType;
import java.sql.Array;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectionUtil {

    public static java.sql.Connection connect(Connection c) throws SQLException {
        java.sql.Connection con = null;
        if (c == null) {
            return null;
        }
        try {
            String driver = c.getType();
            if (driver.equals("mySQL")) {
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                }catch(Exception e){}
                con = DriverManager.getConnection("jdbc:mysql://" + c.getServer() + "/piscisfactoria?useLegacyDatetimeCode=false&serverTimezone=UTC", c.getUserName(), c.getPassword());
            } else if (driver.equals("H2")) {
                //~/test
                try{
                    Class.forName("org.h2.Driver");
                 }catch(Exception e){}
                
                con = DriverManager.getConnection("jdbc:h2:"+c.getServer()+"/piscisfactoria");
            } else {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + c.getServer() + "/piscisfactoria?useLegacyDatetimeCode=false&serverTimezone=UTC", c.getUserName(), c.getPassword());
            }

            checkStructure(con);

        } catch (ClassNotFoundException ex) {
            Dialog.showError("ERROR", "ERROR conectando con DDBB", ex.toString());
        }
        return con;
    }

    public static ResultSet execQuery(java.sql.Connection con, String q, List<Object> params) throws SQLException {
        ResultSet result = null;
        if (con == null) {
            return null;
        }

        PreparedStatement ps = prepareQuery(con, q, params);
        result = ps.executeQuery();

        return result;
    }

    public static ResultSet execQuery(java.sql.Connection con, String q, Object param) throws SQLException {
        List<Object> params = new ArrayList<>();
        params.add(param);
        return execQuery(con, q, params);
    }

    public static int execUpdate(java.sql.Connection con, String q, List<Object> params, boolean insert) throws SQLException {
        if (con == null) {
            return -1;
        }

        PreparedStatement ps = prepareQuery(con, q, params);
        int result = ps.executeUpdate();
        //check if insert
        if (insert) {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);  //<-- return last id inserted
                } else {
                    return -1;
                }
            }
        } else {
            return result;
        }

    }

    public static int execUpdate(java.sql.Connection con, String q, Object param, boolean insert) throws SQLException {
        List<Object> params = new ArrayList<>();
        params.add(param);
        return execUpdate(con, q, params, insert);
    }

    /**
     *
     */
    public static int is(Integer n) {
        return 0;
    }
    public static int is(Float n) {
        return 1;
    }
    public static int is(Double n) {
        return 2;
    }
    public static int is(Boolean n) {
        return 3;
    }
    public static int is(String n) {
        return 4;
    }   
    public static int is(Array n){
        return 5;
    }
    public static int is(Object n) {
        return 6;
    }

    public static PreparedStatement prepareQuery(java.sql.Connection con, String q, List params) throws SQLException {
        PreparedStatement ps = null;
        ps = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS); //<-- solo para insert es útil
        if (params != null) {
            int i = 1;
            for (Object o : params) {
                switch (is(params)) {
                    case 0:
                        ps.setInt(i++, (Integer) o);
                        break;
                    case 1:
                        ps.setFloat(i++, (Float) o);
                        break;
                    case 2:
                        ps.setDouble(i++, (Double) o);
                        break;
                    case 3:
                        ps.setBoolean(i++, (Boolean) o);
                        break;
                    case 4:
                        ps.setString(i++, (String) o);
                        break;
                    case 5:
                        ps.setArray(i++, (Array) o);
                        break;
                    default:
                        ps.setObject(i++, o);
                }
            }
        }
        return ps;
    }

    public static void checkStructure(java.sql.Connection con) {
        try {
            String sql1,sql2;

            if (AppController.currentConnection.getType().equals(ConnectionsType.MYSQL.getType())) {              
                sql1 = "CREATE TABLE IF NOT EXISTS `jugador` ("
                    + "  `id` int(11) NOT NULL AUTO_INCREMENT,"
                    + "  `name` text NOT NULL,"
                    + "  `birthdate` datetime NOT NULL,"
                    + "  PRIMARY KEY (`id`)"
                    + ")";
                 sql2= "CREATE TABLE IF NOT EXISTS `channel` ("
                    + "  `id` int(11) NOT NULL AUTO_INCREMENT,"
                    + "  `type` text NOT NULL,"
                    + "  `value` text NOT NULL,"
                    + "  `id_contact` int(11) NOT NULL,"
                    + "  PRIMARY KEY (`id`)"
                    + ")";
                // sql3 = "ALTER TABLE `channel`"
                //        + "  ADD CONSTRAINT `CR` FOREIGN KEY (`id_contact`) REFERENCES `contactos` (`id`);";
            
            } else {
               sql1 = "CREATE TABLE IF NOT EXISTS contactos (id INT PRIMARY KEY auto_increment, name VARCHAR(255), birthdate VARCHAR(255));";
               sql2 = "CREATE TABLE IF NOT EXISTS channel (id INT PRIMARY KEY auto_increment, type VARCHAR(255), value VARCHAR(255), id_contact INT );";
            }
            con.setAutoCommit(false);
            ConnectionUtil.execUpdate(con, sql1, null, false);
            ConnectionUtil.execUpdate(con, sql2, null, false);
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException ex) {
            Dialog.showError("ERROR", "Error creando tablas", ex.toString());
        }
    }
}
