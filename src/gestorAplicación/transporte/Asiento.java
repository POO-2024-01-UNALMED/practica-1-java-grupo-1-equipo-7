package gestorAplicación.transporte;

import java.io.Serializable;

public class Asiento implements Serializable{
	private String numeroAsiento;
	private boolean reservado;
	
	@Override 
	public String toString() {
		return numeroAsiento;
	}
	
	public Asiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}
	
	public String getNumeroAsiento() {
		return numeroAsiento;
	}
	
	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

}
