package gestorAplicación.gestion;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import baseDatos.Deserializador;

public class Empresa implements Serializable {
	private static ArrayList<Empresa> empresas = new ArrayList<Empresa>();
//	static final long serialVersionUID = 1L;
	public ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	public String nombre;
	
	public Empresa() {
		Deserializador.deserializar(this);
		empresas.add(this);
	}
	
	public Empresa(String nombre) {
		this.nombre = nombre;
		empresas.add(this);
	}
	
	public static ArrayList<Viaje> buscarViajes(LocalDate fecha) {
		ArrayList<Viaje> viajes = new ArrayList<Viaje>();

		for (Empresa empresa : empresas) {
			for (Viaje viaje : empresa.getViajes()) {
				if (fecha.equals(viaje.getFecha())) {
					viajes.add(viaje);
				}
			}
		}

		return viajes;
	}

	public static ArrayList<Viaje> buscarViajes(String origen, String destino) {
		ArrayList<Viaje> viajes = new ArrayList<Viaje>();

		if (destino.isBlank()) {
			for (Empresa empresa : empresas) {
				for (Viaje viaje : empresa.getViajes()) {
					if 
					(
					origen.equals(viaje.getTerminalOrigen().getUbicacion())
					) {
						viajes.add(viaje);
					}
				}
			}
		} else if (origen.isBlank()) {
			for (Empresa empresa : empresas) {
				for (Viaje viaje : empresa.getViajes()) {
					if 
					(
					destino.equals(viaje.getTerminalDestino().getUbicacion())
					) {
						viajes.add(viaje);
					}
				}
			}
		} else {
			for (Empresa empresa : empresas) {
				for (Viaje viaje : empresa.getViajes()) {
					if 
					(
					origen.equals(viaje.getTerminalOrigen().getUbicacion()) &&
					destino.equals(viaje.getTerminalDestino().getUbicacion())  					
					) {
						viajes.add(viaje);
					}
				}
			}
		}

		return viajes;
	}
	
	public static ArrayList<Viaje> buscarViajes(LocalTime hora) {
		ArrayList<Viaje> viajes = new ArrayList<Viaje>();
		
		for (Empresa empresa : empresas) {
			for (Viaje viaje : empresa.getViajes()) {
				if (hora.equals(viaje.getHora())) {
					viajes.add(viaje);
				}
			}
		}

		return viajes;
	}
	
	public static ArrayList<Viaje> buscarViajes(String string) {
		ArrayList<Viaje> viajes = new ArrayList<Viaje>();
		
		for (Empresa empresa : empresas) {
			for (Viaje viaje : empresa.getViajes()) {
				if (string.equals(viaje.getId())) {
					viajes.add(viaje);
				}
			}
		}

		return viajes;
	}
	
	public static Viaje buscarViaje(String id) {
		for (Empresa empresa : empresas) {
			for (Viaje viaje : empresa.getViajes()) {
				if (id.equals(viaje.getId())) {
					return viaje;
				}
			}
		}
		
		return null;
	}

	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(ArrayList<Viaje> viajes) {
		this.viajes = viajes;
	}

	public static ArrayList<Empresa> getEmpresas() {
		return empresas;
	}

	public static void setEmpresas(ArrayList<Empresa> empresas) {
		Empresa.empresas = empresas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
