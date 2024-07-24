package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import gestorAplicación.personas.Conductor;
import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.Bus;

public class Viaje implements Serializable {
	private static ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private Terminal terminalOrigen;
	private Terminal terminalDestino;
	private Empresa empresa;
	private LocalDate fecha;
	private LocalTime hora;
	private String id;
	private Conductor conductor;
	private Bus bus;
	
	public Viaje(Terminal terminalOrigen, Terminal terminalDestino, String id) {
		this.terminalOrigen = terminalOrigen;
		this.terminalDestino = terminalDestino;
		this.id = id;
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
	
	public ArrayList<Asiento> listaAsientos() {
		return this.getBus().getAsientos();
	}
	
	@Override
	public String toString() {
		return "    " + getFecha() + 
				"     " + getTerminalOrigen().getUbicacion() + 
				"     " + getTerminalDestino().getUbicacion() + 
				"     " + getHora() + "		" + getId() + 
				"     " + getBus().getPlaca() + "    ";
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
	public static ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
