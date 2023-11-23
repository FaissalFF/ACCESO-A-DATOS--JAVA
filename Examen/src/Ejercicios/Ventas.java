package Ejercicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement
@XmlType(propOrder = {"cantVendida", "impRecaudado"})
class Ventas implements Serializable{
	private int idProducto;
	private float cantVendida;
	private float impRecaudado;
	
	public Ventas() {
		
	}

	public Ventas(int idProducto, float cantVendida, float impRecaudado) {
		this.idProducto = idProducto;
		this.cantVendida = cantVendida;
		this.impRecaudado = impRecaudado;
	}
	@XmlAttribute
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	@XmlElement
	public float getCantVendida() {
		return cantVendida;
	}

	public void setCantVendida(float cantVendida) {
		this.cantVendida = cantVendida;
	}

	public float getImpRecaudado() {
		return impRecaudado;
	}
	@XmlElement
	public void setImpRecaudado(float impRecaudado) {
		this.impRecaudado = impRecaudado;
	}
	@Override
	public String toString() {
		return "Ventas [idProducto=" + idProducto + ", cantVendida=" + cantVendida + ", impRecaudado=" + impRecaudado
				+ "]";
	}
	
}
