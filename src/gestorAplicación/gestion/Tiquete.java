package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import gestorAplicación.personas.Pasajero;
import gestorAplicación.transporte.Asiento;

public class Tiquete implements Serializable {
	private Pasajero pasajero;
	private String costo;
	private Viaje viaje;
	private Asiento asiento;
	private LocalDate fechaCompra;
	private String referenciaReserva;
	private static ArrayList<Tiquete> tiquetes = new ArrayList<Tiquete>();
	private static int referencias = 1;
	
	public Tiquete(Pasajero pasajero, Viaje viaje, Asiento asiento) {
		this.pasajero = pasajero;
		this.viaje = viaje;
		this.asiento = asiento;
		this.referenciaReserva = String.valueOf(referencias);
		tiquetes.add(this);
		referencias++;
	}
	
	public static ArrayList<Tiquete> buscarTiquetesValidos(ArrayList<Tiquete> tiquetes) {
		ArrayList<Tiquete> tiquetesValidos = new ArrayList<Tiquete>();
		for(Tiquete tiquete : tiquetes) {
			if(tiquete.getViaje().getFecha().isAfter(LocalDate.now())) {
				tiquetesValidos.add(tiquete);
			}
		}
		 return tiquetesValidos;
	}
	
	public static ArrayList<Tiquete> buscarTiquetesVencidos(ArrayList<Tiquete> tiquetes) {
		ArrayList<Tiquete> tiquetesVencidos = new ArrayList<Tiquete>();
		for(Tiquete tiquete : tiquetes) {
			if(tiquete.getViaje().getFecha().isBefore(LocalDate.now())) {
				tiquetesVencidos.add(tiquete);
			}
		}
		 return tiquetesVencidos;
	}
	
	public static Tiquete buscarTiquete(String numeroReferencia) {
		for(Tiquete tiquete : tiquetes) {
			if(tiquete.getReferenciaReserva().equals(numeroReferencia)) {
				return tiquete;
			}
		}
		return null;
	}
	
	/*public String toString() {
		
		
	}*/
	
	public Viaje getViaje() {
		return viaje;
	}
	
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	
	public Pasajero getPasajero() {
		return pasajero;
	}
	
	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public String getReferenciaReserva() {
		return referenciaReserva;
	}

	public void setReferenciaReserva(String referenciaReserva) {
		this.referenciaReserva = referenciaReserva;
	}
	
	public static ArrayList<Tiquete> getTiquetes() {
		return tiquetes;
	}
} 
