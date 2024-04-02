package gestorAplicación;

import java.io.Serializable;
import java.util.ArrayList;

public class Terminal implements Serializable {
	private static ArrayList<Terminal> terminales = new ArrayList<Terminal>();
	private ArrayList<Conductor> conductores = new ArrayList<Conductor>();
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private ArrayList<Bus> buses = new ArrayList<Bus>();
	private String ciudad;
	
	public Terminal(String ciudad) {
		this.ciudad = ciudad;
		terminales.add(this);
	}
	
	public void reservarTiquete() {
		
	}
	
	public static ArrayList<Terminal> getTerminales() {
		return terminales;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
}
