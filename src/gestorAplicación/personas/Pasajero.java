package gestorAplicación.personas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Hospedaje;
import gestorAplicación.gestion.Tiquete;
import gestorAplicación.gestion.Viaje;

public class Pasajero extends Persona implements Serializable {
	private static final long serialVersionUID = -8124260530486820488L;
	private static ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
	private ArrayList<Tiquete> tiquetes = new ArrayList<Tiquete>();

	public Pasajero() {
		pasajeros.add(this);
	}

	public Pasajero(String nombre, String id) {
		super(nombre, id);
		pasajeros.add(this);
	}

	public Pasajero(String nombre, String idPasajero, String telefono, String correo) {
		super(nombre, idPasajero, telefono, correo);
		pasajeros.add(this);
	}

	public static Pasajero buscarPasajero(String id) {
		for (Pasajero pasajero : pasajeros) {
			if (pasajero.getId().equals(id)) {
				return pasajero;
			}
		}
		return null;
	}

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

	public Tiquete buscarTiquete(Viaje viaje) {
		for (Tiquete tiquete : tiquetes) {
			tiquete.setViaje(Empresa.buscarViaje(viaje.getId()));
			if (tiquete.getViaje().equals(viaje)) {
				return tiquete;
			}
		}

		return null;
	}

	public Tiquete buscarTiquete(String numeroReserva) {
		for (Tiquete tiquete : tiquetes) {
			if (tiquete.getNumeroReserva().equals(numeroReserva)) {
				return tiquete;
			}
		}

		return null;
	}

	public void cancelarTiquete(Tiquete tiquete) {
		for (Tiquete _tiquete : this.getTiquetes()) {
			if (_tiquete.equals(tiquete)) {
				_tiquete.liberarAsiento();
				this.getTiquetes().remove(_tiquete);
				break;
			}
		}
	}

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

	public static void eliminarPasajero(String nombre, String id) {

		Pasajero pasajero = Pasajero.buscarPasajero(nombre, id);
		pasajeros.remove(pasajero);

	}

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
