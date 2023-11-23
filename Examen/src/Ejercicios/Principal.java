package Ejercicios;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Principal{
static Scanner sc = new Scanner(System.in);
public static Modelo ad = new Modelo();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int opcion = 0;
        do {
            System.out.println("Selecciona una opción: ");
            System.out.println("[1] Ejercicio 1");
            System.out.println("[2] Ejercicio 2");
            System.out.println("[3] Ejercicio 3");
            System.out.println("[4] Ejercicio 4");
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
            }
        } while (opcion != 0);
	}
	private static void ejer1() {
		ArrayList<Ventas> ven = ad.obtenerVentas();
		File f1 = new File("ventas.obj");
		for (Ventas ventas : ven) {
			Ventas v = ad.encontrarVenta(ventas.getIdProducto());
			if(v == null) {
				ad.aniadirVenta(ventas);
			}else {
				ad.acumularCant(ventas);
			}
		}

		if(f1 != null) {
			ArrayList<Ventas> v1 = ad.mostrar();
			for (Ventas ventas2 : v1) {
				System.out.println(ventas2);
			}
		}
		ad.mostrar();
	}
	private static void ejer2() {
		ArrayList<Ventas>vn = ad.obtenerVentas();
		boolean encontrado;
		int [] num = new int[vn.size()];
		int cont = 0;
		for (Ventas ventas : vn) {
			encontrado = false;
			for (int i : num) {
				if(ventas.getIdProducto() == i) {
					encontrado = true;
					break;
				}
			}
			if(encontrado == false) {
				num[cont] = ventas.getIdProducto();
				cont ++;
			}
		}
		Arrays.sort(num);
		for (int i : num) {
			if(i != 0) {
			System.out.println("Introduzca el nombre del producto " + i + ": ");
			String nombre = sc.nextLine();
			System.out.println("Introduzca el stock del producto " + i + ": ");
			float stock = sc.nextFloat();
			sc.nextLine();
			ad.escribirEnBinario(i, nombre, stock);
			
		}
		}	
		ad.mostrarB();
	}
	private static void ejer3() {
		ArrayList<Ventas>vn = ad.obtenerVentas();
		boolean encontrado;
		File f1 = new File("stock.bin");
		ad.mostrarB();
		System.out.println("Introduzca el id de la fruta a venta: ");
		int idBus = sc.nextInt();
		sc.nextLine();
		if(ad.buscarVentaB(idBus)) {
			System.out.println("Introduzca el nuevo Stock: ");
			ad.modificarStock(idBus, sc.nextFloat());
			sc.nextLine();
			ad.mostrarB();
		}else {
			System.out.println("Venta no existe");
		}
		
	}
	private static void ejer4() {
		ad.mostrarB();
		System.out.println("Introduzca el id de la venta: ");
		int idBus = sc.nextInt();
		sc.nextLine();
		Ventas v = ad.encontrarVenta(idBus);
		stock s1 = ad.buscarVentaBi(idBus);
		ArrayList<Ventas> vn = new ArrayList<>();
		vn.add(ad.encontrarVenta(idBus));
		if(ad.buscarVentaB(idBus)) {
			Info i = new Info();
			i.setId(idBus);
			i.setNombre(s1.getNombre());
			i.setStock(s1.getStock());
			i.setVentas(vn);
			i.setRecaudado(v.getImpRecaudado());
			ad.marshall(i);
			Info i2 = ad.unmarshall(i);
			System.out.println(i2);
		}else {
			System.out.println("Venta no existe");
		}
	}

}
