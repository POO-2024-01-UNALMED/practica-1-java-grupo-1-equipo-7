package gestorAplicación;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Viaje implements Serializable {
	private String idViaje;
	private LocalDate fecha;
	private Bus bus;
	private Conductor conductor;
	private Terminal terminalOrigen;
	private Terminal terminalDestino;
	private static ArrayList<Viaje> viajes = new ArrayList<Viaje>();

	public Viaje(Terminal terminalOrigen, Terminal terminalDestino, String idViaje) {
		this.terminalOrigen = terminalOrigen;
		this.terminalDestino = terminalDestino;
		this.idViaje = idViaje;
		viajes.add(this);
	}

	public static ArrayList<Viaje> buscarViajes(String terminalOrigen, String terminalDestino) {
		ArrayList<Viaje> listaViajes = new ArrayList<Viaje>();

		for (Viaje viaje : viajes) {
			if (viaje.getTerminalOrigen() == Terminal.buscarTerminal(terminalOrigen)
					&& viaje.getTerminalDestino() == Terminal.buscarTerminal(terminalDestino)) {
				listaViajes.add(viaje);
			}
		}
		return listaViajes;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Terminal getTerminalOrigen() {
		return terminalOrigen;
	}

	public Terminal getTerminalDestino() {
		return terminalDestino;
	}

	public String getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(String idViaje) {
		this.idViaje = idViaje;
	}

}
