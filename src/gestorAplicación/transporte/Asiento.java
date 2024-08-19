package gestorAplicación.transporte;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import gestorAplicación.gestion.Terminal;

public class Asiento implements Serializable{
	static final long serialVersionUID = 3L;
	private static ArrayList<Asiento> asientos = new ArrayList<Asiento>();
	private String numero;
	private boolean reservado;
	private LocalDateTime fechaReserva;
	private TipoAsiento tipoAsiento;
	private String color;

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
		this.tipoAsiento=tipo;
		this.color=tipo.getColor();
		asientos.add(this);
	}
	
	public static Asiento buscarAsiento(String numero,String tipo) {
		for(Asiento asiento:asientos) {
			if(asiento.getNumero()!=null && asiento.getTipoAsiento()!=null) {
			if(asiento.getNumero().equals(numero) && asiento.getTipoAsiento().equals(TipoAsiento.valueOf(tipo))) {
				return asiento;
			}}
		}
		return null;
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

	public void setTipoPorColor(String color) {
		if (color.toLowerCase().equals("azul")) {
			this.tipoAsiento=TipoAsiento.ESTANDAR;
			this.color=TipoAsiento.ESTANDAR.getColor();
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
		if(tipo.toUpperCase().equals(TipoAsiento.ESTANDAR.toString())) {
			tipoAsiento=TipoAsiento.ESTANDAR;
			this.color=TipoAsiento.ESTANDAR.getColor();
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
	
	public void liberar() {
		this.setReservado(false);
		this.setFechaReserva(null);
	}

	public String getColor() {
		return color;
	}
	
	public static ArrayList<Asiento> getAsientos() {
		return asientos;
	}

	public static void setAsientos(ArrayList<Asiento> asientos) {
		Asiento.asientos = asientos;
	}
}
