package proyectoFicherosB;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

class Principal {
	static Scanner t = new Scanner(System.in);
	static Modelo ad = new Modelo("asignaturas.bin");
	public static void main(String[] args) {
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta Asignatura");
			System.out.println("2-Mostrar Asignaturas");
			System.out.println("3-Baja Asignatura");
			System.out.println("4-Borrar Asignatura");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1:
				altaAsignatura();
				break;
			case 2:
				mostrarAsignaturas();
				break;
			case 3:
				bajaAsignatura();
				break;
			case 4:
				borrarAsignatura();
				break;
			
			}

		} while (opcion != 0);

	}

	private static void altaAsignatura() {
		Asignatura a = new Asignatura();
		System.out.println("Introduzca el id: ");
		a.setId(t.nextInt());t.nextLine();
		System.out.println("Introduzca el nombre: ");
		a.setNombre(t.nextLine());
		System.out.println("Introduzca la fecha: ");
		try {
			a.setFechaRD(ad.formatoFecha.parse(t.nextLine()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Introduzca el numero de creditos: ");
		a.setCreditos(t.nextFloat());t.nextLine();
		a.setActiva(true);
		Boolean b = ad.darDeAlta(a);
		if(b == true) {
			System.out.println("Asignatura creada correctamente");
		}else {
			System.err.print("Error al crear la asignatura");
		}
	}

	private static void mostrarAsignaturas() {
		ArrayList<Asignatura> asig = ad.mostrarAsigs();
		for(Asignatura a : asig) {
			System.out.println(a);
		}
		
	}

	private static void bajaAsignatura() {
		System.out.println("Introduzca el id: ");
		ad.bajaAsig(t.nextInt());t.nextLine();
		
	}

	private static void borrarAsignatura() {
		System.out.println("Introduzca el id: ");
		ad.borrarAsig(t.nextInt());
		t.nextLine();
	}

}
