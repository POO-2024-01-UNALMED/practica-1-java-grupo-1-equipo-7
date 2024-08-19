package gestorAplicación.personas;

import gestorAplicación.gestion.Hospedaje;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Viaje;
import java.util.ArrayList;
import java.io.Serializable;

public class Conductor extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ArrayList<Conductor> conductores = new ArrayList<Conductor>();
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();

	public Conductor(String nombre) {
		super(nombre, "0");
		conductores.add(this);
	}

	public Conductor(String nombre, String id) {
		super(nombre, id);
		conductores.add(this);
	}

	public static Conductor buscarConductor(String id) {
		for (Conductor conductor : Conductor.conductores) {
			if (conductor.getId().equals(id)) {
				return conductor;
			}
		}
		return null;
	}

	public static Conductor buscarConductor(String nombre, String id) {
		for (Conductor conductor : conductores) {
			if (conductor.getNombre() != null && conductor.getId() != null) {
				if (conductor.getId().equals(id) && conductor.getNombre().equals(nombre)) {
					return conductor;
				}
			}
		}
		return null;
	}

	public ArrayList<Viaje> getViajes() {
		return viajes;

	}

	public static ArrayList<Conductor> getConductores() {
		return conductores;
	}

	public static void eliminarConductor(String id) {

		Conductor conductor = Conductor.buscarConductor(id);
		conductores.remove(conductor);

	}
}
