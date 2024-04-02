package gestorAplicación;

import java.util.ArrayList;

public class Viaje {
	private ArrayList<Pasajero> listaPasajeros = new ArrayList<Pasajero>();
	private Conductor conductorAsignado;
	private Terminal terminalDestion;
	private Terminal terminalOrigen;
	private Bus busAsignado;
	private String estado;
	
	public Conductor getConductorAsignado() {
		return conductorAsignado;
	}
	
	public void setConductorAsignado(Conductor conductorAsignado) {
		this.conductorAsignado = conductorAsignado;
	}
	
	public Terminal getTerminalDestion() {
		return terminalDestion;
	}
	
	public void setTerminalDestion(Terminal terminalDestion) {
		this.terminalDestion = terminalDestion;
	}
	
	public Terminal getTerminalOrigen() {
		return terminalOrigen;
	}
	
	public void setTerminalOrigen(Terminal terminalOrigen) {
		this.terminalOrigen = terminalOrigen;
	}
	
	public Bus getBusAsignado() {
		return busAsignado;
	}
	
	public void setBusAsignado(Bus busAsignado) {
		this.busAsignado = busAsignado;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
