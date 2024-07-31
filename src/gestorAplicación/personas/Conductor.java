package gestorAplicación.personas;

import java.io.Serializable;

public class Conductor extends Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean disponible;
	
	public Conductor(String nombre) {
		super(nombre, "", "", "");
	}

	public boolean isDisponible() {
		return disponible;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}
