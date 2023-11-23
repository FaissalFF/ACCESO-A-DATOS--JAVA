package Ejercicios;

public class stock {
	private int id;
	private String nombre;
	private float stock;
	
	public stock() {
		
	}

	public stock(int id, String nombre, float stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getStock() {
		return stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "stock [id=" + id + ", nombre=" + nombre + ", stock=" + stock + "]";
	}
	
	
}
