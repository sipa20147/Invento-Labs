package jobtest.task3.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jobtest.task3.db.DbConnection;
import jobtest.task3.model.entity.Measurement;

public class MeasurementRepository extends AbstractRepository<Measurement> {

    public MeasurementRepository(DbConnection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name FROM measurement";
    }

    @Override
    protected List<Measurement> parseResultSet(ResultSet result) throws SQLException {
        List<Measurement> list = new ArrayList<>();
        while (result.next()) {
            Measurement app = new Measurement();
            app.setId(result.getInt("id"));
            app.setName(result.getString("name"));
            list.add(app);
        }
        return list;
    }
}