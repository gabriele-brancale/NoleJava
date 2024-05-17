package entity;

public class EntitySala {
	private int id;
	private int capienza;
	
	
	
	

	public EntitySala(int id, int capienza) {
		super();
		this.id = id;
		this.capienza = capienza;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCapienza() {
		return capienza;
	}
	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}
	
}
