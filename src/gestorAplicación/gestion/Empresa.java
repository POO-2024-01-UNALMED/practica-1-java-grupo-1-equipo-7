package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import baseDatos.Deserializador;
import gestorAplicación.personas.Conductor;
import gestorAplicación.transporte.Asiento;

public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Empresa> empresas = new ArrayList<Empresa>();
	private ArrayList<Conductor> conductores = new ArrayList<Conductor>();
	private ArrayList<Terminal> terminales = new ArrayList<Terminal>();
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private String nombre;

	public Empresa(String nombre) {
		this.nombre = nombre;
		empresas.add(this);
	}

	// Método para buscar viajes por fecha, usado en el filtro de la funcionalidad 1
	public static ArrayList<Viaje> buscarViajes(LocalDate fecha) {
		ArrayList<Viaje> viajes = new ArrayList<Viaje>();

		for (Empresa empresa : empresas) {
			for (Viaje viaje : empresa.getViajes()) {
				if (fecha.equals(viaje.getFecha())) {
					viajes.add(viaje);
				}
			}
		}

		return viajes;
	}
	
	// Método para buscar viajes por origen y destino, usado en el principio de la funcionalidad 2
	public static ArrayList<Viaje> buscarViajes(String origen, String destino) {
		ArrayList<Viaje> viajes = new ArrayList<Viaje>();

		if (destino.isBlank()) {
			for (Empresa empresa : empresas) {
				for (Viaje viaje : empresa.getViajes()) {
					if (origen.equals(viaje.getTerminalOrigen().getUbicacion())) {
						viajes.add(viaje);
					}
				}
			}
		} else if (origen.isBlank()) {
			for (Empresa empresa : empresas) {
				for (Viaje viaje : empresa.getViajes()) {
					if (destino.equals(viaje.getTerminalDestino().getUbicacion())) {
						viajes.add(viaje);
					}
				}
			}
		} else {
			for (Empresa empresa : empresas) {
				for (Viaje viaje : empresa.getViajes()) {
					if (viaje.tieneSillas()) {
						if (origen.equals(viaje.getTerminalOrigen().getUbicacion())
								&& destino.equals(viaje.getTerminalDestino().getUbicacion())
								&& LocalDateTime.now().isBefore(LocalDateTime.of(viaje.getFecha(), viaje.getHora()))) {
							viajes.add(viaje);
						}
					}
				}
			}
		}

		return viajes;
	}

	// Método para buscar viajes por hora, usado en el filtro de la funcionalidad 1
	public static ArrayList<Viaje> buscarViajes(LocalTime hora) {
		ArrayList<Viaje> viajes = new ArrayList<Viaje>();

		for (Empresa empresa : empresas) {
			for (Viaje viaje : empresa.getViajes()) {
				if (hora.equals(viaje.getHora())) {
					viajes.add(viaje);
				}
			}
		}

		return viajes;
	}
	
	// Método para buscar viajes por id, usado en el filtro de la funcionalidad 1
	public static ArrayList<Viaje> buscarViajes(String string) {
		ArrayList<Viaje> viajes = new ArrayList<Viaje>();

		for (Empresa empresa : empresas) {
			for (Viaje viaje : empresa.getViajes()) {
				if (string.equals(viaje.getId())) {
					viajes.add(viaje);
				}
			}
		}

		return viajes;
	}

	// Método para busca un viaje por id, usado en varias funcionalidades
	public static Viaje buscarViaje(String id) {
		for (Empresa empresa : empresas) {
			for (Viaje viaje : empresa.getViajes()) {
				if (id.equals(viaje.getId())) {
					return viaje;
				}
			}
		}

		return null;
	}

	// Método para buscar un conductor por id, usado en la funcionalidad 5
	public static Conductor buscarConductor(String id) {
		for (Empresa empresa : Empresa.getEmpresas()) {
			for (Conductor conductor : empresa.getConductores()) {
				if (conductor.getId().equals(id)) {
					return conductor;
				}
			}
		}

		return null;
	}

	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(ArrayList<Viaje> viajes) {
		this.viajes = viajes;
	}

	public static ArrayList<Empresa> getEmpresas() {
		return empresas;
	}

	public static void setEmpresas(ArrayList<Empresa> empresas) {
		Empresa.empresas = empresas;
	}
	
	// Método para buscar empresas por nombre, usado en la funcionalidad 5
	public static Empresa buscarEmpresa(String nombre) {
		for (Empresa empresa : Empresa.empresas) {
			if (empresa.nombre.equals(nombre)) {
				return empresa;
			}
		}
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Conductor> getConductores() {
		return conductores;
	}

	public void setConductores(ArrayList<Conductor> conductores) {
		this.conductores = conductores;
	}

	// Método para añadir un conductor a una empresa, usado en la funcionalidad 5
	public void añadirConductor(Conductor conductor) {
		Boolean conductorNuevo = true;
		for (Conductor c : this.conductores) {
			if (conductor.equals(c)) {
				conductorNuevo = false;
			}
		}
		if (conductorNuevo) {
			this.conductores.add(conductor);
		}

		else {
			System.out.println("El conductor ya ha sido registrado");
		}
	}

	public ArrayList<Terminal> getTerminales() {
		return terminales;
	}

	public void setTerminales(ArrayList<Terminal> terminales) {
		this.terminales = terminales;
	}

	// Método para añadir una terminal a una empresa, usado en la funcionalidad 5
	public void añadirTerminal(Terminal terminal) {
		Boolean terminalNueva = true;
		for (Terminal t : terminales) {
			if (terminal.equals(t)) {
				terminalNueva = false;
			}
		}
		if (terminalNueva) {
			terminales.add(terminal);
		}

		else {
			System.out.println("La terminal ya ha sido registrada");
		}
	}

	// Método para eliminar un conductor de una empresa, usado en la funcionalidad 5
	public void eliminarConductor(Conductor conductorbuscado) {
		for (Conductor conductor : this.conductores) {
			if (conductor.equals(conductorbuscado)) {
				conductor = null;
			}
		}

	}
	
	// Método para eliminar una empresa por nombre, usado en la funcionalidad 5
	public static void eliminarEmpresa(String nombre) {

		Empresa empresa = Empresa.buscarEmpresa(nombre);
		empresas.remove(empresa);
	}

}
