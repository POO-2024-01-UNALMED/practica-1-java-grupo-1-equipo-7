package gestorAplicación.gestion;

import java.io.Serializable;
import java.util.ArrayList;

public class Terminal implements Serializable {
	private String ubicacion;
	private static ArrayList<Terminal> terminales = new ArrayList<Terminal>();
	
	public Terminal(String ciudad) {
		this.ubicacion = ciudad;
		terminales.add(this);
	}
	
	public static Terminal buscarTerminal(String ubicacion) {
		for(Terminal terminal : terminales) {
			if(terminal.getUbicacion().equals(ubicacion)) {
				return terminal;
			}
		}
		return null;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public static ArrayList<Terminal> getTerminales() {
		return terminales;
	}
	
}
