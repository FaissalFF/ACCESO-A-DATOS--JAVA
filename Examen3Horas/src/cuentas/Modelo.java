package cuentas;

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
import java.util.Random;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import cuentas.MiObjectOutputStream;

public class Modelo implements Serializable{

	public Modelo() {
		
	}

	public boolean escribirBin(Cuentas cuentas) {
		File f1 = new File("cuentas.bin");
		boolean resultado = false;
		RandomAccessFile ra = null;
		try {
			ra = new RandomAccessFile(f1, "rw");
				ra.seek(ra.length());
				ra.writeInt(cuentas.getCodigo());
				ra.writeChars(cuentas.getApellidos() + "\t");
				ra.writeChars(cuentas.getNombre() + "\t");
				ra.writeFloat(cuentas.getSaldo());
				ra.writeBoolean(cuentas.isCancelada());
				resultado = true;
		}catch(EOFException e) {} catch (FileNotFoundException e) {
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
		return resultado;
	}

	public ArrayList<Cuentas> obtenerCuentas() {
		ArrayList<Cuentas> c = new ArrayList<Cuentas>();;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("cuentas.txt"));
			String linea = "";
			while((linea = br.readLine()) != null) {
				String [] datos = linea.split(";");
				Cuentas c1 = new Cuentas();
				c1.setCodigo(Integer.parseInt(datos[0]));
				c1.setApellidos(datos[1]);
				c1.setNombre(datos[2]);
				c1.setSaldo(Float.parseFloat(datos[3]));
				c1.setCancelada(false);
				c.add(c1);
			}
		}catch(EOFException e) {} catch (FileNotFoundException e) {
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
		
		return c;
	}

	public ArrayList<Cuentas> mostrarBin() {
		File f1 = new File("cuentas.bin");
		// TODO Auto-generated method stub
		ArrayList<Cuentas> c = new ArrayList<Cuentas>();
		RandomAccessFile ra = null;
		try {
			ra = new RandomAccessFile(f1, "r");
			while(true) {
				Cuentas c1 = new Cuentas();
				c1.setCodigo(ra.readInt());
				c1.setApellidos("");
				char letra;
				while((letra = ra.readChar()) != '\t') {
					c1.setApellidos(c1.getApellidos() + letra);
				}
				c1.setNombre("");
				char letra2;
				while((letra2 = ra.readChar()) != '\t') {
					c1.setNombre(c1.getNombre() + letra2);
				}
				c1.setSaldo(ra.readFloat());
				c1.setCancelada(ra.readBoolean());
				c.add(c1);
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
		return c;
	}

	public Cuentas obtenerCuenta(int numero) {
		Cuentas c = null;
		for (Cuentas cuenta : obtenerCuentas()) {
			if(cuenta.getCodigo() == numero) {
				c = cuenta;
				break;
			}
		}
		return c;
	}

	public void ingresarDinero(float cantidad, Cuentas c2) {
		File f1 = new File("cuentas.bin");
		// TODO Auto-generated method stub
		ArrayList<Cuentas> c = new ArrayList<Cuentas>();
		RandomAccessFile ra = null;
		try {
			ra = new RandomAccessFile(f1, "rw");
			while(true) {
				int codC = ra.readInt();
				if(codC == c2.getCodigo()) {
					ra.seek(ra.getFilePointer() + ((c2.getApellidos().length() * 2) + 2) + ((c2.getNombre().length() * 2) + 2));
					ra.writeFloat(c2.getSaldo() + cantidad);
					break;
				}else {
					char letra1;
					while((letra1 = ra.readChar()) != '\t') {
						
					}
					char letra2;
					while((letra2 = ra.readChar()) != '\t') {
						
					}
					ra.readFloat();
					ra.readBoolean();
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

	public void retirarDinero(float cantidad, Cuentas c) {
		File f1 = new File("cuentas.bin");
		// TODO Auto-generated method stub
		ArrayList<Cuentas> c1 = new ArrayList<Cuentas>();
		RandomAccessFile ra = null;
		try {
			ra = new RandomAccessFile(f1, "rw");
			while(true) {
				int codC = ra.readInt();
				if(codC == c.getCodigo()) {
					ra.seek(ra.getFilePointer() + ((c.getApellidos().length() * 2) + 2) + ((c.getNombre().length() * 2) + 2));
					ra.writeFloat(c.getSaldo() - cantidad);
					break;
				}else {
					char letra1;
					while((letra1 = ra.readChar()) != '\t') {
						
					}
					char letra2;
					while((letra2 = ra.readChar()) != '\t') {
						
					}
					ra.readFloat();
					ra.readBoolean();
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

	public void escribirObj(Cuentas cuentas) {
		File f1 = new File("cuentas.obj");
		ObjectOutputStream oos = null;
		try {
			if(f1.exists()) {
				oos = new MiObjectOutputStream(new FileOutputStream("cuentas.obj", true));
			}else {
				oos = new ObjectOutputStream(new FileOutputStream("cuentas.obj", true));
			}
			oos.writeObject(cuentas);
		}catch(EOFException e) {} catch(FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
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

	public ArrayList<Cuentas> leerObj() {
		// TODO Auto-generated method stub
		ArrayList<Cuentas> c = new ArrayList<Cuentas>();
		File f1 = new File("cuentas.obj");
		ObjectInputStream ois = null;
		try {
			if(f1.exists()) {
			ois = new ObjectInputStream(new FileInputStream(f1));
			}
			while(true) {
				Cuentas c1 = (Cuentas) ois.readObject();
				c.add(c1);
			}
		}catch(EOFException e) {}catch(FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
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
		return c;
	}

	public void cancelarCuenta(int id) {
		File f1 = new File("cuentas.obj");
		File ftmp = new File("cuentas.tmp");
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		try {
			if(f1.exists()) {
			ois = new ObjectInputStream(new FileInputStream(f1));
			}
			if(!ftmp.exists()) {
				oos = new ObjectOutputStream(new FileOutputStream(ftmp, true));
			}else {
				oos = new MiObjectOutputStream(new FileOutputStream(ftmp, true));
			}
			while(true) {
				Cuentas c = (Cuentas) ois.readObject();
				if(c.getCodigo() == id) {
					c.setCancelada(true);
					oos.writeObject(c);
				}else {
					oos.writeObject(c);
				}
			}
		}catch(EOFException e) {}catch(FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
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
			f1.delete();
			ftmp.renameTo(f1);
		}
		
	}

	public void marshall(Movimientos m) {
		try {
			Marshaller m2 = JAXBContext.newInstance(Movimientos.class).createMarshaller();
			m2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m2.marshal(m, new File(m.getSucursal() + ".xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
