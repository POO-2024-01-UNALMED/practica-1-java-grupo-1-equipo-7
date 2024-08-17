package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import gestorAplicación.personas.Pasajero;
import gestorAplicación.personas.Persona;
import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.tipoVehiculo;

public class Tiquete implements Serializable {
	private static ArrayList<Tiquete> tiquetes = new ArrayList<Tiquete>();
	private static int numerosReserva = 1000000;
	private Pasajero pasajero;
	private Viaje viaje;
	private Asiento asiento;
	private String numeroReserva;
	private Hospedaje hospedaje;
	private tipoVehiculo tipovehiculo;
	
	public Tiquete() {
		
	}
	
	public Tiquete(Pasajero pasajero, Viaje viaje, Asiento asiento, 
			Hospedaje hospedaje, tipoVehiculo tipovehiculo) {
		this.pasajero = pasajero;
		this.viaje = viaje;
		this.asiento = asiento;
		this.numeroReserva = String.valueOf(numerosReserva);
		this.hospedaje=hospedaje;
		this.tipovehiculo=tipovehiculo;
		tiquetes.add(this);
		numerosReserva++;
	}
	
	public Tiquete(Pasajero pasajero, Viaje viaje, Asiento asiento) {
		this.pasajero = pasajero;
		this.viaje = viaje;
		this.asiento = asiento;
		this.numeroReserva = String.valueOf(numerosReserva);
		this.hospedaje = null;
		tiquetes.add(this);
		numerosReserva++;
	}
	
	public void cambiarAsiento(Asiento nuevoAsiento) {
		this.liberarAsiento();
		this.getViaje().reservarAsiento(nuevoAsiento.getNumero());
		this.setAsiento(nuevoAsiento);
	}
	
	public void cambiarViaje(Viaje viaje, String numeroAsiento) {
		this.liberarAsiento();
		this.setViaje(viaje);
		this.setAsiento(viaje.buscarAsiento(numeroAsiento));
		viaje.reservarAsiento(numeroAsiento);
	}
	
	public void liberarAsiento() {
		this.getViaje().liberarAsiento(this.
				getAsiento().getNumero());
	}
	
	@Override
	public String toString() {
		int nombre = 9 - pasajero.getNombre().length();
		String strNombre = String.valueOf(nombre);
		
		int _asiento = 16 - Integer.valueOf((String.valueOf(asiento.getNumero() + " " 
															+ asiento.getTipoAsiento())).length());
		String strAsiento = String.valueOf(_asiento);
		
		String spaceNombre;
		String spaceAsiento; 
		
		if(nombre == 0) {
			spaceNombre = "";
		} else {
			spaceNombre = String.format("%" + strNombre + "s", ""); 
		}
		
		if(_asiento == 0) {
			spaceAsiento = "";
		} else {
			spaceAsiento = String.format("%" + strAsiento + "s", "");
		}
		
		return "    " + numeroReserva + "               " + pasajero.getNombre() + spaceNombre
				+ "     " + asiento + spaceAsiento + "    " 
				+ viaje.getStrFecha() + " " + viaje.getHora() + "     " + viaje.getId();
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
	
	public tipoVehiculo getTipovehiculo() {
		return tipovehiculo;
	}

	public void setTipovehiculo(tipoVehiculo tipovehiculo) {
		this.tipovehiculo = tipovehiculo;
	}
} 
