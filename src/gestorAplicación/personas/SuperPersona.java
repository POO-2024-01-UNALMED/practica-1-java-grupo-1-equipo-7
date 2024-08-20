package gestorAplicación.personas;

import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicación.gestion.Tiquete;

public interface SuperPersona extends Serializable {
	public void cancelarTiquete(Tiquete tiquete);
	
	public Tiquete buscarTiquete(String numeroReserva);
	
	public ArrayList<Tiquete> buscarTiquetes(String tipoTiquetes);
	
	public void agregarTiquete(Tiquete tiquete);
}
