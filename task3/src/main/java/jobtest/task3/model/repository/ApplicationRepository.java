package jobtest.task3.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jobtest.task3.db.DbConnection;
import jobtest.task3.model.entity.Application;

public class ApplicationRepository extends AbstractRepository<Application> {

    public ApplicationRepository(DbConnection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name FROM application";
    }

    @Override
    protected List<Application> parseResultSet(ResultSet result) throws SQLException {
        List<Application> list = new ArrayList<>();
        while (result.next()) {
            Application app = new Application();
            app.setId(result.getInt("id"));
            app.setName(result.getString("name"));
            list.add(app);
        }
        return list;
    }
}
