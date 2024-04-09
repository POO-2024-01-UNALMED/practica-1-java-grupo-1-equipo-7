package baseDatos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import gestorAplicación.*;

public class Serializador {
	
	public static void serializar() {
		try {
			FileOutputStream fos = new FileOutputStream(new File("").getAbsolutePath() + "/src/baseDatos/temp/terminales.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.close();
			fos.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
}
