package baseDatos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Habitacion;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.personas.Pasajero;
import gestorAplicación.transporte.Asiento;

public class Serializador {
	private static File rutaTemp 
			= new File(new File("").getAbsolutePath() + "/src/baseDatos/temp");

	public static void limpiarArchivos() {
		File[] docs = rutaTemp.listFiles();

		for (File file : docs) {
			try {
				PrintWriter pw = new PrintWriter(file);
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void serializar() {
		FileOutputStream fos;
		ObjectOutputStream oos;
		File[] docs = rutaTemp.listFiles();

		for (File file : docs) {
			if (file.getAbsolutePath().contains("empresas")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(Empresa.getEmpresas());
					oos.close();
					fos.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("viajes")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(Viaje.getViajes());
					oos.close();
					fos.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("asientos")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(Asiento.getAsientos());
					oos.close();
					fos.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("habitaciones")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(Habitacion.getHabitaciones());
					oos.close();
					fos.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("terminales")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(Terminal.getTerminales());
					oos.close();
					fos.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("pasajeros")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(Pasajero.getPasajeros());
					oos.close();
					fos.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
//	public static void serializar(Empresa empresa) {
//		FileOutputStream fos;
//		ObjectOutputStream oos;
//		File[] docs = rutaTemp.listFiles();
//
//		for (File file : docs) {
////			if (file.getAbsolutePath().contains("viajes")) {
////				try {
////					fos = new FileOutputStream(file);
////					oos = new ObjectOutputStream(fos);
////					oos.writeObject(empresa.getViajes());
////				} catch(Exception e) {
////					e.printStackTrace();
////				}
////			} 
//			
//			if (file.getAbsolutePath().contains("empresas")) {
//				try {
//					fos = new FileOutputStream(file);
//					oos = new ObjectOutputStream(fos);
//					oos.writeObject(empresa);
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//			} 
//		}
//	}
	
//	public static void serializar(ArrayList<Empresa> empresas) {
//		FileOutputStream fos;
//		ObjectOutputStream oos;
//		File[] docs = rutaTemp.listFiles();
//		
//		Deserializador.contador = 0;
//
//		for (File file : docs) {
////			if (file.getAbsolutePath().contains("viajes")) {
////				try {
////					fos = new FileOutputStream(file);
////					oos = new ObjectOutputStream(fos);
////					oos.writeObject(empresa.getViajes());
////				} catch(Exception e) {
////					e.printStackTrace();
////				}
////			} 
//			
//			if (file.getAbsolutePath().contains("empresas")) {
//				try {
//					fos = new FileOutputStream(file);
//					oos = new ObjectOutputStream(fos);
//					oos.writeObject(empresas);
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//			} 
//		}
//	}
//	
//	public static void serializar(Viaje viaje) {
//		FileOutputStream fos;
//		ObjectOutputStream oos;
//		File[] docs = rutaTemp.listFiles();
//		
//		for (File file : docs) {
////			if (file.getAbsolutePath().contains("buses")) {
////				try {
////					fos = new FileOutputStream(file);
////					oos = new ObjectOutputStream(fos);
////					oos.writeObject(viaje.getBus());
////				} catch(Exception e) {
////					e.printStackTrace();
////				}
////			} 
//			
//			if (file.getAbsolutePath().contains("viajes")) {
//				try {
//					fos = new FileOutputStream(file);
//					oos = new ObjectOutputStream(fos);
//					oos.writeObject(viaje);
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//			} 
//		}
//	}
	
//	public static void serializar(Bus bus) {
//		FileOutputStream fos;
//		ObjectOutputStream oos;
//		File[] docs = rutaTemp.listFiles();
//		
//		for (File file : docs) {
////			if (file.getAbsolutePath().contains("asientos")) {
////				try {
////					fos = new FileOutputStream(file);
////					oos = new ObjectOutputStream(fos);
////					oos.writeObject(bus.getAsientos());
////				} catch(Exception e) {
////					e.printStackTrace();
////				}
////			} 
//			
//			if (file.getAbsolutePath().contains("buses")) {
//				try {
//					fos = new FileOutputStream(file);
//					oos = new ObjectOutputStream(fos);
//					oos.writeObject(bus);
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//			} 
//		}
//	}
	
//	public static void serializar(ArrayList<Terminal> terminales) {
//		FileOutputStream fos;
//		ObjectOutputStream oos;
//		File[] docs = rutaTemp.listFiles();
//		
//		for (File file : docs) {
//			if (file.getAbsolutePath().contains("terminales")) {
//				try {
//					fos = new FileOutputStream(file);
//					oos = new ObjectOutputStream(fos);
//					oos.writeObject(terminales);
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//			} 
//		}
//	}
}
