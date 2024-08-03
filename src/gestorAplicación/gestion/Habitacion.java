package gestorAplicación.gestion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import gestorAplicación.personas.Persona;

public class Habitacion {
	private ArrayList<Persona> persona = new ArrayList<Persona>();
	private Hospedaje hospedaje;
	private String numeroHabitacion;
	private boolean reservada;
	private LocalDateTime tiempoReserva;
	private String ubicacion;
	
	public Habitacion() {
		
	}
	
	public Habitacion(String numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}
	
	public Habitacion(Hospedaje hospedaje,String numero,String ubicacion) {
		this.hospedaje=hospedaje;
		numeroHabitacion=numero;
		this.ubicacion=ubicacion;
	}
	
	@Override
	public String toString() {
		String strBoolean;
		if(reservada) {
			strBoolean="si";
		} else {
			strBoolean="no";
		}
		return "    " + numeroHabitacion.charAt(0) + "     "
				+ numeroHabitacion.substring(1) + strBoolean;
	}

	public ArrayList<Persona> getPersona() {
		return persona;
	}

	public void setPersona(ArrayList<Persona> persona) {
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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}
