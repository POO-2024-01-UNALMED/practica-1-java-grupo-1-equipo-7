package gestorAplicación.personas;

import java.io.Serializable;

public class Conductor extends Persona implements Serializable{
	private boolean disponible;
	
	public Conductor() {
		
	}

	public boolean isDisponible() {
		return disponible;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
}
