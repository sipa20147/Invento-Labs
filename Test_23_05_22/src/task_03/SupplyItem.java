package task_03;

public class SupplyItem {
	private int id;
	private int item;
	private int quantity;
	private int measurementId;
	private double amount;
	private int supplyId;
	private int materialId;
	private int currencyId;
	
	public SupplyItem() {}

	public SupplyItem(int id, int item, int quantity, int measurementId, double amount, int supplyId,
			int materialId, int currencyId) {
		this.id = id;
		this.item = item;
		this.quantity = quantity;
		this.measurementId = measurementId;
		this.amount = amount;
		this.supplyId = supplyId;
		this.materialId = materialId;
		this.currencyId = currencyId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(int measurementId) {
		this.measurementId = measurementId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	@Override
	public String toString() {
		return "SupplyItem [id=" + id + ", item=" + item + ", quantity=" + quantity
				+ ", measurementId=" + measurementId + ", amount=" + amount + ", supplyId=" + supplyId + ", materialId="
				+ materialId + ", currencyId=" + currencyId + "]";
	}

}
