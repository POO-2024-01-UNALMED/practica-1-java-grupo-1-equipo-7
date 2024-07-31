package gestorAplicación.transporte;

public class Vehiculo {
	private String placa;
	private boolean disponible;
	private boolean peritaje;
	
	public Vehiculo() {
		peritaje=true;
	}
	
	public Vehiculo(String placa) {
		this.placa=placa;
		peritaje=true;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public boolean isPeritaje() {
		return peritaje;
	}
	public void setPeritaje(boolean peritaje) {
		this.peritaje = peritaje;
	}
}
