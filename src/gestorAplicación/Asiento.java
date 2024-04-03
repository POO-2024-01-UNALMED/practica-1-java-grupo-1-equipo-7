package gestorAplicación;

import java.io.Serializable;

public class Asiento implements Serializable{
	private String numeroSilla;
	private boolean estaReservado;

	public String getNumeroSilla() {
		return numeroSilla;
	}

	public void setNumeroSilla(String numeroSilla) {
		this.numeroSilla = numeroSilla;
	}

	public boolean isEstaReservado() {
		return estaReservado;
	}

	public void setEstaReservado(boolean estaReservado) {
		this.estaReservado = estaReservado;
	}
	
	
}
