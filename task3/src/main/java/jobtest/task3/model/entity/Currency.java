package jobtest.task3.model.entity;

public class Currency {

    private int id;
    private String name;

    public Currency() {
    }

    public Currency(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}