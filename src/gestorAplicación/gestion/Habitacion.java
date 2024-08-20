package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import gestorAplicación.personas.Persona;
import gestorAplicación.transporte.Asiento;

public class Habitacion implements Serializable {
	private static final long serialVersionUID = 6655776532233087484L;
	private static ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
	private Hospedaje hospedaje;
	private String numeroHabitacion;
	private boolean reservada;
	private LocalDateTime fechaReserva;
	private String ubicacion;

	public Habitacion(String numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public Habitacion(Hospedaje hospedaje, String numeroHabitacion, String ubicacion) {
		this.hospedaje = hospedaje;
		this.numeroHabitacion = numeroHabitacion;
		this.ubicacion = ubicacion;
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

		if (reservada) {
			strBoolean = "Sí";
		} else {
			strBoolean = "No";
		}

		return "    " + numeroHabitacion + "                      " + strBoolean + "            " + disponibleEn();
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
		ubicacion=ubicacion.toUpperCase();
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

	public static void chequearHabitaciones() {
		for(Terminal terminal : Terminal.getTerminales()) {
			for (Hospedaje hospedaje : terminal.getHospedajes()) {
				for (Habitacion habitacion : hospedaje.getHabitaciones()) {
					if(habitacion.getFechaReserva() != null) {
						if(habitacion.getFechaReserva().isEqual(LocalDateTime.now()) 
								|| habitacion.getFechaReserva().
								isBefore(LocalDateTime.now())) {
							habitacion.liberar();
						} else {
							Duration duration = 
									Duration.between(LocalDateTime.now(), 
									habitacion.getFechaReserva());
							
							ScheduledExecutorService service = 
									Executors.newScheduledThreadPool(1);
							
							Runnable task = () -> {
								habitacion.liberar();
							};
							
							service.schedule(task, duration.toMinutes(), 
									TimeUnit.MINUTES);
						}
					} 
				}
			}
		}
	}
}
