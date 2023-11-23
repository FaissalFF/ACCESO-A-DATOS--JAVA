package cuentas;

import java.io.Serializable;

public class Cuentas implements Serializable{
	private int codigo; //4
	private String apellidos; //length *2
	private String nombre; //length *2
	private float saldo; //4
	private boolean cancelada; //1
	
	public Cuentas() {
		
	}
	
	public Cuentas(int codigo, String apellidos, String nombre, float saldo, boolean cancelada) {
		super();
		this.codigo = codigo;
		this.apellidos = apellidos;
		this.nombre = nombre;
		this.saldo = saldo;
		this.cancelada = cancelada;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public boolean isCancelada() {
		return cancelada;
	}
	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	@Override
	public String toString() {
		return "Cuentas [codigo=" + codigo + ", apellidos=" + apellidos + ", nombre=" + nombre + ", saldo=" + saldo
				+ ", cancelada=" + cancelada + "]";
	}
	
	
}
