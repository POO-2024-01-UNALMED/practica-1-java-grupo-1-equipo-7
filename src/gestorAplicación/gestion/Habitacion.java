package gestorAplicación.gestion;

import java.time.LocalDateTime;

import gestorAplicación.personas.Persona;

public class Habitacion {
	private Persona persona;
	private Hospedaje hospedaje;
	private String numeroHabitacion;
	private boolean reservada;
	private LocalDateTime tiempoReserva;
	
	public Habitacion() {
		
	}
	
	public Habitacion(String numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Hospedaje getHospedaje() {
		return hospedaje;
	}

	public void setHospedaje(Hospedaje hospedaje) {
		this.hospedaje = hospedaje;
	}

	public String getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(String numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public boolean isReservada() {
		return reservada;
	}

	public void setReservada(boolean reservada) {
		this.reservada = reservada;
	}

	public LocalDateTime getTiempoReserva() {
		return tiempoReserva;
	}

	public void setTiempoReserva(LocalDateTime tiempoReserva) {
		this.tiempoReserva = tiempoReserva;
	}
}
