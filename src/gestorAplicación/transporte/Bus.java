package gestorAplicación.transporte;

import java.io.Serializable;
import java.util.ArrayList;

import baseDatos.Deserializador;
import gestorAplicación.gestion.Viaje;

public class Bus extends Vehiculo implements Serializable {
	private static ArrayList<Bus> buses = new ArrayList<Bus>();
//	static final long serialVersionUID = 4L;
	private ArrayList<Asiento> asientos = new ArrayList<Asiento>();
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private String placa;
	
	public Bus() {
//		Deserializador.deserializar(this);
		buses.add(this);
	}
	
	public Bus(String placa, int asientos) {
		this.placa = placa;
		this.crearAsientos(asientos);
		buses.add(this);
	}

	public void crearAsientos(int asientos) {
		String letras = "ABCD";

		for (int numero = 1; numero < asientos + 1; numero++) {
			for (int letra = 0; letra < 4; letra++) {
				String numeroAsiento = String.valueOf(numero) + 
				letras.charAt(letra);
				
				this.asientos.add(new Asiento(numeroAsiento));
			}
		}
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
	
	public void setAsientos(ArrayList<Asiento> asientos) {
		this.asientos = asientos;
	}
	
	public ArrayList<Viaje> getViajes() {
		return viajes;
	}
}
