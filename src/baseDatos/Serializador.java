package baseDatos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Tiquete;
import gestorAplicación.gestion.Habitacion;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.personas.Pasajero;
import gestorAplicación.transporte.Asiento;

public class Serializador {
	private static File rutaTemp = new File(new File("").getAbsolutePath() + "/src/baseDatos/temp");

	public static void limpiarArchivos() {
		File[] docs = rutaTemp.listFiles();
		PrintWriter pw;

		for (File file : docs) {
			try {
				pw = new PrintWriter(file);
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
			} else if (file.getAbsolutePath().contains("tiquetes")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(new Tiquete());
					oos.close();
					fos.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
