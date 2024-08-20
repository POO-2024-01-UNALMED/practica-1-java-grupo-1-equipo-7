package uiMain;

import java.time.LocalDate;
import java.time.LocalTime;

import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Hospedaje;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Tiquete;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.personas.Pasajero;
import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.Bus;

public class Auxiliar {
	public static void instanciarObjetos() {
		Terminal medellin = new Terminal("MEDELLIN");
		Terminal bogota = new Terminal("BOGOTA");
		Terminal cali = new Terminal("CALI");
		Terminal bucaramanga = new Terminal("BUCARAMANGA");
		Terminal pereira = new Terminal("PEREIRA");
		Terminal santaMarta = new Terminal("SANTA MARTA");
		Terminal cartagena = new Terminal("CARTAGENA");
		Terminal tolima = new Terminal("TOLIMA");
		
		Empresa swift = new Empresa("Swift");
		Empresa zoom = new Empresa("Zoom");
		Empresa jet = new Empresa("Jet");
		Empresa cruis = new Empresa("Cruis");
		
		Bus bus1 = new Bus(14, new int[]{3, 4});
		Bus bus2 = new Bus(13, new int[]{2, 6});
		Bus bus3 = new Bus(10, new int[]{2, 7});
		Bus bus4 = new Bus(15, new int[]{5, 9});
		Bus bus5 = new Bus(9, new int[]{3, 4});
		Bus bus6 = new Bus(14, new int[]{5, 10});
		Bus bus7 = new Bus(13, new int[]{3, 4});
		Bus bus8 = new Bus(11, new int[]{5, 8});
		
		Viaje viaje1 = new Viaje(medellin, bogota);
		Viaje viaje2 = new Viaje(cali, bogota);
		Viaje viaje3 = new Viaje(medellin, cali);
		Viaje viaje4 = new Viaje(pereira, santaMarta);
		Viaje viaje5 = new Viaje(cartagena, tolima);
		Viaje viaje6 = new Viaje(bucaramanga, medellin);
		Viaje viaje7 = new Viaje(pereira, cali);
		Viaje viaje8 = new Viaje(bogota, santaMarta);
		
		Hospedaje pod = new Hospedaje("Pod", 3, 4);
		Hospedaje nest = new Hospedaje("Nest", 4, 5);
		Hospedaje oasis = new Hospedaje("Oasis", 3, 6);
		Hospedaje haven = new Hospedaje("Haven", 2, 5);
		Hospedaje zen = new Hospedaje("Zen", 4, 4);
		Hospedaje den = new Hospedaje("Den", 2, 7);
		Hospedaje pad = new Hospedaje("Pad", 4, 6);
		Hospedaje hub = new Hospedaje("Hub", 4, 3);
		
		Pasajero pasajero1 = new Pasajero("Samuel", "123456");
		Pasajero pasajero2 = new Pasajero("Santiago", "654321");
		Pasajero pasajero3 = new Pasajero("Juan", "123321");
		Pasajero pasajero4 = new Pasajero("Camilo", "100123");
		
		viaje1.setEmpresa(zoom);
		viaje2.setEmpresa(zoom);
		viaje3.setEmpresa(swift);
		viaje4.setEmpresa(swift);
		viaje5.setEmpresa(cruis);
		viaje6.setEmpresa(cruis);
		viaje7.setEmpresa(jet);
		viaje8.setEmpresa(jet);
		
		viaje1.setBus(bus1);
		viaje2.setBus(bus2);
		viaje3.setBus(bus3);
		viaje4.setBus(bus4);
		viaje5.setBus(bus5);
		viaje6.setBus(bus6);
		viaje7.setBus(bus7);
		viaje8.setBus(bus8);
		
		viaje1.setFecha(LocalDate.parse("2025-08-21"));
		viaje2.setFecha(LocalDate.parse("2026-05-23"));
		viaje3.setFecha(LocalDate.parse("2025-09-15"));
		viaje4.setFecha(LocalDate.parse("2025-12-21"));
		viaje5.setFecha(LocalDate.parse("2024-10-22"));
		viaje6.setFecha(LocalDate.parse("2024-12-26"));
		viaje7.setFecha(LocalDate.parse("2026-07-25"));
		viaje8.setFecha(LocalDate.parse("2024-07-29"));
		
		viaje1.setHora(LocalTime.of(15, 00));
		viaje2.setHora(LocalTime.of(16, 00));
		viaje3.setHora(LocalTime.of(13, 00));
		viaje4.setHora(LocalTime.of(12, 00));
		viaje5.setHora(LocalTime.of(9, 00));
		viaje6.setHora(LocalTime.of(8, 30));
		viaje7.setHora(LocalTime.of(20, 10));
		viaje8.setHora(LocalTime.of(17, 00));
		
		zoom.getViajes().add(viaje1);
		zoom.getViajes().add(viaje2);
		swift.getViajes().add(viaje3);
		swift.getViajes().add(viaje4);
		cruis.getViajes().add(viaje5);
		cruis.getViajes().add(viaje6);
		jet.getViajes().add(viaje7);
		jet.getViajes().add(viaje8);
	
		medellin.getHospedajes().add(pod);
		bogota.getHospedajes().add(nest);
		cali.getHospedajes().add(den);
		bucaramanga.getHospedajes().add(zen);
		pereira.getHospedajes().add(haven);
		santaMarta.getHospedajes().add(hub);
		cartagena.getHospedajes().add(oasis);
		tolima.getHospedajes().add(pad);

		pasajero1.agregarTiquete(new Tiquete(pasajero1, viaje1, viaje1.buscarAsiento("12A")));
		pasajero2.agregarTiquete(new Tiquete(pasajero2, viaje2, viaje2.buscarAsiento("11D")));
		pasajero3.agregarTiquete(new Tiquete(pasajero3, viaje3, viaje3.buscarAsiento("5A")));
		pasajero4.agregarTiquete(new Tiquete(pasajero4, viaje4, viaje4.buscarAsiento("10B")));
		pasajero1.agregarTiquete(new Tiquete(pasajero1, viaje5, viaje5.buscarAsiento("6B")));
		pasajero2.agregarTiquete(new Tiquete(pasajero2, viaje6, viaje6.buscarAsiento("9A")));
		pasajero3.agregarTiquete(new Tiquete(pasajero3, viaje7, viaje7.buscarAsiento("11D")));
		pasajero4.agregarTiquete(new Tiquete(pasajero4, viaje8, viaje8.buscarAsiento("10C")));
		
		viaje1.reservarAsiento(viaje1.buscarAsiento("12A").getNumero(), null);
		viaje2.reservarAsiento(viaje2.buscarAsiento("11D").getNumero(), null);
		viaje3.reservarAsiento(viaje3.buscarAsiento("5A").getNumero(), null);
		viaje4.reservarAsiento(viaje4.buscarAsiento("10B").getNumero(), null);
		viaje5.reservarAsiento(viaje5.buscarAsiento("6B").getNumero(), null);
		viaje6.reservarAsiento(viaje6.buscarAsiento("9A").getNumero(), null);
		viaje7.reservarAsiento(viaje7.buscarAsiento("11D").getNumero(), null);
		viaje8.reservarAsiento(viaje8.buscarAsiento("10C").getNumero(), null);
		
		
		
	}
}
