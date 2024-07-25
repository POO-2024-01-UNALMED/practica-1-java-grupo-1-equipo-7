package uiMain;

import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.transporte.Bus;

public class Auxiliar {
	
	public static void crearInstancias() {
		Terminal medellin = new Terminal("MEDELLIN");
		Terminal bogota = new Terminal("BOGOTA");
		Terminal cali = new Terminal("CALI");
		Terminal bucaramanga = new Terminal("BUCARAMANGA");
		Terminal pereira = new Terminal("PEREIRA");
		Terminal santaMarta = new Terminal("SANTA MARTA");
		
		Empresa empresa = new Empresa("Coord");
		Viaje viaje = new Viaje(medellin, bogota, "0001");
		Bus bus = new Bus("1234", 12);
		
		empresa.getViajes().add(viaje);
		
		viaje.setEmpresa(empresa);
		viaje.setBus(bus);
		
		bus.getAsientos().get(45).setReservado(true);
		bus.getAsientos().get(44).setReservado(true);
	}

}
