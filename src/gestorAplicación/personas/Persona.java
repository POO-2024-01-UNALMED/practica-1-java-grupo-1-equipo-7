package gestorAplicación.personas;

import java.io.Serializable;

import gestorAplicación.gestion.Tiquete;

public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6358131159471883226L;
	protected String nombre;
	protected String id;
	private String correo;
	private String telefono;
	
	public Persona() {
		this("", "");
	}
	
	public Persona(String nombre, String id) {
		this.nombre = nombre;
		this.id = id;
	}
	
	public Persona(String nombre2, String idPasajero, String telefono, String correo) {
		nombre=nombre2;
		id=idPasajero;
		this.setTelefono(telefono);
		this.setCorreo(correo);
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getId() {
		return id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(String id) {
		this.id = id;
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
}
