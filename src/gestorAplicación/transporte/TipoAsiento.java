package gestorAplicación.transporte;

public enum TipoAsiento {
	STANDART("azul"),PREMIUM("amarillo"),PREFERENCIAL("rojo");

	TipoAsiento(String color) {
		this.color=color;
	}
	
	public String getColor() {
		return color;
	}

	private String color;
}
