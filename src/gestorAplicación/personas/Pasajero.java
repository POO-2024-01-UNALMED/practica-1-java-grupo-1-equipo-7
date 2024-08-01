package gestorAplicación.personas;

import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicación.gestion.Tiquete;
import gestorAplicación.gestion.Viaje;

public class Pasajero extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
	private ArrayList<Tiquete> tiquetes = new ArrayList<Tiquete>();
	
	public Pasajero() {
		pasajeros.add(this);
	}
	
	public Pasajero(String nombre, String id, String correo, String telefono) {
		super(nombre, id, correo, telefono);
		pasajeros.add(this);
	}
	
	public static Pasajero buscarPasajero(String id) {
		for(Pasajero pasajero : pasajeros) {
			if(pasajero.getId().equals(id)) {
				return pasajero;
			}
		}
		return null;
	}
	
	public Tiquete buscarTiquete(Viaje viaje) {
		for(Tiquete tiquete : tiquetes) {
			if(tiquete.getViaje().equals(viaje)) {
				return tiquete;
			}
		}
		
		return null;
	}
	
	public void agregarTiquete(Tiquete tiquete) {
		tiquetes.add(tiquete);
	}

	public ArrayList<Tiquete> getTiquetes() {
		return tiquetes;
	}

	public void setTiquetes(ArrayList<Tiquete> tiquetes) {
		this.tiquetes = tiquetes;
	}
	
	

	public static ArrayList<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public static void setPasajeros(ArrayList<Pasajero> pasajeros) {
		Pasajero.pasajeros = pasajeros;
	}
	
	

}
