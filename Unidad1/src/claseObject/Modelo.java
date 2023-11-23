package claseObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ProyectoFicherosN2.Alumno;

class Modelo {
public static String nombreFichero;
	public Modelo(String nombreFichero){
		this.nombreFichero = nombreFichero;
	}
	public Historial obtenerHistorial(Alumno a) {
		// TODO Auto-generated method stub
		Historial h = new Historial();
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(nombreFichero));
			while(true) {
				Historial h1 = (Historial) ois.readObject();
				if(h1.getA().getDni().equalsIgnoreCase(a.getDni())) {
					h = h1;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return h;
	}

}
