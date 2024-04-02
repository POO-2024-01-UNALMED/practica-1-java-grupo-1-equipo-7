package gestorAplicación;

import java.io.Serializable;

public class Conductor implements Serializable {
	private boolean disponible;
	private Bus busAsignado;
	
	public boolean isDisponible() {
		return disponible;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public Bus getBusAsignado() {
		return busAsignado;
	}
	
	public void setBusAsignado(Bus busAsignado) {
		this.busAsignado = busAsignado;
	}
	
}
