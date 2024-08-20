package gestorAplicación.personas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Hospedaje;
import gestorAplicación.gestion.Tiquete;
import gestorAplicación.gestion.Viaje;

public class Pasajero extends Persona implements Serializable {
	private static final long serialVersionUID = 8L;
	private static ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
	private ArrayList<Tiquete> tiquetes = new ArrayList<Tiquete>();

	public Pasajero() {
		this("Sin nombre","0");
		pasajeros.add(this);
	}

	public Pasajero(String nombre, String id) {
		this(nombre, id,"0000000000","No tiene");
		pasajeros.add(this);
	}

	public Pasajero(String nombre, String idPasajero, String telefono, String correo) {
		super(nombre, idPasajero, telefono, correo);
		pasajeros.add(this);
	}

	// Método para buscar un pasajero por id, usado en varias funcionalidades
	public static Pasajero buscarPasajero(String id) {
		for (Pasajero pasajero : pasajeros) {
			if (pasajero.getId().equals(id)) {
				return pasajero;
			}
		}
		return null;
	}

	// Método para buscar un pasajero por nombre e id, usado en varias funcionalidades
	public static Pasajero buscarPasajero(String nombre, String id) {
		for (Pasajero pasajero : pasajeros) {
			if (pasajero.nombre.equals(nombre) && pasajero.id != null) {
				if (pasajero.id.equals(id)) {
					return pasajero;
				}
			}
		}
		return null;
	}

	// Método para buscar los tiquetes válidos y vencidos asociados con un pasajero, 
	// usado en la funcionalidad 3
	public ArrayList<Tiquete> buscarTiquetes(String tipoTiquetes) {

		if (tipoTiquetes.equals("validos")) {
			ArrayList<Tiquete> tiquetesValidos = new ArrayList<Tiquete>();

			for (Tiquete tiquete : this.getTiquetes()) {
				tiquete.setViaje(Empresa.buscarViaje(tiquete.getViaje().getId()));
				if (tiquete.getViaje().getFecha().isAfter(LocalDate.now())) {
					tiquetesValidos.add(tiquete);
				}
			}

			return tiquetesValidos;
		} else if (tipoTiquetes.equals("vencidos")) {
			ArrayList<Tiquete> tiquetesVencidos = new ArrayList<Tiquete>();

			for (Tiquete tiquete : this.getTiquetes()) {
				tiquete.setViaje(Empresa.buscarViaje(tiquete.getViaje().getId()));
				if (tiquete.getViaje().getFecha().isBefore(LocalDate.now())) {
					tiquetesVencidos.add(tiquete);
				}
			}

			return tiquetesVencidos;
		} else {
			return null;
		}
	}

	// Método para buscar un tiquete de acuerdo al viaje, usado en la funcionalidad 3
	public Tiquete buscarTiquete(Viaje viaje) {
		for (Tiquete tiquete : tiquetes) {
			tiquete.setViaje(Empresa.buscarViaje(viaje.getId()));
			if (tiquete.getViaje().equals(viaje)) {
				return tiquete;
			}
		}

		return null;
	}

	// Método para buscar un tiquete de acuerdo al numero de reserva, usado en la funcionalidad 3
	public Tiquete buscarTiquete(String numeroReserva) {
		for (Tiquete tiquete : tiquetes) {
			if (tiquete.getNumeroReserva().equals(numeroReserva)) {
				return tiquete;
			}
		}

		return null;
	}

	// Método para buscar un tiquete de acuerdo al numero de reserva, usado en la funcionalidad 3
	public void cancelarTiquete(Tiquete tiquete) {
		for (Tiquete _tiquete : this.getTiquetes()) {
			if (_tiquete.equals(tiquete)) {
				_tiquete.liberarAsiento();
				this.getTiquetes().remove(_tiquete);
				break;
			}
		}
	}

	// Método para agregar un tiquete, usado en la funcionalidad 2
	public void agregarTiquete(Tiquete tiquete) {
		tiquetes.add(tiquete);
	}

	public ArrayList<Tiquete> getTiquetes() {
		return tiquetes;
	}

	public void setTiquetes(ArrayList<Tiquete> tiquetes) {
		this.tiquetes = tiquetes;
	}

	public static ArrayList<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public static void setPasajeros(ArrayList<Pasajero> pasajeros) {
		Pasajero.pasajeros = pasajeros;
	}

	// Método para eliminar un pasajero por nombre e id, usado en la funcionalidad 5
	public static void eliminarPasajero(String nombre, String id) {

		Pasajero pasajero = Pasajero.buscarPasajero(nombre, id);
		pasajeros.remove(pasajero);

	}
	
	// Método para eliminar un pasajero por id, usado en la funcionalidad 5
	public static void eliminarPasajero(String id) {

		Pasajero pasajero = Pasajero.buscarPasajero(id);
		pasajeros.remove(pasajero);

	}

	@Override
	public String toString() {

		return "     " + this.getNombre() + "        " + this.getId() + "        " + this.getTelefono() + "           "
				+ this.getCorreo() + "     ";
	}
}
