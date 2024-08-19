package gestorAplicación.transporte;

import java.io.Serializable;

import gestorAplicación.personas.Conductor;

public class Vehiculo implements Serializable {
	private String placa;
	private Conductor conductor;
	
	public Vehiculo() {
		
	}
	
	public Vehiculo(String placa) {
		this.placa=placa;
		
	}

	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}
}
