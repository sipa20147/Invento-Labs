package jobtest.task3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnection {

    private Connection connection;

    public DbConnection(DbConfig config) throws SQLException {
        this.connection = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
        this.connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        this.connection.commit();
    }

    public void rollback() throws SQLException {
        this.connection.rollback();
    }

    public void close() throws SQLException {
        this.connection.close();
    }

    public PreparedStatement statement(String sql) throws SQLException {
        return this.connection.prepareStatement(sql);
    }
}
