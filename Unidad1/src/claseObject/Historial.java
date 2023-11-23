package claseObject;

import java.io.Serializable;
import java.util.ArrayList;

import claseRAF.Nota;
import ProyectoFicherosN2.Alumno;

public class Historial implements Serializable{
	private Alumno a;
	ArrayList<Object[]> datos = new ArrayList<Object[]>();
	
	public Historial() {
		
	}

	public Alumno getA() {
		return a;
	}

	public void setA(Alumno a) {
		this.a = a;
	}

	public ArrayList<Object[]> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<Object[]> datos) {
		this.datos = datos;
	}

	@Override
	public String toString() {
		String resultado = "Historial [a=" + a + "]";
		
		for (Object[] objects : datos) {
			resultado += "\t"+objects[0]+"\n\t\t[nota="+objects[1]+"]\n";
		}
		return resultado;
	}
	
	
}
