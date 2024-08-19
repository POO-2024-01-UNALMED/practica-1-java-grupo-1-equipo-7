package gestorAplicación.transporte;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import gestorAplicación.gestion.Terminal;

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
