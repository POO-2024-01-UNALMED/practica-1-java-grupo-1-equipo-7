package baseDatos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import gestorAplicación.*;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Tiquete;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.personas.Conductor;
import gestorAplicación.personas.Pasajero;
import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.Bus;

public class Serializador {
	private static File rutaTemp = new File(new File("").getAbsolutePath() + 
	"/src/baseDatos/temp");

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

	public static void serializar(Asiento asiento) {
		try {
			FileOutputStream fos = new FileOutputStream(rutaTemp + "/asientos.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(asiento);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void serializar(Bus bus) {
		try {
			FileOutputStream fos = new FileOutputStream(rutaTemp + "/buses.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(bus);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void serializar(Conductor conductor) {
		try {
			FileOutputStream fos = new FileOutputStream(rutaTemp + "/conductores.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(conductor);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void serializar(Pasajero pasajero) {
		try {
			FileOutputStream fos = new FileOutputStream(rutaTemp + "/pasajeros.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(pasajero);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void serializar(Terminal terminal) {
		try {
			FileOutputStream fos = new FileOutputStream(rutaTemp + "/terminales.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(terminal);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void serializar(Tiquete tiquete) {
		try {
			FileOutputStream fos = new FileOutputStream(rutaTemp + "/tiquetes.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tiquete);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void serializar(Viaje viaje) {
		try {
			FileOutputStream fos = new FileOutputStream(rutaTemp + "/viajes.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(viaje);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
