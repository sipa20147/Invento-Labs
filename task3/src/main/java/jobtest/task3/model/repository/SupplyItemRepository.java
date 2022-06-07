package jobtest.task3.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jobtest.task3.db.DbConnection;
import jobtest.task3.model.entity.SupplyItem;

public class SupplyItemRepository extends AbstractRepository<SupplyItem> {

	public SupplyItemRepository(DbConnection connection) {
		super(connection);
	}

	@Override
	protected String getSelectQuery() {
		return "SELECT id, item, quantity, measurement_id, amount, supply_id, material_id, currency_id FROM supply_item";
	}

	@Override
	protected List<SupplyItem> parseResultSet(ResultSet result) throws SQLException {
		List<SupplyItem> list = new ArrayList<>();
		while (result.next()) {
			SupplyItem app = new SupplyItem();
			app.setId(result.getInt("id"));
			app.setItem(result.getInt("item"));
			app.setQuantity(result.getInt("quantity"));
			app.setMeasurementId(result.getInt("measurement_id"));
			app.setAmount(result.getDouble("amount"));
			app.setSupplyId(result.getInt("supply_id"));
			app.setMaterialId(result.getInt("material_id"));
			app.setCurrencyId(result.getInt("currency_id"));
			list.add(app);
		}
		return list;
	}

}
