package gestorAplicación.personas;
import gestorAplicación.gestion.Hospedaje;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Viaje;
import java.util.ArrayList;
import java.io.Serializable;


public class Conductor extends Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	private static ArrayList<Conductor> conductores = new ArrayList<Conductor>();
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private boolean disponible;

	
	public Conductor(String nombre) {
		super(nombre, "");
		conductores.add(this);
	}
	
	public Conductor(String nombre,String id) {
		super(nombre, id);
		conductores.add(this);
	}

	public boolean isDisponible() {
		return disponible;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public static Conductor buscarConductor(String id) {
		for (Conductor conductor: Conductor.conductores) {
			if(conductor.getId().equals(id)) {
				return conductor;
			}
		}
		return null;
	}
	
	public static Conductor buscarConductor(String nombre,String id) {
		for(Conductor conductor: conductores) {
			if(conductor.getNombre()!=null && conductor.getId()!=null) {
			if(conductor.getId().equals(id) && conductor.getNombre().equals(nombre)) {
				return conductor;
			}}
		}
		return null;}
	
	
	public void añadirViaje(Viaje viaje) {
		Boolean viajeNuevo=true;
		for (Viaje v : viajes) {
			if (viaje.equals(v)) {
				viajeNuevo=false;
			}
		}
		if (viajeNuevo) {
			viajes.add(viaje);
		}
		
		else {
			System.out.println("El viaje ya ha sido asignado al conductor");
		}
	}
	
	public ArrayList<Viaje> getViajes(){
		return viajes;

	}
	
	public static ArrayList<Conductor> getConductores(){
		return conductores;
	}
	
	public static void eliminarConductor(String id) {
		
		Conductor conductor= Conductor.buscarConductor(id);
		conductores.remove(conductor);
				
				
			
		
		
	}
}
	

