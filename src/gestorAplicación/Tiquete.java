package gestorAplicación;

import java.io.Serializable;

public class Tiquete implements Serializable {
	private Pasajero pasajero;
	private Viaje viaje;
	private Asiento asiento;
	private String referenciaReserva;
	private static int referencias = 1;
	
	public Tiquete(Pasajero pasajero, Viaje viaje, Asiento asiento) {
		this.pasajero = pasajero;
		this.viaje = viaje;
		this.asiento = asiento;
		this.referenciaReserva = String.valueOf(referencias);
		referencias++;
	}
	
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
