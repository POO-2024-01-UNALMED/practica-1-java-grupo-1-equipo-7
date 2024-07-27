package gestorAplicación.personas;

public class Persona {
	protected String nombre;
	protected String id;
	protected String correo;
	protected String telefono;
	
	public Persona() {
		this("", "", "", "");
	}
	
	public Persona(String nombre, String id, String correo, String telefono) {
		this.nombre = nombre;
		this.id = id;
		this.correo = correo;
		this.telefono = telefono;
	}
	
	public String getId() {
		return id;
	}
}
