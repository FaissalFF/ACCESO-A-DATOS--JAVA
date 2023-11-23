package proyectoFicherosB;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Modelo {
	private String nombreFichero;
	public SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	private String nombreFichConfig = ".Miconfig";
	
	public Modelo(String fichero) {
		this.nombreFichero = fichero;
	}

	public Boolean darDeAlta(Asignatura a) {
		Boolean resultado = false;
		DataOutputStream d = null;
		try {
			d= new DataOutputStream(new FileOutputStream(nombreFichero, true));
			d.writeInt(a.getId());
			d.writeChars(a.getNombre() + "\t");
			d.writeLong(a.getFechaRD().getTime());
			d.writeFloat(a.getCreditos());
			d.writeBoolean(a.isActiva());
			resultado = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				d.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
		
	}

	public ArrayList<Asignatura> mostrarAsigs() {
		// TODO Auto-generated method stub
		ArrayList<Asignatura> asig = new ArrayList<Asignatura>();
		DataInputStream di = null;
		try {
			di = new DataInputStream(new FileInputStream(nombreFichero));
			while(true) {
			Asignatura a = new Asignatura();
			a.setId(di.readInt());
			char letra;
			String palabra = "";
			while((letra = di.readChar()) != '\t') {
				palabra = palabra + letra;
			}
			a.setNombre(palabra);
			a.setFechaRD(new Date(di.readLong()));
			a.setCreditos(di.readFloat());
			a.setActiva(di.readBoolean());
			asig.add(a);
			}
			
		}catch(EOFException e) {
		
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				di.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return asig;
	}

	public void bajaAsig(int nextInt) {
		//int = 4 Bytes
		//long = 4 Bytes
		//String = 2 Bytes * caracter
		//float = 4 bytes
		//double 8 bytes
		//boolean 2 Bytes
		File f1 = new File(nombreFichero);
		RandomAccessFile ra = null;
		try {
			ra = new RandomAccessFile(f1, "rw");
			ra.skipBytes(4);
			while(ra.readChar() != '\t') {
			}
			ra.skipBytes(8 + 4);
			ra.writeBoolean(false);
			for(Asignatura a : mostrarAsigs()) {
				System.out.println(a);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ra.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public int obtenerId() {
		// TODO Auto-generated method stub
		// Si el fichero .config existe debe devolver el nº
		// que hay en el fichero más 1 y modificar el nº del
		// fichero con el nuevo valor.

		// Si no existe, devuelve 1 y el fichero se crea con
		// el nº 1.
		int resultado = 1;

		// DEclarar el fichero de acceso aleatorio
		RandomAccessFile fA = null;

		try {
			// Chequear si existe .config
			File f = new File(nombreFichConfig);
			boolean existe = f.exists();
			// Abrir el fichero RandomAccessFile para leer y escribir (rw)
			fA = new RandomAccessFile(nombreFichConfig, "rw");
			if (existe) {
				// Recorrer el fichero y leer el nº
				while (true) {
					// Leer el nº
					resultado = fA.readInt() + 1;
					// Colocamos el apuntador del fichero al principio para sobreescribir el nº
					// Despalazamos hacia atrás(restamos) 4 Bytes
					fA.seek(fA.getFilePointer() - 4);
					// Escribimos el nuevo nº
					fA.writeInt(resultado);
				}
			} else {
				// Escribimos el nuevo nº
				fA.writeInt(resultado);
			}

		} catch (EOFException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (fA != null) {
				try {
					fA.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}
	
	public Asignatura obtenerAsignatura(int id) {
		Asignatura resultado = null;

		// TODO Auto-generated method stub
		DataInputStream ra = null;
		try {
			ra = new DataInputStream(new FileInputStream(nombreFichero));
			while (true) {
				if (ra.readInt() == id) {
					resultado = new Asignatura();

					resultado.setId(id);
					char letra;
					resultado.setNombre("");
					while ((letra = ra.readChar()) != '\n') {
						resultado.setNombre(resultado.getNombre() + letra);
					}
					resultado.setFechaRD(new Date(ra.readLong()));
					resultado.setCreditos(ra.readFloat());
					resultado.setActiva(ra.readBoolean());
					return resultado;
				} else {
					// Si la asignatura no es la buscada
					// Leer todos los datos para avanzar al siguiente id
					while (ra.readChar() != '\n') {
					}
					ra.readLong();
					ra.readFloat();
					ra.readBoolean();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("Aún no hay asignaturas");
		} catch (EOFException e) {

		} catch (IOException e) {

		} finally {
			if (ra != null) {
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

	public void borrarAsig(int id) {
		Asignatura resultado = new Asignatura();
		File fOriginal = new File(nombreFichero);
		File fTmp = new File("asignatura.tmp");
		RandomAccessFile ra = null;
		RandomAccessFile ra2 = null;
		try {
			ra = new RandomAccessFile(fOriginal, "rw");
			ra2 = new RandomAccessFile(fTmp, "rw");
			while(true) {
				int id2 = ra.readInt();
				if( id2 != id) {
				resultado.setId(id2);
				char letra;
				resultado.setNombre("");
				while ((letra = ra.readChar()) != '\t') {
					resultado.setNombre(resultado.getNombre() + letra);
				}
				resultado.setFechaRD(new Date(ra.readLong()));
				resultado.setCreditos(ra.readFloat());
				resultado.setActiva(ra.readBoolean());
				ra2.writeInt(resultado.getId());
				ra2.writeChars(resultado.getNombre() + "\t");
				ra2.writeLong(resultado.getFechaRD().getTime());
				ra2.writeFloat(resultado.getCreditos());
				ra2.writeBoolean(resultado.isActiva());
				}else {
					while (ra.readChar() != '\t') {
						int cont = 0;
					}
					ra.readLong();
					ra.readFloat();
					ra.readBoolean();
				}
			}
		}catch(EOFException e) {
		
		}catch (FileNotFoundException e) {
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
			if(ra2 != null) {
				try {
					ra2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fOriginal.delete()) {
				if(fTmp.renameTo(fOriginal)) {
					System.out.println("Asignatura Borrada correctamente");
				}else {
					System.out.println("Error al renombrar");
				}
			}else {
				System.out.println("Error al borrar el fichero");
			}
		}
	}
	
	
}
