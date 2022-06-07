package jobtest.task3.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jobtest.task3.db.DbConnection;
import jobtest.task3.exception.PersistException;

public abstract class AbstractRepository<T> implements GenericRepository<T> {

    protected DbConnection connection;

    public AbstractRepository(DbConnection connection) {
        this.connection = connection;
    }

    @Override
    public T getById(int key) throws SQLException {
        String sql = getSelectQuery() + " WHERE id = ?";
        try (PreparedStatement statement = this.connection.statement(sql)) {
            statement.setInt(1, key);
            ResultSet set = statement.executeQuery();
            List<T> list = parseResultSet(set);
            if (list.size() > 1) {
                throw new PersistException("Received more than one record");
            }
            if (list == null || list.isEmpty()) {
                return null;
            }
            return list.iterator().next();
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<T> getAll() throws SQLException {
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.statement(sql)) {
            ResultSet set = statement.executeQuery();
            return parseResultSet(set);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    protected abstract String getSelectQuery();

    protected abstract List<T> parseResultSet(ResultSet result) throws SQLException;
}
