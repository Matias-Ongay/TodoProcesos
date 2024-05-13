package SQL;

import java.sql.SQLException;

public interface DataDeleter {
    void deleteDatabaseData() throws SQLException;
}