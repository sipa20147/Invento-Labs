package jobtest.task3.model.repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T> {

    public T getById(int key) throws SQLException;

    public List<T> getAll() throws SQLException;
}
