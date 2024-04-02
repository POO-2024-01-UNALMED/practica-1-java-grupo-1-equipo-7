package gestorAplicación;

import java.io.Serializable;
import java.util.ArrayList;

public class Bus implements Serializable {
	private ArrayList<Viaje> viajesProgramados = new ArrayList<Viaje>();
	private Conductor conductorAsignado;
	private int capacidadPasajeros;
	private String placa;
	
	public Conductor getConductorAsignado() {
		return conductorAsignado;
	}
	
	public void setConductorAsignado(Conductor conductorAsignado) {
		this.conductorAsignado = conductorAsignado;
	}
	
	public int getCapacidadPasajeros() {
		return capacidadPasajeros;
	}
	
	public void setCapacidadPasajeros(int capacidadPasajeros) {
		this.capacidadPasajeros = capacidadPasajeros;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
}
