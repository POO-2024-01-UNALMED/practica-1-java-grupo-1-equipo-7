package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import gestorAplicación.personas.Conductor;
import gestorAplicación.personas.Pasajero;
import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.Bus;

public class Viaje implements Serializable {
	private static ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	static final long serialVersionUID = 2L;
	private ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
	private ArrayList<Hospedaje> hospedajes = new ArrayList<Hospedaje>();
	private Terminal terminalOrigen;
	private Terminal terminalDestino;
	private Empresa empresa;
	private LocalDate fecha;
	private LocalTime hora;
	private String id;
	private Conductor conductor;
	private Bus bus;
	
	public Viaje() {
//		Deserializador.deserializar(this);
		viajes.add(this);
	}
	
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
	
	public void reservarAsiento(String numeroAsiento) {
		for(Asiento asiento : this.listaAsientos()) {
			if(asiento.getNumeroAsiento().equals(numeroAsiento)) {
				asiento.setReservado(true);
			}
		}
	}
	
	public void liberarAsiento(String numeroAsiento) {
		for(Asiento asiento : this.listaAsientos()) {
			if(asiento.getNumeroAsiento().equals(numeroAsiento)) {
				asiento.setReservado(false);
			}
		}
	}
	
	public Asiento buscarAsiento(String numeroAsiento) {
		for (Asiento asiento : this.getBus().getAsientos()) {
			if (asiento.getNumeroAsiento().equals(numeroAsiento)) {
				return asiento;
			}
		}
		
		return null;
	}
	
	public Hospedaje buscarHospedaje(String nombre) {
		for (Hospedaje hospedaje : this.getHospedajes()) {
			if (hospedaje.getNombre().equals(nombre)) {
				return hospedaje;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return "    " + getStrFecha() + 
				"     " + getTerminalOrigen().getUbicacion() + 
				"     " + getTerminalDestino().getUbicacion() + 
				"     " + getHora() + "              " + getId() + 
				"     " + getBus().getPlaca();
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
	
	public String getStrFecha() {
		DateTimeFormatter formateoFecha = 
		DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		
		String strFecha = fecha.format(formateoFecha);
				
		return strFecha;
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

	public ArrayList<Hospedaje> getHospedajes() {
		return hospedajes;
	}

	public void setHospedajes(ArrayList<Hospedaje> hospedajes) {
		this.hospedajes = hospedajes;
	}

	public ArrayList<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(ArrayList<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}
}
