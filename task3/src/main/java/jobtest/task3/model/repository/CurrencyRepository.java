package jobtest.task3.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jobtest.task3.db.DbConnection;
import jobtest.task3.model.entity.Currency;

public class CurrencyRepository extends AbstractRepository<Currency> {

    public CurrencyRepository(DbConnection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, name FROM currency";
    }

    @Override
    protected List<Currency> parseResultSet(ResultSet result) throws SQLException {
        List<Currency> list = new ArrayList<>();
        while (result.next()) {
            Currency app = new Currency();
            app.setId(result.getInt("id"));
            app.setName(result.getString("name"));
            list.add(app);
        }
        return list;
    }
}