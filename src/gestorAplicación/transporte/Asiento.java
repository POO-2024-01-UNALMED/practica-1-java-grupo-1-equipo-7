package gestorAplicación.transporte;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Viaje;

public class Asiento implements Serializable {
	private static final long serialVersionUID = 6674047871371131306L;
	private static ArrayList<Asiento> asientos = new ArrayList<Asiento>();
	private String numero;
	private boolean reservado;
	private LocalDateTime fechaReserva;
	private TipoAsiento tipoAsiento;

	public Asiento() {
		this("Indefinido");
		asientos.add(this);
	}

	public Asiento(String numero) {
		this.numero = numero;
		asientos.add(this);
	}

	public Asiento(String numero, TipoAsiento tipo) {
		this.numero = numero;
		this.tipoAsiento = tipo;
		asientos.add(this);
	}
	
	public static void chequearAsientos() {
		for(Empresa empresa : Empresa.getEmpresas()) {
			for (Viaje viaje : empresa.getViajes()) {
				for (Asiento asiento : viaje.listaAsientos()) {
					if(asiento.getFechaReserva() != null) {
						if(asiento.getFechaReserva().isEqual(LocalDateTime.now()) 
								|| asiento.getFechaReserva().
								isBefore(LocalDateTime.now())) {
							asiento.liberar();
						} else {
							Duration duration = 
									Duration.between(LocalDateTime.now(), 
									asiento.getFechaReserva());
							
							ScheduledExecutorService service = 
									Executors.newScheduledThreadPool(1);
							
							Runnable task = () -> {
								asiento.liberar();
							};
							
							service.schedule(task, duration.toMinutes(), 
									TimeUnit.MINUTES);
						}
					} 
				}
			}
		}
	}

	public static Asiento buscarAsiento(String numero, String tipo) {
		for (Asiento asiento : asientos) {
			if (asiento.getNumero() != null && asiento.getTipoAsiento() != null) {
				if (asiento.getNumero().equals(numero) && asiento.getTipoAsiento().
						equals(TipoAsiento.valueOf(tipo))) {
					return asiento;
				}
			}
		}
		return null;
	}

	public void reservar(LocalDateTime fechaReserva) {
		if (fechaReserva != null) {
			this.setReservado(true);
			this.setFechaReserva(fechaReserva);
		} else {
			this.setReservado(true);
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDateTime fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String toString() {
		return numero + " " + tipoAsiento;
	}

	public TipoAsiento getTipoAsiento() {
		return tipoAsiento;

	}

	public void liberar() {
		this.setReservado(false);
		this.setFechaReserva(null);
	}

	public static ArrayList<Asiento> getAsientos() {
		return asientos;
	}

	public static void setAsientos(ArrayList<Asiento> asientos) {
		Asiento.asientos = asientos;
	}
}
