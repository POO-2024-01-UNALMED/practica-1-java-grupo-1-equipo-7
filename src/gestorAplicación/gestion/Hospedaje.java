package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import gestorAplicación.transporte.Asiento;

public class Hospedaje implements Serializable {
	private ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
	private int calificacion;
	private String nombre;
	private String ubicacion;
	
	public Hospedaje() {
		
	}
	
	public Hospedaje(String nombre, int pisos, int habitacionesPiso) {
		this.nombre = nombre;
		crearHabitaciones(pisos, habitacionesPiso);
	}
	
	public Hospedaje(String nombre,String ubicacion,ArrayList<Habitacion> habitacion) {
		this.nombre = nombre;
		this.setUbicacion(ubicacion);
		this.habitaciones=habitacion;

	}
	
	public void crearHabitaciones(int pisos, int habitacionesPiso) {
		for(int piso = 100; piso <= pisos * 100; piso += 100) {
			for(int habitacion = 1; habitacion <= habitacionesPiso; 
					habitacion++) {
				String numeroHabitacion = String.valueOf(piso + habitacion);
				
				Habitacion copiaHabitacion = 
						new Habitacion(numeroHabitacion);
				
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
		return "    " + nombre + "     " + calificacion + " estrellas" 
				+ "      " + this.habitacionesDisponibles();
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

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
}
