package gestorAplicación.gestion;

import java.io.Serializable;
import java.util.ArrayList;



public class Terminal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1318401077959501569L;
	private static ArrayList<Terminal> terminales = new ArrayList<Terminal>();
	private ArrayList<Empresa> empresas = new ArrayList<Empresa>();
	private ArrayList<Hospedaje> hospedajes = new ArrayList<Hospedaje>();

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
		empresa.getTerminales().add(this);
	}
	
	public static void eliminarTerminal(String nombre,String ubicacion) {
		
		Terminal terminal= Terminal.buscarTerminal(nombre, ubicacion);
		terminales.remove(terminal);
				
				
		
	}
	
	public void eliminarEmpresa(String nombreEmp) {
		
		Empresa empresa= this.buscarEmpresa(nombreEmp);
		empresa.getTerminales().remove(this);
		this.empresas.remove(empresa);
				
				
		
	}
}
