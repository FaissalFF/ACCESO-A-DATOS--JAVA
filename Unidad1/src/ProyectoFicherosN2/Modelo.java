package ProyectoFicherosN2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Modelo {
	private String nombreFichero;
	public SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

	public Modelo(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	public boolean altaAlumno(Alumno a) {
		// TODO Auto-generated method stub
		Boolean creado = false;
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(nombreFichero, true));
			bw.write(a.getDni() + ";" + a.getNombre() + ";" + formatoFecha.format(a.getFechaN()) + ";" + a.getNumExp() + ";" + a.getEstatura() + ";" + a.isActivo() + "\n");
			creado = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(bw != null) {
				bw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return creado;
	}

	public ArrayList<Alumno> mostrarAlumnos() {
		BufferedReader br = null;
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		try {
			br = new BufferedReader(new FileReader(nombreFichero));
			String linea = "";
			while((linea = br.readLine()) != null) {
				String [] datos = linea.split(";");
				listaAlumnos.add(new Alumno(datos[0], datos[1], formatoFecha.parse(datos[2]), Integer.parseInt(datos[3]), 
						Float.parseFloat(datos[4]), Boolean.parseBoolean(datos[5])));
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaAlumnos;
		
	}

	public void bajaAlumno(String dni) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		BufferedWriter bw = null;
		File fOriginal = new File(nombreFichero);
		File fTmp = new File("alumnos.tmp");
		try {
			br = new BufferedReader(new FileReader(nombreFichero));
			bw = new BufferedWriter(new FileWriter("alumnos.tmp"));
			String linea = "";
			while((linea = br.readLine()) != null) {
				String [] datos = linea.split(";");
				if(datos[0].equalsIgnoreCase(dni)) {
					Alumno a = new Alumno();
					a.setDni(dni);
					a.setNombre(datos[1]);
					a.setFechaN(formatoFecha.parse(datos[2]));
					a.setNumExp(Integer.parseInt(datos[3]));
					a.setEstatura(Float.parseFloat(datos[4]));
					a.setActivo(false);
					bw.write(a.getDni() + ";" + a.getNombre() + ";" + formatoFecha.format(a.getFechaN()) + ";" + a.getNumExp() + ";" + a.getEstatura() + ";" + a.isActivo() + "\n");
				}else {
					bw.write(linea);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
				bw.close();
				if(fTmp != null) {
					fOriginal.delete();
					fTmp.renameTo(fOriginal);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void borrarAlumno(String dni) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		File fOriginal = new File(nombreFichero);
		File fTmp = new File("alumnos.tmp");
		try {
			br = new BufferedReader(new FileReader(nombreFichero));
			bw = new BufferedWriter(new FileWriter("alumnos.tmp"));
			String linea = "";
			while((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				if(!datos[0].equalsIgnoreCase(dni)) {
					bw.write(linea + "\n");
				}
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
					br.close();
					bw.close();
					if(fTmp != null) {
						fOriginal.delete();
						fTmp.renameTo(fOriginal);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public Alumno mostrarAlumnoconDni(String dni) {
		BufferedReader br = null;
		Alumno a = null;
			try {
				br = new BufferedReader(new FileReader(nombreFichero));
				String linea = "";
				while((linea = br.readLine()) != null) {
					String[] datos = linea.split(";");
					if(datos[0].equalsIgnoreCase(dni)) {
						 a = new Alumno();
						a.setDni(dni);
						a.setNombre(datos[1]);
						a.setFechaN(formatoFecha.parse(datos[2]));
						a.setNumExp(Integer.parseInt(datos[3]));
						a.setEstatura(Float.parseFloat(datos[4]));
						a.setActivo(Boolean.parseBoolean(datos[5]));
						System.out.println(a);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return a;
	}
	
}
