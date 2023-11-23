package Ejercicios;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "nombre", "stock", "ventas", "recaudado"})
public class Info {
	private int id;
	private String nombre;
	private float stock;
	ArrayList<Ventas> Ventas;
	private float recaudado;
	
	public Info() {
		
	}
	
	
	public Info(int id, String nombre, float stock, float recaudado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
		this.recaudado = recaudado;
	}

	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public float getStock() {
		return stock;
	}
	public void setStock(float stock) {
		this.stock = stock;
	}
	
	@XmlElement
	public ArrayList<Ventas> getVentas() {
		return Ventas;
	}


	public void setVentas(ArrayList<Ventas> ventas) {
		Ventas = ventas;
	}


	@XmlElement
	public float getRecaudado() {
		return recaudado;
	}
	public void setRecaudado(float recaudado) {
		this.recaudado = recaudado;
	}


	@Override
	public String toString() {
		for (Ventas ventas2 : Ventas) {
			System.out.println("	id: " + ventas2.getIdProducto());
			System.out.println("	Cantidad Vendida: " + ventas2.getCantVendida());
			System.out.println("	Cantidad Vendida: " + ventas2.getImpRecaudado());
		
	}
		return "Info [id=" + id + ", nombre=" + nombre + ", stock=" + stock  + ", recaudado="
				+ recaudado + " Ventas:" +"]";
		
	}
}