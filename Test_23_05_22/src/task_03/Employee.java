package task_03;

public class Employee {
	private int id;
	private String accountName;
	private boolean isActive;
	private int applicationId;
	private int jobId;
	private int departamentId;
	
	public Employee() {
	}

	public Employee(int id, String accountName, boolean isActive, int applicationId, int jobId, int departamentId) {
		this.id = id;
		this.accountName = accountName;
		this.isActive = isActive;
		this.applicationId = applicationId;
		this.jobId = jobId;
		this.departamentId = departamentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getDepartamentId() {
		return departamentId;
	}

	public void setDepartamentId(int departamentId) {
		this.departamentId = departamentId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", accountName=" + accountName + ", isActive=" + isActive + ", applicationId="
				+ applicationId + ", jobId=" + jobId + ", departamentId=" + departamentId + "]";
	}

}
