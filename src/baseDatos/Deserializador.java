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
	private static File rutaTemp = new File(new File("").getAbsolutePath() + "/src/baseDatos/temp");
	
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
					fis.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("pasajeros")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					Pasajero.setPasajeros((ArrayList<Pasajero>) ois.readObject());
					ois.close();
					fis.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("terminales")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					Terminal.setTerminales((ArrayList<Terminal>) ois.readObject());
					ois.close();
					fis.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
