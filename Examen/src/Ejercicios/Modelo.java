package Ejercicios;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

class Modelo implements Serializable{
	public static File f1 = new File("ventas.obj");
	public ArrayList<Ventas> obtenerVentas() {
		ArrayList<Ventas> ven = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("ventas.txt"));
			String linea = "";
			while((linea = br.readLine()) != null) {
				Ventas v = new Ventas();
				String [] datos = linea.split(";");
				v.setIdProducto(Integer.parseInt(datos[0]));
				v.setCantVendida(Float.parseFloat(datos[1]));
				v.setImpRecaudado(Float.parseFloat(datos[2]));
				ven.add(v);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ven;
	}

	public Ventas encontrarVenta(int idProducto) {
		// TODO Auto-generated method stub
		Ventas v = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("ventas.obj"));
			while(true) {
				Ventas v2 = (Ventas) ois.readObject();
			if(v2.getIdProducto() == idProducto) {
				v = new Ventas();
				v.setIdProducto(v2.getIdProducto());
				v.setCantVendida(v2.getCantVendida());
				v.setImpRecaudado(v2.getImpRecaudado());
			}
		}
		}catch(EOFException e) {
		
		}catch(FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
			}catch (IOException e) {
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
		if(v == null) {
			System.out.println("venta no existe");
		}
		return v;
	}

	public void aniadirVenta(Ventas v) {
		ObjectOutputStream oos = null;
		try {
			if(f1.exists()) {
				oos = new MiObjectOutputStream(new FileOutputStream("ventas.obj", true));
			}else {
				oos = new ObjectOutputStream(new FileOutputStream("ventas.obj", true));
			}
			oos.writeObject(v);
		}catch(EOFException e) {
		
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	public void acumularCant(Ventas ventas) {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		File f2 = new File("ventas.tmp");
		try {
			if(f2.exists()) {
				oos = new MiObjectOutputStream(new FileOutputStream(f2, true));
			}else {
				oos = new ObjectOutputStream(new FileOutputStream(f2, true));
			}
			ois = new ObjectInputStream(new FileInputStream("ventas.obj"));
			while(true) {
				Ventas v2 = (Ventas) ois.readObject();
				if(v2.getIdProducto() == ventas.getIdProducto()) {
					Ventas v3 = new Ventas();
					v3.setIdProducto(v2.getIdProducto());
					v3.setCantVendida(v2.getCantVendida() + ventas.getCantVendida());
					v3.setImpRecaudado(v2.getImpRecaudado() + ventas.getImpRecaudado());
					oos.writeObject(v3);
				}
			}
		}catch(EOFException e) {
			
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		f1.delete();
		f2.renameTo(f1);
		
		
	}

	public ArrayList<Ventas> mostrar() {
		ObjectInputStream ois = null;
		ArrayList<Ventas> v1 = new ArrayList<>();
		try {
			ois = new ObjectInputStream(new FileInputStream("ventas.obj"));
			while(true) {
				Ventas v = (Ventas) ois.readObject();
				v1.add(v);
			}
		}catch(EOFException e) {
			
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
		return v1;
	}

	public void escribirEnBinario(int id, String nombre, float stock) {
		File fB = new File("stock.bin");
		RandomAccessFile ra = null;
		try {
			ra = new RandomAccessFile(fB, "rw");
			ra.seek(ra.length());
			ra.writeInt(id);
			ra.writeChars(nombre + "\t");
			ra.writeFloat(stock);
		}catch(EOFException e) {} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(ra != null) {
				try {
					ra.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void mostrarB() {
		File f1 = new File("stock.bin");
		RandomAccessFile ra = null;
		try {
			ra = new RandomAccessFile(f1, "rw");
			while(true) {
				int id = ra.readInt();
				System.out.println("-----PRODUCTO " + id + "-----");
			System.out.println("id: " + id);
			String nombre = "";
			char letra;
			while((letra = ra.readChar()) != '\t') {
				nombre = nombre + letra;
			}
			System.out.println("nombre: " + nombre);
			System.out.println("stock: " + ra.readFloat());
			System.out.println();
			}
		}catch(EOFException e) {} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(ra != null) {
				try {
					ra.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	public Boolean buscarVentaB(int idBus) {
		File fB = new File("stock.bin");
		boolean encontrado = false;
		RandomAccessFile ra = null;
		try {
			ra = new RandomAccessFile(fB, "rw");
			while(ra.getFilePointer() < ra.length()) {
				if(idBus == ra.readInt()) {
					encontrado = true;
					break;
				}else {
					char letra;
					while((letra = ra.readChar()) != '\t') {
						
					}
					ra.readFloat();
				}
			}
		}catch(EOFException e) {} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(ra != null) {
				try {
					ra.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return encontrado;
	}
	
	

	public void modificarStock(int idBus, float nextFloat) {
		File fB = new File("stock.bin");
		boolean encontrado = false;
		RandomAccessFile ra = null;
		try {
			ra = new RandomAccessFile(fB, "rw");
			while(true) {
				if(idBus == ra.readInt()) {
					char letra;
					while((letra = ra.readChar()) != '\t') {
						
					}
					ra.writeFloat(nextFloat);
				}else {
					char letra;
					while((letra = ra.readChar()) != '\t') {
						
					}
					ra.readFloat();
				}
			}
		}catch(EOFException e) {} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(ra != null) {
				try {
					ra.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	public stock buscarVentaBi(int idBus) {
		File fB = new File("stock.bin");
		stock s = null;
		RandomAccessFile ra = null;
		try {
			ra = new RandomAccessFile(fB, "rw");
			while(ra.getFilePointer() < ra.length()) {
				s = new stock();
				int id = ra.readInt();
				if(idBus == id) {
					s.setId(id);
					char letra;
					s.setNombre("");
					while((letra = ra.readChar()) != '\t') {
						s.setNombre(s.getNombre() + letra);
					}
					s.setStock(ra.readFloat());
					break;
				}else {
					char letra;
					while((letra = ra.readChar()) != '\t') {
						
					}
					ra.readFloat();
				}
			}
		}catch(EOFException e) {} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(ra != null) {
				try {
					ra.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return s;
	}

	public void marshall(Info i) {
		try {
			Marshaller m = JAXBContext.newInstance(Info.class).createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(i, new File(i.getId() + ".xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Info unmarshall(Info i) {
		File f1 = new File(i.getId() + ".xml");
		Info i2 = new Info();
		try {
			Unmarshaller um = JAXBContext.newInstance(Info.class).createUnmarshaller();
			i2 = (Info) um.unmarshal(f1);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i2;
	}

}
