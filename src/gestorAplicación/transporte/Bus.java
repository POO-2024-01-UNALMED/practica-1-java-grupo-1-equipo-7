package gestorAplicación.transporte;

import java.io.Serializable;
import java.util.ArrayList;

import baseDatos.Deserializador;
import gestorAplicación.gestion.Viaje;


public class Bus extends Vehiculo implements Serializable {

	private static final long serialVersionUID = 2378919680109241789L;
	private static ArrayList<Bus> buses = new ArrayList<Bus>();
	private ArrayList<Asiento> asientos = new ArrayList<Asiento>();
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private int[] tiposAsientoFila;

	public Bus() {
		this.setPeritaje(true);
		buses.add(this);
	}
	
	public Bus(String placa, int asientos, 
			int[] tiposAsientoFila) {
		this.setPlaca(placa);
		this.setPeritaje(true);
		this.tiposAsientoFila = tiposAsientoFila;
		this.crearAsientos(asientos);
		buses.add(this);
	}

	public void crearAsientos(int asientos) {
		String letras = "ABCD";

		for (int numero = 1; numero < asientos + 1; numero++) {
			for (int letra = 0; letra < 4; letra++) {
				String numeroAsiento = String.valueOf(numero) + 
				letras.charAt(letra);
				
				if (numero <= tiposAsientoFila[0]) {
					TipoAsiento tipo = TipoAsiento.PREFERENCIAL;
					this.asientos.add(new Asiento(numeroAsiento, tipo));
				} else if (numero <= tiposAsientoFila[1]) {
					TipoAsiento tipo = TipoAsiento.PREMIUM;
					this.asientos.add(new Asiento(numeroAsiento, tipo));
				} else {
					TipoAsiento tipo = TipoAsiento.ESTANDAR;
					this.asientos.add(new Asiento(numeroAsiento, tipo));
				}
			}
		}
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
	
	public void añadirViaje(Viaje viaje) {
		viajes.add(viaje);
	}
	
	public void setViajes(ArrayList<Viaje> viaje) {
		viajes=viaje;
	}
	
	public int[] getTiposAsiento() {
		return tiposAsientoFila;
	}
}
