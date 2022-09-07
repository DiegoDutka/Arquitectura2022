import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
    public static void main(String[] args) {
        DBDriver();
        //define Direccionen de BD
        String uri ="jdbc:derby:MyDerbyDb;create=true";
        try {
            // abre coneccion
            Connection conn = DriverManager.getConnection(uri);
            insert(conn,1,"lolo",20);

            //cierra coneccion
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void insert(Connection conn, int id, String name, int years) throws SQLException {
        String insert = "INSERT INTO persona (id, nombre, edad) VALUES(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1,  id);
        ps.setString(2, name);
        ps.setInt(3, years);
        ps.executeUpdate();
        ps.close();
        conn.commit();

    }




    private static void DBDriver() {
        //Instancia BD
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
