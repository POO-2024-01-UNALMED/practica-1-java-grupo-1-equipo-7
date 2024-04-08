package gestorAplicación;

import java.io.Serializable;

public class Asiento implements Serializable{
	private String numeroAsiento;
	private boolean estaReservado;
	
	public Asiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	public String getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}
	
	public boolean isEstaReservado() {
		return estaReservado;
	}

	public void setEstaReservado(boolean estaReservado) {
		this.estaReservado = estaReservado;
	}

}
