package gestorAplicación;

import java.io.Serializable;
import java.util.ArrayList;

public class Terminal implements Serializable {
	ArrayList<Conductor> conductores = new ArrayList<Conductor>();
	ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	ArrayList<Bus> buses = new ArrayList<Bus>();
	String ciudad;
	
	void agregarConductor(Conductor nuevoConductor) {
		conductores.add(nuevoConductor);
	}
	
}
