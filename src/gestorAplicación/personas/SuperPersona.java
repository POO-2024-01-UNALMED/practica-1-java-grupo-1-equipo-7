package gestorAplicación.personas;

import java.util.ArrayList;

import gestorAplicación.gestion.Tiquete;

public interface SuperPersona {
	public void cancelarTiquete(Tiquete tiquete);
	
	public Tiquete buscarTiquete(String numeroReserva);
	
	public ArrayList<Tiquete> buscarTiquetes(String tipoTiquetes);
	
	public void agregarTiquete(Tiquete tiquete);
}
