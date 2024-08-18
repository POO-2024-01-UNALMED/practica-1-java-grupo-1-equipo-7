package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import gestorAplicación.personas.Persona;

public class Habitacion implements Serializable {
	private static final long serialVersionUID = 6655776532233087484L;
	private static ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
	private ArrayList<Persona> persona = new ArrayList<Persona>();
	private Hospedaje hospedaje;
	private String numeroHabitacion;
	private boolean reservada;
	private LocalDateTime fechaReserva;
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
	
	public void reservar(LocalDateTime fechaReserva) {
		this.setReservada(true);
		this.setFechaReserva(fechaReserva);
	}
	
	public void liberar() {
		this.setReservada(false);
		this.setFechaReserva(null);
	}
	
	public String disponibleEn() {
		if (fechaReserva != null) {
			Duration duration = Duration.between(LocalDateTime.now(), fechaReserva);
			return String.valueOf(duration.toDaysPart()) + " días "
					+ String.valueOf(duration.toHoursPart()) + " horas "
					+ String.valueOf(duration.toMinutesPart()) + " minutos";
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		String strBoolean;
		
		if(reservada) {
			strBoolean="Sí";
		} else {
			strBoolean="No";
		}
		
		return "    " + numeroHabitacion + "                      " + strBoolean + "            " 
				+ disponibleEn();
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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDateTime fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public static ArrayList<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public static void setHabitaciones(ArrayList<Habitacion> habitaciones) {
		Habitacion.habitaciones = habitaciones;	
	}
}
