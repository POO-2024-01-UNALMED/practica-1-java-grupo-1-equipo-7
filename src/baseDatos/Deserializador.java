package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import gestorAplicación.*;
import gestorAplicación.transporte.Asiento;

public class Deserializador {
	private static File rutaTemp = new File(new File("").getAbsolutePath() + "/src/baseDatos/temp");
	
	public static void deserializar(Asiento asiento) {
		try {
			FileInputStream fis = new FileInputStream(rutaTemp + "/asientos.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
