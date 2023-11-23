package ProyectoFicherosN2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

class Principal {

	public static Scanner t = new Scanner(System.in);

	// Declaramos el objeto modelo que accede a los datos
	public static Modelo ad = new Modelo("alumnos.txt");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta Alumno");
			System.out.println("2-Mostrar Alumnos");
			System.out.println("3-Baja Alumno");
			System.out.println("4-Borrar Alumno");
			System.out.println("5-Mostrar por dni");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1:
				altaAlumno();
				break;
			case 2:
				mostrarAlumnos();
				break;
			case 3:
				bajaAlumno();
				break;
			case 4:
				borrarAlumno();
				break;
			case 5:
				mostrarPorDni();
				break;

			}

		} while (opcion != 0);

}

	private static void altaAlumno() {
		Alumno a = new Alumno();
		System.out.println("Introduzca el dni: ");
		a.setDni(t.nextLine());
		System.out.println("Introduzca el nombre: ");
		a.setNombre(t.nextLine());
		System.out.println("Introduzca la fecha de nacimiento (dd/MM/yyyy): ");
		try {
			a.setFechaN(ad.formatoFecha.parse(t.nextLine()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Introduzca el numero de expediente: ");
		a.setNumExp(t.nextInt());
		t.nextLine();
		System.out.println("Introduzca la estatura: ");
		a.setEstatura(t.nextFloat());
		a.setActivo(true);
		
		if(ad.altaAlumno(a)) {
			System.out.println("Alumno creado correctamente");
		}else {
			System.err.print("Hubo un error al crear el alimno");
		}
		
	}

	private static void mostrarAlumnos() {
		ArrayList<Alumno> a = ad.mostrarAlumnos();
		for(Alumno al : a) {
			System.out.println(al);
		}
		
	}

	private static void bajaAlumno() {
		System.out.println("Introduzca el DNI: ");
		ad.bajaAlumno(t.nextLine());
		
	}

	private static void borrarAlumno() {
		System.out.println("Introduzca el DNI: ");
		ad.borrarAlumno(t.nextLine());
		
	}

	private static void mostrarPorDni() {
		System.out.println("Introduzca el DNI: ");
		ad.mostrarAlumnoconDni(t.nextLine());
		
	}
}