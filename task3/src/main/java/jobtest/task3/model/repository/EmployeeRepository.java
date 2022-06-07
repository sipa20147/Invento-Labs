package jobtest.task3.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jobtest.task3.db.DbConnection;
import jobtest.task3.exception.PersistException;
import jobtest.task3.model.entity.Employee;

public class EmployeeRepository extends AbstractRepository<Employee> {

    public EmployeeRepository(DbConnection connection) {
        super(connection);
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT id, account_name, is_active, app_id, job_id, dep_id FROM employee";
    }

    @Override
    protected List<Employee> parseResultSet(ResultSet result) throws SQLException {
        List<Employee> list = new ArrayList<>();
        while (result.next()) {
            Employee employee = new Employee();
            employee.setId(result.getInt("id"));
            employee.setName(result.getString("account_name"));
            employee.setIsActive(result.getBoolean("is_active"));
            employee.setAppId(result.getInt("app_id"));
            employee.setJobId(result.getInt("job_id"));
            employee.setDepId(result.getInt("dep_id"));
            list.add(employee);
        }
        return list;
    }

    public List<Employee> getEmployesByActive(boolean active) throws PersistException {
        String sql = getSelectQuery() + " WHERE is_active = ?";
        try (PreparedStatement statement = this.connection.statement(sql)) {
            statement.setBoolean(1, active);
            ResultSet set = statement.executeQuery();
            return parseResultSet(set);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
