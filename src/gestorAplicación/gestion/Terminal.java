package gestorAplicación.gestion;

import java.io.Serializable;
import java.util.ArrayList;

public class Terminal implements Serializable {
	private static final long serialVersionUID = 4L;
	private static ArrayList<Terminal> terminales = new ArrayList<Terminal>();
	private ArrayList<Empresa> empresas = new ArrayList<Empresa>();
	private ArrayList<Hospedaje> hospedajes = new ArrayList<Hospedaje>();

	private String nombre;
	private String ubicacion;

	public Terminal(String ubicacion) {
		ubicacion = ubicacion.toUpperCase();
		this.ubicacion = ubicacion;
		terminales.add(this);
	}

	public Terminal(String nombre, String ubicacion) {
		ubicacion = ubicacion.toUpperCase();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		terminales.add(this);
	}

	// Método para buscar una terminal por ubicación, usado en la funcionalidad 5
	public static Terminal buscarTerminal(String ubicacion) {
		ubicacion = ubicacion.toUpperCase();
		for (Terminal terminal : terminales) {
			if (terminal.getUbicacion().equals(ubicacion)) {
				return terminal;
			}
		}
		return null;
	}

	// Método para buscar una empresa por nombre, usado en la funcionalidad 5
	public Empresa buscarEmpresa(String nombre) {
		for (Empresa empresa : this.empresas) {
			if (empresa.getNombre().equals(nombre)) {
				return empresa;
			}
		}
		return null;
	}

	// Método para buscar una terminal por nombre y ubicación, usado en la funcionalidad 5
	public static Terminal buscarTerminal(String nombre, String ubicacion) {
		ubicacion = ubicacion.toUpperCase();
		for (Terminal terminal : terminales) {
			if (terminal.getNombre() != null) {
				if (terminal.getUbicacion().equals(ubicacion) && terminal.getNombre().equals(nombre)) {
					return terminal;
				}
			}
		}
		return null;
	}

	// Método para buscar un hospedaje por nombre y ubicación, usando en la funcionalidad 5
	public static Hospedaje buscarHospedaje(String nombre, String ubi) {
		ubi = ubi.toUpperCase();
		for (Terminal terminal : Terminal.getTerminales()) {
			for (Hospedaje hospedaje : terminal.getHospedajes()) {
				if (hospedaje.getNombre().equals(nombre) && hospedaje.getUbicacion().equals(ubi)) {
					return hospedaje;
				}
			}
		}

		return null;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		ubicacion = ubicacion.toUpperCase();
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
		this.nombre = nombre;
	}

	public static void setTerminales(ArrayList<Terminal> terminales) {
		Terminal.terminales = terminales;
	}

	// Método para agregar una empresa a una terminal, usado en la funcionalidad 5
	public void agregarEmpresa(Empresa empresa) {
		this.empresas.add(empresa);
		empresa.getTerminales().add(this);
	}

	// Método para eliminar una terminal por nombre y ubicación, usado en la funcionalidad 5
	public static void eliminarTerminal(String nombre, String ubicacion) {
		ubicacion = ubicacion.toUpperCase();

		Terminal terminal = Terminal.buscarTerminal(nombre, ubicacion);
		terminales.remove(terminal);

	}

	// Método para eliminar una empresa de una terminal, usado en la funcionalidad 5
	public void eliminarEmpresa(String nombreEmp) {

		Empresa empresa = this.buscarEmpresa(nombreEmp);
		empresa.getTerminales().remove(this);
		this.empresas.remove(empresa);

	}
}
