package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import gestorAplicación.personas.Pasajero;
import gestorAplicación.transporte.Asiento;

public class Tiquete implements Serializable {
	private static ArrayList<Tiquete> tiquetes = new ArrayList<Tiquete>();
	private static int referencias = 1;
	private Pasajero pasajero;
	private String costo;
	private Viaje viaje;
	private Asiento asiento;
	private LocalDateTime fechaCompra;
	private String numeroReserva;
	private Hospedaje hospedaje;
	
	public Tiquete() {
		
	}
	
	public Tiquete(Pasajero pasajero, Viaje viaje, Asiento asiento, 
			LocalDateTime fechaCompra) {
		this.pasajero = pasajero;
		this.viaje = viaje;
		this.asiento = asiento;
		this.fechaCompra = fechaCompra;
		this.numeroReserva = String.valueOf(referencias);
		tiquetes.add(this);
		referencias++;
	}
	
	public static ArrayList<Tiquete> buscarTiquetes(ArrayList<Tiquete> tiquetes,
			String tipoTiquetes) {
		
		if(tipoTiquetes.equals("validos")) {
			ArrayList<Tiquete> tiquetesValidos = new ArrayList<Tiquete>();
			
			for(Tiquete tiquete : tiquetes) {
				if(tiquete.getViaje().getFecha().isAfter(LocalDate.now())) {
					tiquetesValidos.add(tiquete);
				}
			}
			
			return tiquetesValidos;
		} else {
			ArrayList<Tiquete> tiquetesVencidos = new ArrayList<Tiquete>();
			
			for(Tiquete tiquete : tiquetes) {
				if(tiquete.getViaje().getFecha().isBefore(LocalDate.now())) {
					tiquetesVencidos.add(tiquete);
				}
			}
			
			return tiquetesVencidos;
		}
	}
	
	public static Tiquete buscarTiquete(String numeroReserva) {
		for(Tiquete tiquete : tiquetes) {
			if(tiquete.getNumeroReserva().equals(numeroReserva)) {
				return tiquete;
			}
		}
		return null;
	}
	
	public void cambiarAsiento(Asiento nuevo, Asiento viejo) {
		this.getViaje().reservarAsiento(viejo.getNumeroAsiento());
		this.setAsiento(nuevo);
	}
	
	@Override
	public String toString() {
		return "    " + pasajero.getNombre() + "     " 
				+ asiento.getNumeroAsiento() + "         " + getStrFecha() 
				+ "    " + numeroReserva;
	}
	
	public String getStrFecha() {
		DateTimeFormatter formateoFecha = 
		DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); 
		
		String strFecha = fechaCompra.format(formateoFecha);
				
		return strFecha;
	}
	
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

	public String getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(String numeroReserva) {
		this.numeroReserva = numeroReserva;
	}
	
	public static ArrayList<Tiquete> getTiquetes() {
		return tiquetes;
	}

	public Hospedaje getHospedaje() {
		return hospedaje;
	}

	public void setHospedaje(Hospedaje hospedaje) {
		this.hospedaje = hospedaje;
	}
} 
