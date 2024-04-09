package gestorAplicación;

import java.io.Serializable;
import java.util.ArrayList;

public class Terminal implements Serializable {
	private String ubicacionTerminal;
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private static ArrayList<Terminal> terminales = new ArrayList<Terminal>();
	
	public Terminal(String ciudad) {
		this.ubicacionTerminal = ciudad;
		terminales.add(this);
	}
	
	public static Terminal buscarTerminal(String ubicacionTerminal) {
		for(Terminal terminal : terminales) {
			if(terminal.getUbicacionTerminal().equals(ubicacionTerminal)) {
				return terminal;
			}
		}
		return null;
	}
	
	public String getUbicacionTerminal() {
		return ubicacionTerminal;
	}

	public void setUbicacionTerminal(String ubicacionTerminal) {
		this.ubicacionTerminal = ubicacionTerminal;
	}
	
	public static ArrayList<Terminal> getTerminales() {
		return terminales;
	}
	
}
