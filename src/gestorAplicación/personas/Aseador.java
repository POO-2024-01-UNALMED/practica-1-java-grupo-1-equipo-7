package gestorAplicación.personas;
import java.io.Serializable;

public class Aseador extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean disponibilidad;
	private static int cantidadAseadores;
	
	public Aseador(String nombre,boolean disponibilidad) {
		super(nombre,"","","");
		this.disponibilidad=disponibilidad;
		cantidadAseadores++;
	}
	
	public boolean isDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public static int getCantidadAseadores() {
		return cantidadAseadores;
	}
	

}
