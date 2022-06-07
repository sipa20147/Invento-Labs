package jobtest.task3.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jobtest.task3.db.DbConnection;
import jobtest.task3.exception.PersistException;
import jobtest.task3.model.entity.Supply;

public class SupplyRepository extends AbstractRepository<Supply> {

    public SupplyRepository(DbConnection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, mat_doc, doc_date, pstng_date, employee_id, is_auth_supply FROM supply";
    }

    @Override
    protected List<Supply> parseResultSet(ResultSet result) throws SQLException {
        List<Supply> list = new ArrayList<>();
        while (result.next()) {
            Supply supply = new Supply();
            supply.setId(result.getInt("id"));
            supply.setMatDoc(result.getString("mat_doc"));
            supply.setDocDate(result.getDate("doc_date"));
            supply.setPstngDate(result.getDate("pstng_date"));
            supply.setEmployeeId(result.getInt("employee_id"));
            supply.setAuthSupply(result.getBoolean("is_auth_supply"));
            list.add(supply);
        }
        return list;
    }

    public List<Supply> getSupplyByAuthSupplyLimitTime(Date start, Date end, Boolean active) throws PersistException {
        String sql = getSelectQuery();
        if (start != null && end != null) {
            sql += " WHERE (pstng_date BETWEEN ? AND ?)";
        }
        if (active != null) {
            sql += " AND is_auth_supply=?";
        }
        try (PreparedStatement statement = this.connection.statement(sql)) {
            if (start != null && end != null) {
                statement.setDate(1, new java.sql.Date(start.getTime()));
                statement.setDate(2, new java.sql.Date(end.getTime()));
            }
            if (active != null) {
                statement.setBoolean(3, active);
            }
            ResultSet set = statement.executeQuery();
            return parseResultSet(set);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
