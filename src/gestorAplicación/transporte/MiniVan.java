package gestorAplicación.transporte;

import java.util.ArrayList;

import gestorAplicación.gestion.Viaje;

public class MiniVan extends Vehiculo{
	

		private static ArrayList<MiniVan> minivan = new ArrayList<MiniVan>();
		private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
		private ArrayList<Asiento> asientos = new ArrayList<Asiento>();
	
	
	public MiniVan() {
		this.setPeritaje(true);
		minivan.add(this);
	}
	
	public MiniVan(String placa, ArrayList<Asiento> asientos) {
		this.setPlaca(placa);
		this.setPeritaje(true);
		this.setAsientos(asientos);
		minivan.add(this);
	}

	public ArrayList<Viaje> getViajes() {
		return viajes;
	}


	public ArrayList<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(ArrayList<Asiento> asientos) {
		this.asientos = asientos;
	}
	
	public void añadirViaje(Viaje viaje) {
		viajes.add(viaje);
	}
	
	public void setViajes(ArrayList<Viaje> viaje) {
		viajes=viaje;
	}

}
