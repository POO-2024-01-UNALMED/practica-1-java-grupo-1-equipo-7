package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.Bus;

public class Deserializador {
	private static File rutaTemp 
			= new File(new File("").getAbsolutePath() + "/src/baseDatos/temp");
	
	static int contador = 0;
	
	public static void deserializar(Empresa empresa) {
		File[] docs = rutaTemp.listFiles();
		FileInputStream fis;
		ObjectInputStream ois;
		
		for (File file : docs) {
//			if (file.getAbsolutePath().contains("viajes")) {
//				try {
//					fis = new FileInputStream(file);
//					ois = new ObjectInputStream(fis);
//					
//					empresa.setViajes((ArrayList<Viaje>) ois.readObject());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
			
			if (file.getAbsolutePath().contains("empresas")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					
//					Empresa empresaTemporal = (Empresa) ois.readObject();
//					 
//					empresa.setViajes(empresaTemporal.getViajes());
//					empresa.setNombre(empresaTemporal.getNombre());
					
					ArrayList<Empresa> empresasTemporal = 
							(ArrayList<Empresa>) ois.readObject();
					
				
					empresa.setViajes(empresasTemporal.get(contador).
							getViajes());
					empresa.setNombre(empresasTemporal.get(contador).
							getNombre());
					
					contador++;
					
				} catch (Exception e) {
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
