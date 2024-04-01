package gestorAplicación;

import java.io.Serializable;
import java.util.ArrayList;

public class Bus implements Serializable {
	ArrayList<Viaje> viajesProgramados = new ArrayList<Viaje>();
	Conductor conductorAsignado;
	int capacidadPasajeros;
	private String placa;
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	
}
