import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Select {
    public static void main(String[] args) {
        DBDriver();
        //define Direccionen de BD
        String uri ="jdbc:derby:MyDerbyDb;create=true";
        try {
            // abre coneccion
            Connection conn = DriverManager.getConnection(uri);
            select(conn);
            //cierra coneccion
            conn.close();
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
    private static void select (Connection conn){
        try {
            String select = "SELECT * FROM persona";
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1)+", "+rs.getString(2)+", "+rs.getInt(3));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
