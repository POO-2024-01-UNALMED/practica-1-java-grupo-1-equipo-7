package gestorAplicación.transporte;

import java.io.Serializable;

import gestorAplicación.personas.Conductor;

public class Vehiculo implements Serializable {
	private String placa;
	private boolean disponible;
	private boolean peritaje;
	private int capacidad=0;
	private Conductor conductor;
	
	public Vehiculo() {
		peritaje=true;
	}
	
	public Vehiculo(String placa) {
		this.placa=placa;
		peritaje=true;
	}
	
	public Vehiculo(String placa, int capacidad) {
		this.placa=placa;
		this.setCapacidad(capacidad);
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

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}
}
