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
		
		Bus bus = new Bus("1234", 12);

		Viaje viaje1 = new Viaje(medellin, bogota, "0001");
		Empresa empresa = new Empresa("Coord");
		viaje1.setBus(bus);
		viaje1.setEmpresa(empresa);
		empresa.setNombre("Coor");
		empresa.getViajes().add(viaje1);

		bus.getAsientos().get(7).setReservado(true);
		bus.getAsientos().get(8).setReservado(true);
	}

}
