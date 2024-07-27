package gestorAplicación.transporte;

import java.io.Serializable;

public class Asiento implements Serializable{
	static final long serialVersionUID = 3L;
	private String numeroAsiento;
	private boolean reservado;
	
	public Asiento() {
		
	}
	
	public Asiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}
	
	public String getNumeroAsiento() {
		return numeroAsiento;
	}
	
	public void setNumeroAsiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}
	
	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}
}
