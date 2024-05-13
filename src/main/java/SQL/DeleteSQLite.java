package SQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteSQLite {
    private ConnectionSQLite connectionSQLite;
    public void deleteDatabaseData(){
        String sql= "DELETE FROM task";
        try (Connection conn = connectionSQLite.conectar();
             Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("DELETE OF DATABASE DATA");
        }catch(SQLException e){
            System.out.println("ERROR"+ e.getMessage());
        }
    }
}
