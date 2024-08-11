package gestorAplicación.gestion;

import java.util.ArrayList;

public class Hospedaje {
	private ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
	private int calificacion;
	private String nombre;
	private String ubicacion;
	
	public Hospedaje() {
		
	}
	
	public Hospedaje(String nombre, int pisos, int habitacionesPiso) {
		this.nombre = nombre;
		crearHabitaciones(pisos, habitacionesPiso);
	}
	
	public Hospedaje(String nombre,String ubicacion,ArrayList<Habitacion> habitacion) {
		this.nombre = nombre;
		this.setUbicacion(ubicacion);
		this.habitaciones=habitacion;

	}
	
	public void crearHabitaciones(int pisos, int habitacionesPiso) {
		for(int piso = 1; piso <= pisos; piso++) {
			for(int habitacion = 1; habitacion <= habitacionesPiso; 
					habitacion++) {
				String strPiso = String.valueOf(piso);
				String strHabitacion = String.valueOf(habitacion);
				
				Habitacion copiaHabitacion = 
						new Habitacion(strPiso + strHabitacion);
				
				habitaciones.add(copiaHabitacion);
				
				copiaHabitacion.setHospedaje(this);
			}
		}
	}
	
	public int habitacionesDisponibles() {
		for (Habitacion habitacion : this.getHabitaciones()) {
			
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "    " + nombre + "     " + calificacion + "estrellas";
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
}
