package gestorAplicación.personas;

import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicación.gestion.Tiquete;

public class Pasajero extends Persona implements Serializable {
	private String telefono;
	private String correo;
	private static ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
	private ArrayList<Tiquete> tiquetes = new ArrayList<Tiquete>();
	
	public Pasajero(String nombre, String numeroIdentificacion, String correo, String telefono) {
		this.nombre = nombre;
		this.numeroIdentificacion = numeroIdentificacion;
		this.correo = correo;
		this.telefono = telefono;
		pasajeros.add(this);
	}
	
	public static Pasajero buscarPasajero(String numeroIdentificacion) {
		for(Pasajero pasajero : pasajeros) {
			if(pasajero.getNumeroIdentificacion().equals(numeroIdentificacion)) {
				return pasajero;
			}
		}
		return null;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public ArrayList<Tiquete> getTiquetes() {
		return tiquetes;
	}

	public void setTiquetes(ArrayList<Tiquete> tiquetes) {
		this.tiquetes = tiquetes;
	}

}
