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
	private static final long serialVersionUID = 6L;
	private static ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	
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

	// Método que devuelve la lista de asientos de un viaje, usado en varias funcionalidades
	public ArrayList<Asiento> listaAsientos() {
		return this.getBus().getAsientos();
	}

	// Método que devuelve una lista de tipo int, cuyos valores indican hasta que fila 
	// van cada tipo de asiento (new int[]{5, 10} significa que los asientos preferenciales 
	// van hasta la fila 5, los asientos premium van hasta la fila 10 y el resto de las 
	// filas son estandar. Usado en varias funcionalidades
	public int[] tiposAsiento() {
		return this.getBus().getTiposAsiento();
	}

	// Método para verificar si un viaje tiene sillas disponibles, usado en varias funcionalidades
	public boolean tieneSillas() {
		for (Asiento asiento : listaAsientos()) {
			if (!asiento.isReservado()) {
				return true;
			}
		}

		return false;
	}

	// Método para reservar un asiento en un viaje por un cierto período de tiempo o hasta que 
	// el viaje acabe, usado en varias funcionalidades
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

	// Método para liberar un asiento por el número de asiento, usado en la funcionalidad 1 y 3
	public void liberarAsiento(String numeroAsiento) {
		for (Asiento asiento : this.listaAsientos()) {
			if (asiento.getNumero().equals(numeroAsiento)) {
				asiento.liberar();
			}
		}
	}
	
	// Método para buscar un asiento en un viaje por el número de asiento, 
	// usado en varias funcionalidades
	public Asiento buscarAsiento(String numeroAsiento) {
		for (Asiento asiento : this.listaAsientos()) {
			if (asiento.getNumero().equals(numeroAsiento)) {
				return asiento;
			}
		}

		return null;
	}

	// Método para buscar un hospedaje que esté disponible en el destino del viaje, 
	// usado en la funcionalidad 4
	public Hospedaje buscarHospedaje(String nombre) {
		for (Hospedaje hospedaje : this.hospedajesDisponibles()) {
			if (hospedaje.getNombre().equals(nombre)) {
				return hospedaje;
			}
		}

		return null;
	}

	// Método para buscar un viaje por id de una lista de viajes, usado en la funcionalidad 2
	public static Viaje buscarViaje(ArrayList<Viaje> viajes, String id) {
		for (Viaje viaje : viajes) {
			if (viaje.getId().equals(id)) {
				return viaje;
			}
		}

		return null;
	}
	
	// Método para buscar un viaje, usado en la funcionalidad 5
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
	
	// Método para buscar un viaje, usado en la funcionalidad 5
	public static Viaje buscarViaje(String id) {
		for (Viaje viaje : viajes) {
			if (viaje.getId().equals(id)) {
				return viaje;
			}
		}

		return null;
	}

	// Método que devuelve los hospedajes disponibles de acuerdo al destino del viaje, 
	// usado en la funcionalidad 4
	public ArrayList<Hospedaje> hospedajesDisponibles() {
		ArrayList<Hospedaje> hospedajesDisponibles = new ArrayList<Hospedaje>();

		for (Hospedaje hospedaje : this.terminalDestino.getHospedajes()) {
			hospedajesDisponibles.add(hospedaje);
		}

		return hospedajesDisponibles;
	}

	// Método toString que hace calcula los espacios en blanco necesarios para que la
	// impresión en pantalla se vea uniforme
	@Override
	public String toString() {
		int origen = 11 - (getTerminalOrigen().getUbicacion().length());
		String strOrigen = String.valueOf(origen);

		int destino = 11 - (getTerminalDestino().getUbicacion().length());
		String strDestino = String.valueOf(destino);
		
		int id = 3 - getId().length();
		String strId = String.valueOf(id);

		String spaceOrigen;
		String spaceDestino;
		String spaceId;

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
		
		if (id == 0) {
			spaceId = "";
		} else {
			spaceId = String.format("%" + strId + "s", "");
		}

		return "    " + getStrFecha() + "     " + getTerminalOrigen().getUbicacion() 
				+ spaceOrigen + "     " + getTerminalDestino().getUbicacion() 
				+ spaceDestino + "     " + getHora() + "              " + getId()
				+ spaceId + "     " + getBus().getPlaca() + "       ";
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

	// Método que devuelve la fecha formateada
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
}
