package gestorAplicación.transporte;

import java.util.ArrayList;

import gestorAplicación.gestion.Viaje;

public class MiniVan extends Vehiculo{
	

		private static ArrayList<MiniVan> minivan = new ArrayList<MiniVan>();
		private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
		private int cantidadAsientos;
	
	
	public MiniVan() {
		this.setPeritaje(true);
		minivan.add(this);
	}
	
	public MiniVan(String placa, int cantidadAsientos) {
		this.setPlaca(placa);
		this.setPeritaje(true);
		this.cantidadAsientos=cantidadAsientos;
		minivan.add(this);
	}

	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public int getCantidadAsientos() {
		return cantidadAsientos;
	}

	public void setCantidadAsientos(int cantidadAsientos) {
		this.cantidadAsientos = cantidadAsientos;
	}

}
