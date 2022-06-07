package task_03;

import java.util.Date;

public class Supply {
	private int id;
	private String matDoc;
	private Date docDate;
	private Date pstngDate;
	private int employeeId;
	private boolean authSupply = false;

	public Supply() {
	}

	public Supply(int id, String matDoc, Date docDate, Date pstngDate, int employeeId, boolean authSupply) {
		this.id = id;
		this.matDoc = matDoc;
		this.docDate = docDate;
		this.pstngDate = pstngDate;
		this.employeeId = employeeId;
		this.authSupply = authSupply;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatDoc() {
		return matDoc;
	}

	public void setMatDoc(String matDoc) {
		this.matDoc = matDoc;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public Date getPstngDate() {
		return pstngDate;
	}

	public void setPstngDate(Date pstngDate) {
		this.pstngDate = pstngDate;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public boolean getAuthSupply() {
		return authSupply;
	}

	public void setAuthSupply(boolean authSupply) {
		this.authSupply = authSupply;
	}


	@Override
	public String toString() {
		return "Supply [id=" + id + ", matDoc=" + matDoc + ", docDate=" + docDate + ", pstngDate=" + pstngDate
				+ ", employeeId=" + employeeId + ", isAuthSupply=" + authSupply + "]";
	}

}
