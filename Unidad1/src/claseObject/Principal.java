package claseObject;

import java.util.ArrayList;
import java.util.Scanner;

import ProyectoFicherosN2.Alumno;
import proyectoFicherosB.Asignatura;

class Principal {
	
static Scanner t = new Scanner(System.in);
static claseRAF.Modelo adNotas = new claseRAF.Modelo("notas.bin");
static proyectoFicherosB.Modelo adAsigs = new proyectoFicherosB.Modelo("asignaturas.bin");
static ProyectoFicherosN2.Modelo adAlumnos = new ProyectoFicherosN2.Modelo("alumnos.txt");
public static Modelo adHistorial= new Modelo("historial.obj");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Crear Historial");
			System.out.println("2-Mostrar Historiales");
			System.out.println("3-Modificar Nota en el historial");
			System.out.println("4-Mostrar historial de un alumno");
			System.out.println("5-Borrar historial");
			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
			case 1:
				crearHistorial();
				break;
			case 2:
				mostrarHistorial();
				break;
			case 3:
				modificarNota();
				break;
			case 4:
				
				break;
			case 5:
				borrarHistorial();
				break;
			}

		} while (opcion != 0);
	}
	private static void crearHistorial() {
		ArrayList<Asignatura> as = adAsigs.mostrarAsigs();
		for (Asignatura asignatura : as) {
			System.out.println(asignatura);
		}
		System.out.println("Introduzca el dni: ");
		Alumno a = adAlumnos.mostrarAlumnoconDni(t.nextLine());
		Historial h = adHistorial.obtenerHistorial(a);
		
		
	}
	private static void mostrarHistorial() {
		// TODO Auto-generated method stub
		
	}
	private static void modificarNota() {
		// TODO Auto-generated method stub
		
	}
	private static void borrarHistorial() {
		// TODO Auto-generated method stub
		
	}

}
