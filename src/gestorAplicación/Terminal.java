package gestorAplicación;

import java.io.Serializable;
import java.util.ArrayList;

public class Terminal implements Serializable {
	private ArrayList<Conductor> conductores = new ArrayList<Conductor>();
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private ArrayList<Bus> buses = new ArrayList<Bus>();
	private String ciudad;
	
	void agregarConductor(Conductor nuevoConductor) {
		conductores.add(nuevoConductor);
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
}
