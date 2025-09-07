package mftplus.model.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.Getter;
public  class ConnectionProviderPostgres {
    @Getter

    private static ConnectionProviderPostgres provider = new ConnectionProviderPostgres();
    private ConnectionProviderPostgres() {}

    public  Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "138067sh"
        );
    }
}
