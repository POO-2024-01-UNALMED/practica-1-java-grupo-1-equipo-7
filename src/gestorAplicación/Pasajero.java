package gestorAplicación;

import java.io.Serializable;
import java.util.ArrayList;

public class Pasajero implements Serializable {
	private String idPasajero;
	private String nombre;
	private String telefono;
	
	public Pasajero(String nombre) {
		this.nombre = nombre;
	}

	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getIdPasajero() {
		return idPasajero;
	}

	public void setIdPasajero(String idPasajero) {
		this.idPasajero = idPasajero;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
