package gestorAplicación.transporte;

import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicación.gestion.Viaje;

public class Bus implements Serializable {
	private ArrayList<Asiento> asientos = new ArrayList<Asiento>();
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private int totalAsientos;
	private String placa;
	private static ArrayList<Bus> buses = new ArrayList<Bus>();

	public Bus(String placa, int totalAsientos) {
		this.placa = placa;
		this.totalAsientos = totalAsientos;
		this.crearAsientos(totalAsientos);
		buses.add(this);
	}

	public void crearAsientos(int totalAsientos) {
		String letras = "ABCD";

		for (int i = 1; i < totalAsientos + 1; i++) {
			for (int j = 0; j < 4; j++) {
				String numeroAsiento = String.valueOf(i) + letras.charAt(j);
				asientos.add(new Asiento(numeroAsiento));
			}
		}
	}

	public int getTotalAsientos() {
		return totalAsientos;
	}

	public void setTotalAsientos(int totalAsientos) {
		this.totalAsientos = totalAsientos;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public ArrayList<Asiento> getAsientos() {
		return asientos;
	}
	
	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

}
