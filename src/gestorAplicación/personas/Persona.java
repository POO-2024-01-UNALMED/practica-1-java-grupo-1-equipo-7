package gestorAplicación.personas;

import gestorAplicación.gestion.Tiquete;

public class Persona {
	protected String nombre;
	protected String id;
	
	public Persona() {
		this("", "");
	}
	
	public Persona(String nombre, String id) {
		this.nombre = nombre;
		this.id = id;
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
	
	public void agregarTiquete(Tiquete tiquete) {
		
	}
}
