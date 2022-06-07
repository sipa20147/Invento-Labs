package jobtest.task3.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jobtest.task3.db.DbConnection;
import jobtest.task3.model.entity.Material;

public class MaterialRepository extends AbstractRepository<Material> {

    public MaterialRepository(DbConnection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name FROM material";
    }

    @Override
    protected List<Material> parseResultSet(ResultSet result) throws SQLException {
        List<Material> list = new ArrayList<>();
        while (result.next()) {
            Material app = new Material();
            app.setId(result.getInt("id"));
            app.setName(result.getString("name"));
            list.add(app);
        }
        return list;
    }
}