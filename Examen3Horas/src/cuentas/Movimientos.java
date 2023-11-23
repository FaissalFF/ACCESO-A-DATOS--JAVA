package cuentas;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"sucursal", "listaMovimientos"})
public class Movimientos {
	private String sucursal;
	private ArrayList<Movimiento> listaMovimientos;
	
	public Movimientos() {
		
	}
	
	public Movimientos(String sucursal, ArrayList<Movimiento> listaMovimientos) {
		super();
		this.sucursal = sucursal;
		this.listaMovimientos = listaMovimientos;
	}
	@XmlElement
	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	
	@XmlElement
	@XmlElementWrapper(name="listaMovimientos")
	public ArrayList<Movimiento> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(ArrayList<Movimiento> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}
	
	
}
