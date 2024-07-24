package gestorAplicación.gestion;

import java.io.Serializable;
import java.util.ArrayList;

public class Terminal implements Serializable {
	private static ArrayList<Terminal> terminales = new ArrayList<Terminal>();
	private ArrayList<Empresa> empresas = new ArrayList<Empresa>();
	private String ubicacion;
	
	public Terminal(String ubicacion) {
		this.ubicacion = ubicacion;
		terminales.add(this);
	}
	
	public static Terminal buscarTerminal(String ubicacion) {
		for(Terminal terminal : terminales) {
			if(terminal.getUbicacion().equals(ubicacion)) {
				return terminal;
			}
		}
		return null;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public static ArrayList<Terminal> getTerminales() {
		return terminales;
	}

	public ArrayList<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(ArrayList<Empresa> empresas) {
		this.empresas = empresas;
	}
	
}
