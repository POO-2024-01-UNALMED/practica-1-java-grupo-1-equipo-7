package gestorAplicación.transporte;

public enum TipoAsiento {
	STANDART("azul"),
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
