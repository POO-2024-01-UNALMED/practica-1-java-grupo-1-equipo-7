package gestorAplicación;

import java.io.Serializable;

public class Tiquete implements Serializable {
	private Asiento asiento;
	private String referenciaReserva;
	private Viaje viaje;
	private Pasajero pasajero;
	
	public Viaje getViaje() {
		return viaje;
	}
	
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	
	public Pasajero getPasajero() {
		return pasajero;
	}
	
	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public String getReferenciaReserva() {
		return referenciaReserva;
	}

	public void setReferenciaReserva(String referenciaReserva) {
		this.referenciaReserva = referenciaReserva;
	}
}
