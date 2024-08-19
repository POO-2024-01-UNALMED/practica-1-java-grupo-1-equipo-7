package gestorAplicación.transporte;

import java.io.Serializable;

public enum TipoAsiento implements Serializable{
	ESTANDAR("azul"),
	PREMIUM("amarillo"),
	PREFERENCIAL("rojo");
	
	private String color;

	TipoAsiento(String color) {
		this.color=color;
	}
	
	public String getColor() {
		return color;
	}
}
