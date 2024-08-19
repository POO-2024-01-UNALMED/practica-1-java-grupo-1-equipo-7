package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import gestorAplicación.personas.Conductor;
import gestorAplicación.personas.Pasajero;

import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.Bus;

public class Viaje implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2760602559521284522L;
	private static ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
	private Terminal terminalOrigen;
	private Terminal terminalDestino;
	private Empresa empresa;
	private LocalDate fecha;
	private LocalTime hora;
	private static int ids;
	private String id;
	private Bus bus;
	private ArrayList<Tiquete> tiquetes = new ArrayList<Tiquete>();

	public Viaje() {
//		Deserializador.deserializar(this);
		viajes.add(this);
		ids++;
		id = String.valueOf(ids);
	}

	public Viaje(Terminal terminalOrigen, Terminal terminalDestino) {
		this.terminalOrigen = terminalOrigen;
		this.terminalDestino = terminalDestino;
		viajes.add(this);
		ids++;
		id = String.valueOf(ids);
	}

	public Viaje(Terminal terminalOrigen, Terminal terminalDestino, Empresa empresa, 
			LocalDate fecha, LocalTime hora, Conductor conductor, Bus bus) {
		this.terminalOrigen = terminalOrigen;
		this.terminalDestino = terminalDestino;
		this.empresa = empresa;
		this.fecha = fecha;
		this.hora = hora;
		this.bus = bus;
		conductor.getViajes().add(this);
		bus.añadirViaje(this);
		viajes.add(this);
		ids++;
		id = String.valueOf(ids);
		}

	public Viaje(ArrayList<Pasajero> pasajeros, ArrayList<Hospedaje> hospedajes, 
			ArrayList<Tiquete> tiquetes, Terminal terminalOrigen, Terminal terminalDestino, 
			Empresa empresa, LocalDate fecha, LocalTime hora, Conductor conductor, Bus bus) {
		this.pasajeros = pasajeros;
		this.tiquetes = tiquetes;

		this.terminalOrigen = terminalOrigen;
		this.terminalDestino = terminalDestino;
		this.empresa = empresa;
		this.fecha = fecha;
		this.hora = hora;
		this.bus = bus;
		conductor.getViajes().add(this);
		bus.añadirViaje(this);
		viajes.add(this);
		ids++;
		id = String.valueOf(ids);
	}

	public ArrayList<Asiento> listaAsientos() {
		return this.getBus().getAsientos();
	}

	public int[] tiposAsiento() {
		return this.getBus().getTiposAsiento();
	}

	public boolean tieneSillas() {
		for (Asiento asiento : listaAsientos()) {
			if (!asiento.isReservado()) {
				return true;
			}
		}

		return false;
	}

	public void reservarAsiento(String numeroAsiento, LocalDateTime fechaReserva) {
		for (Asiento asiento : this.listaAsientos()) {
			if (!asiento.isReservado()) {
				if (asiento.getNumero().equals(numeroAsiento)) {
					if (fechaReserva != null) {
						asiento.reservar(fechaReserva);
					} else {
						asiento.reservar(null);
					}
					break;
				}
			}
		}
	}

	public void liberarAsiento(String numeroAsiento) {
		for (Asiento asiento : this.listaAsientos()) {
			if (asiento.getNumero().equals(numeroAsiento)) {
				asiento.liberar();
			}
		}
	}

	public Asiento buscarAsiento(String numeroAsiento) {
		for (Asiento asiento : this.listaAsientos()) {
			if (asiento.getNumero().equals(numeroAsiento)) {
				return asiento;
			}
		}

		return null;
	}

	public Hospedaje buscarHospedaje(String nombre) {
		for (Hospedaje hospedaje : this.hospedajesDisponibles()) {
			if (hospedaje.getNombre().equals(nombre)) {
				return hospedaje;
			}
		}

		return null;
	}

	public static Viaje buscarViaje(ArrayList<Viaje> viajes, String id) {
		for (Viaje viaje : viajes) {
			if (viaje.getId().equals(id)) {
				return viaje;
			}
		}

		return null;
	}

	public static Viaje buscarViaje(Terminal terminalOrigen, Terminal terminalDestino, Empresa empresa, 
			LocalDate fecha, LocalTime hora, Conductor conductor, Bus bus) {
		for (Viaje viaje : viajes) {
			if (viaje.getTerminalOrigen().equals(terminalOrigen) && viaje.getTerminalDestino().equals(terminalDestino) &&
			viaje.empresa.equals(empresa) && viaje.fecha.equals(fecha) && viaje.hora.equals(hora) &&
			viaje.getBus().equals(bus) && viaje.getBus().getConductor().equals(conductor)) {
				return viaje;
			}
		}

		return null;
	}
	
	public static Viaje buscarViaje(String id) {
		for (Viaje viaje : viajes) {
			if (viaje.getId().equals(id)) {
				return viaje;
			}
		}

		return null;
	}

	public ArrayList<Hospedaje> hospedajesDisponibles() {
		ArrayList<Hospedaje> hospedajesDisponibles = new ArrayList<Hospedaje>();

		for (Hospedaje hospedaje : Hospedaje.getHospedajes()) {
			if (this.getTerminalDestino().getUbicacion().equals(hospedaje.getUbicacion())) {
				hospedajesDisponibles.add(hospedaje);
			}
		}

		return hospedajesDisponibles;
	}

	@Override
	public String toString() {
		int origen = 11 - (getTerminalOrigen().getUbicacion().length());
		String strOrigen = String.valueOf(origen);

		int destino = 11 - (getTerminalDestino().getUbicacion().length());
		String strDestino = String.valueOf(destino);

		String spaceOrigen;
		String spaceDestino;

		if (origen == 0) {
			spaceOrigen = "";
		} else {
			spaceOrigen = String.format("%" + strOrigen + "s", "");
		}

		if (destino == 0) {
			spaceDestino = "";
		} else {
			spaceDestino = String.format("%" + strDestino + "s", "");
		}

		return "    " + getStrFecha() + "     " + getTerminalOrigen().getUbicacion() 
				+ spaceOrigen + "     " + getTerminalDestino().getUbicacion() 
				+ spaceDestino + "     " + getHora() + "              " + getId()
				+ "     " + getBus().getPlaca() + "         ";
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getStrFecha() {
		DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ArrayList<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(ArrayList<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public ArrayList<Tiquete> getTiquetes() {
		return tiquetes;
	}

	public void setTiquetes(ArrayList<Tiquete> tiquetes) {
		this.tiquetes = tiquetes;
	}

	public static ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public static void setViajes(ArrayList<Viaje> viajes) {
		Viaje.viajes = viajes;
	}

	public void añadirTiquete(Tiquete tiquete) {
		Boolean tiqueteNuevo = true;
		for (Tiquete t : tiquetes) {
			if (tiquete.equals(t)) {
				tiqueteNuevo = false;
			}
		}
		if (tiqueteNuevo) {
			tiquetes.add(tiquete);
		}

		else {
			System.out.println("El tiquete ya ha sido registrado");
		}
	}

	/*
	 * public void añadirHospedaje(Hospedaje hospedaje) { Boolean
	 * hospedajeNuevo=true; for (Hospedaje h: this.hospedajesDisponibles()) { if
	 * (hospedaje.equals(h)) { hospedajeNuevo=false; } } if (hospedajeNuevo) {
	 * hospedajes.add(hospedaje); }
	 * 
	 * else { System.out.println("El hospedaje ya ha sido registrado"); } }
	 */

	public void añadirPasajero(Pasajero pasajero) {
		Boolean pasajeroNuevo = true;
		for (Pasajero p : pasajeros) {
			if (pasajero.equals(p)) {
				pasajeroNuevo = false;
			}
		}
		if (pasajeroNuevo) {
			pasajeros.add(pasajero);
		}

		else {
			System.out.println("El pasajero ya ha sido registrado");
		}
	}

	public static void eliminarViaje(String id) {

		Viaje viaje = Viaje.buscarViaje(id);
		viajes.remove(viaje);

	}
}
