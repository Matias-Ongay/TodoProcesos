package SQL;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableSQLite implements TableCreator{
    private ConnectionSQLite connectionSQLite;

    public CreateTableSQLite() {
        this.connectionSQLite = new ConnectionSQLite();
    }

      public void createTableTask() {
        System.out.println("Creando tabla 'task' en la base de datos SQLite...");
        // Script SQL para crear la tabla 'task' si no existe y agregar la columna 'status'
        String sql = "CREATE TABLE IF NOT EXISTS task (\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    description TEXT NOT NULL,\n"
                + "    endDate TEXT NOT NULL,\n"
                + "    priority TEXT NOT NULL,\n"
                + "    userId TEXT NOT NULL,\n"
                + "    status TEXT NOT NULL\n"
                + ");";
        try (Connection conn = connectionSQLite.conectar();
        Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
