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
	
	public String getNombre() {
		return nombre;
	}
	
	public String getId() {
		return id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
