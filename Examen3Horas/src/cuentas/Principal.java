package cuentas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Principal implements Serializable{
		static Scanner sc = new Scanner(System.in);
		public static Modelo ad = new Modelo();
		public static Movimientos m = new Movimientos();
			public static void main(String[] args) {
				// TODO Auto-generated method stub
		        int opcion = 0;
		        do {
		            System.out.println("Selecciona una opción: ");
		            System.out.println("[1] Ejercicio 1");
		            System.out.println("[2] Ejercicio 2");
		            System.out.println("[3] Ejercicio 3");
		            System.out.println("[4] Ejercicio 4");
		            System.out.println("[5] Ejercicio 5");
		            System.out.println("[6] Ejercicio 6");
		            System.out.println("[7] Ejercicio 7");
		            opcion = sc.nextInt();
		            sc.nextLine();
		            switch (opcion) {
		            case 1:
		                ejer1();
		                break;
		            case 2:
		                ejer2();
		                break;
		            case 3:
		                ejer3();
		                break;
		            case 4:
		                ejer4();
		                break;
		            case 5:
		                ejer5();
		                break;
		            case 6:
		                ejer6();
		                break;
		            case 7:
		                ejer7();
		                break;
		            }
		       } while (opcion != 0);
	}
			private static void ejer1() {
				ArrayList<Cuentas> cu = ad.obtenerCuentas();
				if(cu == null) {
					System.out.println("No hay cuentas");
				}else {
					for (Cuentas cuentas : cu) {
						if(ad.escribirBin(cuentas)) {
							System.out.println("Fichero Escrito Correctamente");
						}else {
							System.out.println("Error al escribir");
						}
					}
					
				}
				
			}
			private static void ejer2() {
				ArrayList<Cuentas> c = ad.mostrarBin();
				for (Cuentas cuentas : c) {
					System.out.println(cuentas);
				}
				
				
			}
			@SuppressWarnings("unused")
			private static void ejer3() {
				System.out.println("Introduzca el codigo de su cuenta: ");
				int numero = sc.nextInt();
				sc.nextLine();
				Cuentas c = ad.obtenerCuenta(numero);
				if(c.isCancelada() == true) {
					System.out.println("Lo sentimos, su cuenta está cancelada");
				}else if(c == null) {
					System.out.println("Cuenta no existente");
				}else {
					System.out.println("Qué Movimiento desea hacer? (0-Ingreso/1-Reintegro)");
					int eleccion = sc.nextInt();
					sc.nextLine();
					if(eleccion == 0) {
						System.out.println("Cuanto Dinero desea Ingresar?: ");
						float cantidad = sc.nextFloat();
						sc.nextLine();
						ad.ingresarDinero(cantidad, c);
					}else if (eleccion == 1){
						System.out.println("Cuanto Dinero desea Ingresar?: ");
						float cantidad = sc.nextFloat();
						sc.nextLine();
						if(c.getSaldo() > cantidad) {
							ad.retirarDinero(cantidad, c);
						}else {
							System.out.println("No tienes suficiente saldo como para hacer esta operación");
						}

					}else {
						System.out.println("Por favor, Introduzca un valor válido");
					}
				}
				
			}
			private static void ejer4() {
				ArrayList<Cuentas>c = ad.mostrarBin();
				for (Cuentas cuentas : c) {
					ad.escribirObj(cuentas);
				}
			}
			private static void ejer5() {
				ArrayList<Cuentas>c = ad.leerObj();
				for (Cuentas cuentas : c) {
					System.out.println(cuentas);
				}
				
			}
			private static void ejer6() {
				ArrayList<Cuentas>c = ad.leerObj();
				for (Cuentas cuentas : c) {
					System.out.println(cuentas);
				}
				System.out.println("Introduzca el id de la cuenta a cancelar: ");
				int id = sc.nextInt();
				sc.nextLine();
				if(ad.obtenerCuenta(id) != null) {
					ad.cancelarCuenta(id);
				}else {
					System.out.println("La cuenta no existe");
				}
				
			}
			private static void ejer7() {
				System.out.println("Ingrese el tipo de movimiento (I-Ingreso/R-Reintegro): ");
				String tipo = sc.nextLine();
				if(tipo.equalsIgnoreCase("I")) {
				System.out.println("Ingrese el nombre de la sucursal: ");
				String sucursal = sc.nextLine();
				m.setSucursal(sucursal);
				ArrayList<Movimiento> mov = new ArrayList<>();
				System.out.println("Ingrese el número de su cuenta: ");
				int cod = sc.nextInt();
				sc.nextLine();
				Cuentas c = ad.obtenerCuenta(cod);
				if(c != null) {
					System.out.println("Ingrese la cantidad: ");
					float cantidad = sc.nextFloat();
					sc.nextLine();
					Movimiento m2 = new Movimiento();
					m2.setApellidos(c.getApellidos());
					m2.setNombre(c.getNombre());
					m2.setCuenta(cod);
					m2.setImporte(cantidad);
					m2.setTipo("I");
					mov.add(m2);
					m.setListaMovimientos(mov);
					ad.marshall(m);
				}else if(c.isCancelada() == true) {
					System.out.println("Cuenta Cancelada");
				}else {
					System.out.println("Cuenta no existe");
				}
				}else if(tipo.equalsIgnoreCase("R")) {
					System.out.println("Ingrese el nombre de la sucursal: ");
					String sucursal = sc.nextLine();
					m.setSucursal(sucursal);
					ArrayList<Movimiento> mov = new ArrayList<>();
					System.out.println("Ingrese el número de su cuenta: ");
					int cod = sc.nextInt();
					sc.nextLine();
					Cuentas c = ad.obtenerCuenta(cod);
					if(c != null) {
						System.out.println("Ingrese la cantidad: ");
						float cantidad = sc.nextFloat();
						sc.nextLine();
						if(cantidad < c.getSaldo()) {
							Movimiento m2 = new Movimiento();
							m2.setApellidos(c.getApellidos());
							m2.setNombre(c.getNombre());
							m2.setCuenta(cod);
							m2.setImporte(cantidad);
							m2.setTipo("R");
							mov.add(m2);
							m.setListaMovimientos(mov);
							ad.marshall(m);
						}else {
							System.out.println("No tienes suficiente Saldo");
						}
						
					}else if(c.isCancelada() == true) {
						System.out.println("Cuenta Cancelada");
					}else {
						System.out.println("Cuenta no existe");
					}
				}else {
					System.out.println("Por favor, introduzca un valor válido");
				}

				
				
			}
}