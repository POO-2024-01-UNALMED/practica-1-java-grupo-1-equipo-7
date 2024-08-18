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
	private String nombre;
	private String ubicacion;
	
	public Terminal(String ubicacion) {
		this.ubicacion = ubicacion;
		terminales.add(this);
	}
	
	public Terminal(String nombre,String ubicacion) {
		this.nombre=nombre;
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
	
	public Empresa buscarEmpresa(String nombre) {
		for(Empresa empresa : this.empresas) {
			if(empresa.getNombre().equals(nombre)) {
				return empresa;
			}
		}
		return null;
	}
	
	public static Terminal buscarTerminal(String nombre,String ubicacion) {
		for(Terminal terminal : terminales) {
			if(terminal.getNombre()!=null) {
			if(terminal.getUbicacion().equals(ubicacion) && terminal.getNombre().equals(nombre)) {
				return terminal;
			}}
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;}
	public static void setTerminales(ArrayList<Terminal> terminales) {
		Terminal.terminales = terminales;
	}
	
	public void agregarEmpresa(Empresa empresa) {
		this.empresas.add(empresa);
	}
	
	public static void eliminarTerminal(String nombre,String ubicacion) {
		
		Terminal terminal= Terminal.buscarTerminal(nombre, ubicacion);
		terminales.remove(terminal);
				
				
		
	}
	
	public void eliminarEmpresa(String nombreEmp) {
		
		Empresa empresa= this.buscarEmpresa(nombreEmp);
		this.empresas.remove(empresa);
				
				
		
	}
}
