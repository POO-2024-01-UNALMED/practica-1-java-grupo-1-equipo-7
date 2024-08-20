package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import gestorAplicación.personas.Conductor;
import gestorAplicación.transporte.Asiento;

public class Hospedaje implements Serializable {
	private static final long serialVersionUID = 3316398631943862366L;
	private static ArrayList<Hospedaje> hospedajes = new ArrayList<Hospedaje>();
	private ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
	private ArrayList<String> calificaciones = new ArrayList<String>();
	private double calificacion;
	private String nombre;
	private String ubicacion;

	public Hospedaje() {
		hospedajes.add(this);
	}

	public Hospedaje(String nombre, int pisos, int habitacionesPiso) {
		this.nombre = nombre;
		crearHabitaciones(pisos, habitacionesPiso);
		hospedajes.add(this);
	}

	public Hospedaje(String nombre, String ubicacion) {
		ubicacion=ubicacion.toUpperCase();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		hospedajes.add(this);
	}

	public void crearHabitaciones(int pisos, int habitacionesPiso) {
		for (int piso = 100; piso <= pisos * 100; piso += 100) {
			for (int habitacion = 1; habitacion <= habitacionesPiso; habitacion++) {
				String numeroHabitacion = String.valueOf(piso + habitacion);

				Habitacion copiaHabitacion = new Habitacion(numeroHabitacion);

				habitaciones.add(copiaHabitacion);

				copiaHabitacion.setHospedaje(this);
			}
		}
	}

	public boolean tieneHabitaciones() {
		for (Habitacion habitacion : habitaciones) {
			if (!habitacion.isReservada()) {
				return true;
			}
		}

		return false;
	}

	public Habitacion buscarHabitacion(String numeroHabitacion) {
		for (Habitacion habitacion : this.getHabitaciones()) {
			if (habitacion.getNumeroHabitacion().equals(numeroHabitacion)) {
				return habitacion;
			}
		}

		return null;
	}

	public int habitacionesDisponibles() {
		int numeroHabitaciones = 0;

		for (Habitacion habitacion : this.getHabitaciones()) {
			if (!habitacion.isReservada()) {
				numeroHabitaciones += 1;
			}
		}

		return numeroHabitaciones;
	}

	public void reservarHabitacion(String numeroHabitacion, LocalDateTime fechaReserva, 
			Tiquete tiquete) {
		for (Habitacion habitacion : this.getHabitaciones()) {
			if (habitacion.getNumeroHabitacion().equals(numeroHabitacion)) {
				habitacion.reservar(fechaReserva);
				tiquete.setHospedaje(this);
				break;
			}
		}
	}

	public static Hospedaje buscarHospedaje(String nombre, String ubi) {
		ubi=ubi.toUpperCase();
		for (Hospedaje hospedaje : Hospedaje.hospedajes) {
			if (hospedaje.nombre.equals(nombre) && hospedaje.ubicacion != null) {
				if (hospedaje.ubicacion.equals(ubi)) {
					return hospedaje;
				}
			}
		}
		return null;
	}

	public void liberarHabitacion(String numeroHabitacion) {
		for (Habitacion habitacion : this.getHabitaciones()) {
			if (habitacion.getNumeroHabitacion().equals(numeroHabitacion)) {
				habitacion.liberar();
				break;
			}
		}
	}

	@Override
	public String toString() {
	int nombre = 5 - this.nombre.length();
	String strNombre = String.valueOf(nombre);
	
	String spaceNombre;

	if (nombre == 0) {
		spaceNombre = "";
	} else {
		spaceNombre = String.format("%" + strNombre + "s", "");
	}

		return "    " + this.nombre + spaceNombre + "      " + this.calificacion + " estrellas" + "     " 
				+ this.habitacionesDisponibles();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificaciones.add(calificacion);
		double contador = 0;
		double acumulador = 0;
		for (String calificación : this.calificaciones) {
			contador++;
			double nota = Double.parseDouble(calificación);
			acumulador += nota;
		}
		this.calificacion = acumulador / contador;

	}

	public String getUbicacion() {
		return ubicacion;
	}

	public static void eliminarHospedaje(String nombre, String ubicacion) {
		ubicacion=ubicacion.toUpperCase();

		Hospedaje hospedaje = Hospedaje.buscarHospedaje(nombre, ubicacion);
		hospedajes.remove(hospedaje);

	}

	public void eliminarHabitacion(String numero) {

		Habitacion habitacion = this.buscarHabitacion(numero);
		habitaciones.remove(habitacion);

	}

	public void setUbicacion(String ubicacion) {
		ubicacion=ubicacion.toUpperCase();
		this.ubicacion = ubicacion;
	}

	public static ArrayList<Hospedaje> getHospedajes() {
		return hospedajes;
	}
}
