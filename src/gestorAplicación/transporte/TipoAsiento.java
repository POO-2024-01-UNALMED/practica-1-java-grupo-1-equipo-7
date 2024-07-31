package gestorAplicación.transporte;

public enum TipoAsiento {
	STANDART("azul"),PREMIUM("amarillo"),PREFERENCIAL("rojo");

	TipoAsiento(String color) {
		this.setColor(color);
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	private String color;
}
