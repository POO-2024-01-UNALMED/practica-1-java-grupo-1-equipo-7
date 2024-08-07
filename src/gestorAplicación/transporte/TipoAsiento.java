package gestorAplicación.transporte;

public enum TipoAsiento {
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
