package gestorAplicación;

import java.io.Serializable;

public class Asiento implements Serializable{
	private boolean disponible;
	private String identificador;
	private Bus busPertenece;
	
	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	public Bus getBusPertenece() {
		return busPertenece;
	}

	public void setBusPertenece(Bus busPertenece) {
		this.busPertenece = busPertenece;
	}
}
