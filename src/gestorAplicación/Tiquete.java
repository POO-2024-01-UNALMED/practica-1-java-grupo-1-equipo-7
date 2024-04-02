package gestorAplicación;

public class Tiquete {
	private Asiento asientoAsignado;
	private Terminal terminalDestino;
	private Terminal terminalOrigen;
	private String numeroTiquete;
	private Viaje viaje;
	private Pasajero pasajero;
	
	public Asiento getAsientoAsignado() {
		return asientoAsignado;
	}
	
	public void setAsientoAsignado(Asiento asientoAsignado) {
		this.asientoAsignado = asientoAsignado;
	}
	
	public Terminal getTerminalDestino() {
		return terminalDestino;
	}
	
	public void setTerminalDestino(Terminal terminalDestino) {
		this.terminalDestino = terminalDestino;
	}
	
	public Terminal getTerminalOrigen() {
		return terminalOrigen;
	}
	
	public void setTerminalOrigen(Terminal terminalOrigen) {
		this.terminalOrigen = terminalOrigen;
	}
	
	public String getNumeroTiquete() {
		return numeroTiquete;
	}
	
	public void setNumeroTiquete(String numeroTiquete) {
		this.numeroTiquete = numeroTiquete;
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
}
