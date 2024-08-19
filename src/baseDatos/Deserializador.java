package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Habitacion;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.personas.Pasajero;
import gestorAplicación.transporte.Asiento;

public class Deserializador {
	private static File rutaTemp 
			= new File(new File("").getAbsolutePath() + "/src/baseDatos/temp");
	
//	static int contador = 0;
	
//	public static void deserializar(Empresa empresa) {
//		File[] docs = rutaTemp.listFiles();
//		FileInputStream fis;
//		ObjectInputStream ois;
//		
//		for (File file : docs) {
////			if (file.getAbsolutePath().contains("viajes")) {
////				try {
////					fis = new FileInputStream(file);
////					ois = new ObjectInputStream(fis);
////					
////					empresa.setViajes((ArrayList<Viaje>) ois.readObject());
////				} catch (Exception e) {
////					e.printStackTrace();
////				}
////			}
//			
//			if (file.getAbsolutePath().contains("empresas")) {
//				try {
//					fis = new FileInputStream(file);
//					ois = new ObjectInputStream(fis);
//					
////					Empresa empresaTemporal = (Empresa) ois.readObject();
////					 
////					empresa.setViajes(empresaTemporal.getViajes());
////					empresa.setNombre(empresaTemporal.getNombre());
//					
//					ArrayList<Empresa> empresasTemporal = 
//							(ArrayList<Empresa>) ois.readObject();
//					
//				
//					empresa.setViajes(empresasTemporal.get(contador).
//							getViajes());
//					empresa.setNombre(empresasTemporal.get(contador).
//							getNombre());
//					
//					contador++;
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
	public static void deserializar() {
		FileInputStream fis;
		ObjectInputStream ois;
		File[] docs = rutaTemp.listFiles();
		
		for (File file : docs) {
			if (file.getAbsolutePath().contains("empresas")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					Empresa.setEmpresas((ArrayList<Empresa>) ois.readObject());
					ois.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("viajes")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					Viaje.setViajes((ArrayList<Viaje>) ois.readObject());
					ois.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("asientos")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					Asiento.setAsientos((ArrayList<Asiento>) ois.readObject());
					ois.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("habitaciones")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					Habitacion.setHabitaciones((ArrayList<Habitacion>) ois.readObject());
					ois.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("terminales")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					Terminal.setTerminales((ArrayList<Terminal>) ois.readObject());
					ois.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("pasajeros")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					Pasajero.setPasajeros((ArrayList<Pasajero>) ois.readObject());
					ois.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
//	public static void deserializar(Viaje viaje) {
//		File[] docs = rutaTemp.listFiles();
//		FileInputStream fis;
//		ObjectInputStream ois;
//		
//		for (File file : docs) {
////			if (file.getAbsolutePath().contains("buses")) {
////				try {
////					fis = new FileInputStream(file);
////					ois = new ObjectInputStream(fis);
////					
////					viaje.setBus((Bus) ois.readObject());
////				} catch (Exception e) {
////					e.printStackTrace();
////				}
////			}
//			
//			if (file.getAbsolutePath().contains("viajes")) {
//				try {
//					fis = new FileInputStream(file);
//					ois = new ObjectInputStream(fis);
//					
//					Viaje viajeTemporal = (Viaje) ois.readObject();
//					
//					viaje.setBus(viajeTemporal.getBus());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
//	public static void deserializar(Bus bus) {
//		File[] docs = rutaTemp.listFiles();
//		FileInputStream fis;
//		ObjectInputStream ois;
//		
//		for (File file : docs) {
////			if (file.getAbsolutePath().contains("asientos")) {
////				try {
////					fis = new FileInputStream(file);
////					ois = new ObjectInputStream(fis);
////					
////					bus.setAsientos((ArrayList<Asiento>) ois.readObject());
////				} catch (Exception e) {
////					e.printStackTrace();
////				}
////			}
//			
//			if (file.getAbsolutePath().contains("buses")) {
//				try {
//					fis = new FileInputStream(file);
//					ois = new ObjectInputStream(fis);
//					
//					Bus busTemporal = (Bus) ois.readObject();
//					
//					bus.setAsientos(busTemporal.getAsientos());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	
//	public static void deserializar(ArrayList<Terminal> terminales) {
//		File[] docs = rutaTemp.listFiles();
//		FileInputStream fis;
//		ObjectInputStream ois;
//		
//		for (File file : docs) {
//			if (file.getAbsolutePath().contains("terminales")) {
//				try {
//					fis = new FileInputStream(file);
//					ois = new ObjectInputStream(fis);
//					
//					terminales = (ArrayList<Terminal>) ois.readObject();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
}
