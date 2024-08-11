package gestorAplicación.gestion;

import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicación.personas.Aseador;
import gestorAplicación.personas.Revisor;

public class Terminal implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ArrayList<Terminal> terminales = new ArrayList<Terminal>();
	private ArrayList<Empresa> empresas = new ArrayList<Empresa>();
	private ArrayList<Hospedaje> hospedajes = new ArrayList<Hospedaje>();
	private ArrayList<Aseador> aseadores= new ArrayList<Aseador>();
	private ArrayList<Revisor> revisores= new ArrayList<Revisor>();
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

	public ArrayList<Revisor> getRevisores() {
		return revisores;
	}

	public void setRevisores(ArrayList<Revisor> revisores) {
		this.revisores = revisores;
	}

	public ArrayList<Aseador> getAseadores() {
		return aseadores;
	}

	public void setAseadores(ArrayList<Aseador> aseadores) {
		this.aseadores = aseadores;
	}
	
	public void añadirRevisor(Revisor revisor) {
		Boolean revisorNuevo=true;
		for (Revisor r: revisores) {
			if (revisor.equals(r)) {
				revisorNuevo=false;
			}
		}
		if (revisorNuevo) {
			revisores.add(revisor);
		}
		
		else {
			System.out.println("El revisor ya ha sido registrado");
		}
	}
	
	public void añadirAseador(Aseador aseador) {
		Boolean aseadorNuevo=true;
		for (Aseador a: aseadores) {
			if (aseador.equals(a)) {
				aseadorNuevo=false;
			}
		}
		if (aseadorNuevo) {
			aseadores.add(aseador);
		}
		
		else {
			System.out.println("El aseador ya ha sido registrado");
		}
	}

	public ArrayList<Hospedaje> getHospedajes() {
		return hospedajes;
	}

	public void setHospedajes(ArrayList<Hospedaje> hospedajes) {
		this.hospedajes = hospedajes;
	}
	
}
