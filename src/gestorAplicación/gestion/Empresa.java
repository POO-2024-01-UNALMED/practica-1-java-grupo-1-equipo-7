package gestorAplicación.gestion;

import java.util.ArrayList;

public class Empresa {
	private String nombre;
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	private static ArrayList<Empresa> empresas = new ArrayList<Empresa>();
	
	public Empresa() {
		empresas.add(this);
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
