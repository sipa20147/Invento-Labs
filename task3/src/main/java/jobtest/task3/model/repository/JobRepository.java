package jobtest.task3.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jobtest.task3.db.DbConnection;
import jobtest.task3.model.entity.Job;

public class JobRepository extends AbstractRepository<Job> {

    public JobRepository(DbConnection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name FROM job";
    }

    @Override
    protected List<Job> parseResultSet(ResultSet result) throws SQLException {
        List<Job> list = new ArrayList<>();
        while (result.next()) {
            Job app = new Job();
            app.setId(result.getInt("id"));
            app.setName(result.getString("name"));
            list.add(app);
        }
        return list;
    }
}