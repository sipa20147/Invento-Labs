package jobtest.task3.model.entity;

public class Employee {
    private int id;
    private String name;
    private boolean isActive;
    private int appId;
    private int jobId;
    private int depId;

    public Employee() {
    }

    public Employee(int id, String name, boolean isActive, int appId, int jobId, int depId) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.appId = appId;
        this.jobId = jobId;
        this.depId = depId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }
}
