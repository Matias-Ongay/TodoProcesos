package SQL;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableSQLite {
    private ConnectionSQLite connectionSQLite;

      public void createTableTask() {
        // Script SQL para crear la tabla 'task' si no existe y agregar la columna 'status'
        String sql = "CREATE TABLE IF NOT EXISTS task (\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    description TEXT NOT NULL,\n"
                + "    endDate TEXT NOT NULL,\n"
                + "    priority TEXT NOT NULL,\n"
                + "    userId TEXT NOT NULL,\n"
                + "    status TEXT NOT NULL\n" // Agrega la columna 'status'
                + ");";

        try (Connection conn = connectionSQLite.conectar();
             Statement stmt = conn.createStatement()) {
            // Crea la tabla si no existe y agrega la columna 'status'
            stmt.execute(sql);
            System.out.println("Tabla 'task' verificada o creada con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
