import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCbasic {
    public static void main (String[] args){
        DBDriver();
        //define Direccionen de BD
        String uri ="jdbc:derby:MyDerbyDb;create=true";
        try {
            // abre coneccion
            Connection conn = DriverManager.getConnection(uri);
            createTables(conn);
            //cierra coneccion
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void createTables(Connection conn) {
        String table=
                "CREATE TABLE persona(id INT, " +
                        "nombre VARCHAR(20)," +
                        "edad INT," +
                        "PRIMARY KEY(id))";
        //Ejecutar CREATE
        try {
            conn.prepareStatement(table).execute();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
