package gestorAplicación.personas;
import java.io.Serializable;

public class Revisor extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean disponibilidad;
	private String ubicacion;
	private static int cantidadRevisores;
	
	public Revisor(String nombre, boolean disponibilidad, String ubicacion) {
		super(nombre,"","","");
		this.disponibilidad=disponibilidad;
		this.ubicacion=ubicacion;
		cantidadRevisores++;	
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public static int getCantidadRevisores() {
		return cantidadRevisores;
	}


}
