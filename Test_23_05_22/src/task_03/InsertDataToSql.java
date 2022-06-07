package task_03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class InsertDataToSql {

	public InsertDataToSql() {
	}

	protected void insertToApplication(Connection connection, Application object) throws SQLException {
		String sql = "INSERT INTO application (id, name) VALUES (?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, object.getId());
		statement.setString(2, object.getName());
		statement.execute();
	}

	
	protected void insertToEmployee(Connection connection, Employee object) throws SQLException {
		String sql = "INSERT INTO employee (id, account_name, is_active, app_id, job_id, dep_id) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, object.getId());
		statement.setString(2, object.getAccountName());
		statement.setBoolean(3, object.getIsActive());
		statement.setInt(4, object.getApplicationId());
		statement.setInt(5, object.getJobId());
		statement.setInt(6, object.getDepartamentId());
		statement.execute();
	}

	protected void insertToJob(Connection connection, Job object) throws SQLException {
		String sql = "INSERT INTO job (id, name) VALUES (?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, object.getId());
		statement.setString(2, object.getName());
		statement.execute();
	}

	protected void insertToDepartament(Connection connection, Departament object) throws SQLException {
		String sql = "INSERT INTO departament (id, name) VALUES (?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, object.getId());
		statement.setString(2, object.getName());
		statement.execute();
	}

	protected void insertToMaterial(Connection connection, Material object) throws SQLException {
		String sql = "INSERT INTO material (id, name) VALUES (?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, object.getId());
		statement.setString(2, object.getName());
		statement.execute();
	}

	protected void insertToMeasurement(Connection connection, Measurement object) throws SQLException {
		String sql = "INSERT INTO measurement (id, name) VALUES (?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, object.getId());
		statement.setString(2, object.getName());
		statement.execute();
	}

	protected void insertToCurrency(Connection connection, Currency object) throws SQLException {
		String sql = "INSERT INTO currency (id, name) VALUES (?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, object.getId());
		statement.setString(2, object.getName());
		statement.execute();
	}

	protected void insertToSupplyItem(Connection connection, SupplyItem object) throws SQLException {
		String sql = "INSERT INTO supply_item (id, item, quantity, measurement_id, amount, supply_id, material_id, currency_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, object.getId());
		statement.setInt(2, object.getItem());
		statement.setInt(3, object.getQuantity());
		statement.setDouble(4, object.getMeasurementId());
		statement.setDouble(5, object.getAmount());
		statement.setInt(6, object.getSupplyId());
		statement.setInt(7, object.getMaterialId());
		statement.setInt(8, object.getCurrencyId());
		statement.execute();
	}

	protected void insertToSupply(Connection connection, Supply object) throws SQLException {
		String sql = "INSERT INTO supply (id, mat_doc, doc_date, pstng_date, employee_id, is_auth_supply) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, object.getId());
		statement.setString(2, object.getMatDoc());
		statement.setTimestamp(3, new Timestamp(object.getDocDate().getTime()));
		statement.setTimestamp(4, new Timestamp(object.getPstngDate().getTime()));
		
		if (object.getEmployeeId() == 0) {
			statement.setNull(5, java.sql.Types.INTEGER);
		} else {
			statement.setInt(5, object.getEmployeeId());
		}
		statement.setBoolean(6,  object.getAuthSupply());
		statement.execute();
	}
}
