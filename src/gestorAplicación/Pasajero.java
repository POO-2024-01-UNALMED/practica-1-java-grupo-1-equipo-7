package gestorAplicación;

import java.util.ArrayList;

public class Pasajero {
	private ArrayList<Tiquete> historialTiquetes = new ArrayList<Tiquete>();
	private ArrayList<Viaje> historialViajes = new ArrayList<Viaje>();
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
