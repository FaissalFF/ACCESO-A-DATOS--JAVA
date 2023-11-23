package ProyectoFicherosN;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Ej1 {
	public static Scanner t = new Scanner(System.in);
	public static String rutaCarpeta = "C:\\Users\\pdm_a\\AD";

	public static void main(String[] args) {
		int opcion=0;
		do {
			System.out.println("Selecciona una opción");
			System.out.println("0-Salir");
			System.out.println("1-Info miCarpeta");
			System.out.println("2-Mostrar contenido de miCarpeta");
			System.out.println("3-Crear fichero o carpeta");
			System.out.println("4-Renombrar fichero");
			System.out.println("5-Borrar fichero");
			System.out.println("6-Mostrar contenido de miCarpeta recursivo");
			opcion = t.nextInt();
			t.nextLine();
			
			switch(opcion) {
				case 1:
					info();
					break;
				case 2:
					mostrarContenido();
					
					break;
				case 3:
					crearObjeto();
					break;
				case 4:
					renombrarFichero();
					break;
				case 5:
					borrarObjeto();
					break;
				case 6:
					mostrarContenido(rutaCarpeta,0);
				
			}
		
		}while(opcion!=0);

	}

	private static void info() {
		File f1 = new File(rutaCarpeta);
		System.out.println("Existe: " + f1.exists());
		System.out.println("Se puede leer: " + f1.canRead());
		System.out.println("Es carpeta: " + f1.isDirectory());
		System.out.println("Ruta: " + f1.getAbsolutePath());
		System.out.println("Nombre: " + f1.getName());
		
	}

	private static void mostrarContenido() {
		File f1 = new File(rutaCarpeta);
		if(f1.exists()) {
			if(f1.canRead()) {
				File[] contenido = f1.listFiles();
				for(File f2 : contenido) {
					System.out.println(f2.getName());
				}
			}
		}
		
	}

	private static void crearObjeto() {
		System.out.print("Introduzca el nombre del objeto a crear: ");
		String nombre = t.nextLine();
		File f1 = new File(rutaCarpeta + "\\" + nombre);
		if(f1.exists()) {
			System.err.print("Error, el fichero ya existe");
		}else {
			System.out.println("Introduzca el tipo de objeto (f-Fichero//c-Carpeta): ");
			String tipo = t.nextLine();
			if(tipo.equalsIgnoreCase("f")) {
				try {
					if(f1.createNewFile()) {
						System.out.println("Fichero creado correctamente");
						System.out.println("Mostrar contenido?(Y/N):");
						String respuesta = t.nextLine();
						if(respuesta.equalsIgnoreCase("y")) {
							mostrarContenido();
						}
					}else{
						System.err.print("Error, no se ha podido crear el fichero");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(tipo.equalsIgnoreCase("c")) {
				if(f1.mkdir()) {
					System.out.println("Carpeta creada correctamente");
					System.out.println("Mostrar contenido?(Y/N):");
					String respuesta = t.nextLine();
					if(respuesta.equalsIgnoreCase("y")) {
						mostrarContenido();
					}
				}else {
					System.err.print("Error, la carpeta no se ha podido crear");
				}
			}else {
				System.err.print("Por favor, introduzca un valor válido");
			}
		}
		
		
	}

	private static void renombrarFichero() {
		System.out.println("Introduzca el nombre del fichero a renombrar: ");
		String nombre = t.nextLine();
		File f1 = new File(rutaCarpeta + "\\" + nombre);
		if(f1.exists()) {
			System.out.println("Introduzca el nuevo nombre: ");
			String nNom = t.nextLine();
			File f2 = new File(rutaCarpeta + "\\" + nNom);
			if(!f2.exists()) {
			if(f1.renameTo(f2)) {
			System.out.println("Fichero renombrado correctamente");
			System.out.println("Mostrar contenido?(Y/N):");
			String respuesta = t.nextLine();
			if(respuesta.equalsIgnoreCase("y")) {
				mostrarContenido();
			}
			}else{
				System.err.print("Error al renombrar el fichero");
			}
			}else {
				System.err.print("Error, no puedes usar ese nombre por que ya existe");
			}
		}
	}

	private static void borrarObjeto() {
		mostrarContenido();
		System.out.println("Introduzca el nombre del objeto a borrar: ");
		String nombre = t.nextLine();
		File f1 = new File(rutaCarpeta + "\\" + nombre);
		if(f1.exists()) {
			if(f1.delete()) {
				System.out.println("Fichero borrado correctamente");
				System.out.println("Mostrar contenido?(Y/N):");
				String respuesta = t.nextLine();
				if(respuesta.equalsIgnoreCase("y")) {
					mostrarContenido();
				}
			}else {
				System.err.print("Error, el fichero no se ha podido borrar");
			}
		}else {
			System.err.print("Error, fichero no existe");
		}
	}

	private static void mostrarContenido(String rutaCarpeta2, int i) {
		File f1 = new File(rutaCarpeta2);
		if(f1.exists()) {
			if(f1.canRead()) {
				File[] contenido = f1.listFiles();
				for(File f2 : contenido) {
					System.out.println(f2.getName());
					if(f2.isDirectory()) {
						i++;
						for(int j = 0; j < i; j++) {
						System.out.print("\t");
						}
						mostrarContenido(f2.getAbsolutePath(), i);
					}
				}
			}
		}
	}

}
