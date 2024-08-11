package gestorAplicación.personas;
import gestorAplicación.gestion.Viaje;
import java.util.ArrayList;
import java.io.Serializable;
import gestorAplicación.transporte.tipoVehiculo;

public class Conductor extends Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private boolean disponible;
	private tipoVehiculo tipovehiculo;
	
	public Conductor(String nombre) {
		super(nombre, "");
	}

	public boolean isDisponible() {
		return disponible;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
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
	
	public tipoVehiculo getTipoVehiculo() {
		return tipovehiculo;
	}
	
	public void setTipoVehiculo(tipoVehiculo tipo) {
		tipovehiculo=tipo;
	}
	
	public void setTipoVehiculo(String tipo) {
		tipovehiculo=tipoVehiculo.valueOf(null, tipo);
	}
}
