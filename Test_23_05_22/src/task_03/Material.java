package task_03;

public class Material {
	private int id;
	private String name;

	public Material() {}

	public Material(int id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Material [id=" + id + ", name=" + name + "]";
	}

}
