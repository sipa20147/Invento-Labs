package jobtest.task3.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jobtest.task3.db.DbConnection;
import jobtest.task3.model.entity.Department;

public class DepartmentRepository extends AbstractRepository<Department> {

    public DepartmentRepository(DbConnection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name FROM department";
    }

    @Override
    protected List<Department> parseResultSet(ResultSet result) throws SQLException {
        List<Department> list = new ArrayList<>();
        while (result.next()) {
            Department app = new Department();
            app.setId(result.getInt("id"));
            app.setName(result.getString("name"));
            list.add(app);
        }
        return list;
    }
}