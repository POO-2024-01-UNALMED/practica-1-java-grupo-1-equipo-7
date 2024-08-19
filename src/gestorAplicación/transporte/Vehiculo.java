package gestorAplicación.transporte;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import gestorAplicación.personas.Conductor;

public class Vehiculo implements Serializable {
	private static ArrayList<String> placas=new ArrayList<String>();
	private String placa;
	private Conductor conductor;
	
	public Vehiculo() {
		
	}
	
	public Vehiculo(String placa) {
		this.placa=placa;
		
		
	}

	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Conductor getConductor() {
		return conductor;
	}

	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}

	public static ArrayList<String> getPlacas() {
		return placas;
	}

	public static void setPlacas(ArrayList<String> placas) {
		Vehiculo.placas = placas;
	}
	
	public static String generarPlaca() {
		Random aleatorio = new Random();
		ArrayList<String> letras= new ArrayList<String>();
		String string="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String[] parts = string.split("");
		for (String letra:parts) {
			letras.add(letra);
		}
		while (true) {
			String r1= letras.get(aleatorio.nextInt(26));
			String r2=letras.get(aleatorio.nextInt(26));
			String r3=letras.get(aleatorio.nextInt(26));
			int r4=aleatorio.nextInt(10);
			int r5=aleatorio.nextInt(10);
			int r6=aleatorio.nextInt(10);
			String placa=r1+r2+r3+"-"+r4+r5+r6;
			
			if (verificarPlaca(placa)) {
				return placa;
			}
		}
	
	}
	
	public static boolean verificarPlaca(String placa) {
		boolean ok=true;
		for (String placa1:placas) {
			if (placa1.equals(placa)) {
				ok=false;
			}
		}
		return ok;
	}
}
