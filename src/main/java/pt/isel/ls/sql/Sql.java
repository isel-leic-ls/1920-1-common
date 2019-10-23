package pt.isel.ls.sql;

import java.sql.SQLException;

public final class Sql {

    private Sql() {
        // no instances
    }

    public static boolean isUniqueViolation(SQLException e) {
        return "23505".equals(e.getSQLState());
    }

}
