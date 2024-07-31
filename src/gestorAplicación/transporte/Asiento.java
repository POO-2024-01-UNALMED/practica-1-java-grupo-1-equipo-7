package gestorAplicación.transporte;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Asiento implements Serializable{
	static final long serialVersionUID = 3L;
	private String numeroAsiento;
	private boolean reservado;
	private LocalDateTime fechaReserva;
	private TipoAsiento tipoAsiento;
	private String color;

	
	public Asiento() {
		this("Indefinido");
	}
	
	public Asiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}
	
	public Asiento(String numeroAsiento, TipoAsiento tipo) {
		this.numeroAsiento=numeroAsiento;
		this.tipoAsiento=tipo;
		this.color=tipo.getColor();
	}
	
	public String getNumeroAsiento() {
		return numeroAsiento;
	}
	
	public void setNumeroAsiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
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

	public void setTipoPorColor(String color) {
		if (color.toLowerCase().equals("azul")) {
			this.tipoAsiento=TipoAsiento.STANDART;
			this.color=TipoAsiento.STANDART.getColor();
		}
		if (color.toLowerCase().equals("amarillo")) {
			this.tipoAsiento=TipoAsiento.PREMIUM;
			this.color=TipoAsiento.PREMIUM.getColor();
		}
		if (color.toLowerCase().equals("rojo")) {
			this.tipoAsiento=TipoAsiento.PREFERENCIAL;
			this.color=TipoAsiento.PREFERENCIAL.getColor();
		}
	}
	
	public void setTipoAsiento(String tipo) {
		if(tipo.toUpperCase().equals(TipoAsiento.STANDART.toString())) {
			tipoAsiento=TipoAsiento.STANDART;
			this.color=TipoAsiento.STANDART.getColor();
		}
		if(tipo.toUpperCase().equals(TipoAsiento.PREFERENCIAL.toString())) {
			tipoAsiento=TipoAsiento.PREFERENCIAL;
			this.color=TipoAsiento.PREFERENCIAL.getColor();
		}
		if(tipo.toUpperCase().equals(TipoAsiento.PREMIUM.toString())) {
			tipoAsiento=TipoAsiento.PREMIUM;
			this.color=TipoAsiento.PREMIUM.getColor();
		}}
			
	public TipoAsiento getTipoAsiento() {
		return tipoAsiento;

	}

	public String getColor() {
		return color;
	}
	
	
}
