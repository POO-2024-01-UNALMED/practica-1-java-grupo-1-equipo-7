package uiMain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import baseDatos.Serializador;
import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Tiquete;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.personas.Pasajero;
import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.Bus;

public class Interfaz {
	static Scanner sc = new Scanner(System.in);

	public static void verViajes() {
		for(Viaje viaje : Empresa.listaViajes()) {
			System.out.println("Vuelos disponibles de la empresa " + 
			viaje.getEmpresa().getNombre());
			
			for (int i = 0; i < 72; i++) {
				System.out.print("-");
			}
			
			System.out.println();

			System.out.print("    FECHA     ORIGEN     DESTINO     " + 
			"HORA DE SALIDA     ID     PLACA    ");
			
			System.out.println();

			for (int i = 0; i < 72; i++) {
				System.out.print("-");
			}
			
			System.out.println();
			
			System.out.print(viaje.toString());
			
			System.out.println();
			System.out.println();
		}
		
		System.out.println("¿Desea filtrar por alguna categoría (si/no)");
		
		String respuesta1 = sc.nextLine();
		
		System.out.println();
		
		if(respuesta1.toLowerCase().equals("si")) {
			System.out.println("¿Por cuál categoría desea filtrar?");
			
			String categoria = sc.nextLine();
			
			System.out.println();
			
			ArrayList<Viaje> viajes;
			
			switch(categoria.toUpperCase()) {
			
			case "FECHA":
				System.out.print("Ingrese la fecha en formato dd-mm-aaaa: ");
				
				String fecha = sc.nextLine();
				
				System.out.println();
				
				DateTimeFormatter dateFormatter = 
				DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
				
		        LocalDate newFecha = LocalDate.parse(fecha, dateFormatter); 
		        
		        viajes = Empresa.buscarViajes(newFecha);
				
				System.out.println("Vuelos filtrados por " + 
						categoria.toUpperCase());
				
				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " + 
				"HORA DE SALIDA     ID     PLACA    ");
				
				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();
				
				for(Viaje viaje : viajes) {
					System.out.println(viaje.toString());
				}
				
				System.out.println();
				System.out.println();
				
				break;
			
			case "ORIGEN":
				System.out.print("Ingrese el origen: ");
				
				String origen = sc.nextLine();
				
				System.out.println();
				
				viajes = Empresa.buscarViajes(origen, null);
				
				System.out.println("Vuelos filtrados por " + 
						categoria.toUpperCase());
				
				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " + 
				"HORA DE SALIDA     ID     PLACA    ");
				
				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();
				
				for(Viaje viaje : viajes) {
					System.out.println(viaje.toString());
				}
				
				System.out.println();
				System.out.println();
				
				break;
				
			case "DESTINO":
				System.out.print("Ingrese el destino: ");
				
				String destino = sc.nextLine();
				
				System.out.println();
				
				viajes = Empresa.buscarViajes(null, destino);
		  
				System.out.println("Vuelos filtrados por " + 
						categoria.toUpperCase());
				
				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " + 
				"HORA DE SALIDA     ID     PLACA    ");
				
				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();
				
				for(Viaje viaje : viajes) {
					System.out.println(viaje.toString());
				}
				
				System.out.println();
				System.out.println();
				
				break;
				
			case "HORA DE SALIDA":
				System.out.print("Ingrese la hora de salida " + 
				"en formato 24 horas: ");
				
				String hora = sc.nextLine();
				
				System.out.println();
				
				DateTimeFormatter timeFormatter = 
				DateTimeFormatter.ofPattern("HH:mm"); 
						
				LocalTime newHora = LocalTime.parse(hora, timeFormatter); 
				
				viajes = Empresa.buscarViajes(newHora);
				
				System.out.println("Vuelos filtrados por " + 
						categoria.toUpperCase());
				
				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " + 
				"HORA DE SALIDA     ID     PLACA    ");
				
				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();
				
				for(Viaje viaje : viajes) {
					System.out.println(viaje.toString());
				}
				
				System.out.println();
				System.out.println();
				
				break;
			case "ID":
				System.out.print("Ingrese el id del viaje: ");
				
				String id = sc.nextLine();
				
				System.out.println();
				
				viajes = Empresa.buscarViajes(id);
				
				System.out.println("Vuelos filtrados por " + 
						categoria.toUpperCase());
				
				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " + 
				"HORA DE SALIDA     ID     PLACA    ");
				
				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();
				
				for(Viaje viaje : viajes) {
					System.out.println(viaje.toString());
				}
				
				System.out.println();
				System.out.println();
				
				break;
			case "PLACA":
				System.out.print("Ingrese la placa del vehiculo: ");
				
				String placa = sc.nextLine();
				
				System.out.println();
				
				viajes = Empresa.buscarViajes(placa);
				
				System.out.println("Vuelos filtrados por " + 
						categoria.toUpperCase());
				
				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " + 
				"HORA DE SALIDA     ID     PLACA    ");
				
				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}
				
				System.out.println();
				
				for(Viaje viaje : viajes) {
					System.out.println(viaje.toString());
				}
				
				System.out.println();
				System.out.println();
				
				break;
			}
			
			System.out.println("¿Desea ver más detalles sobre un viaje? " + 
			"(si/no)");
			
			String respuesta2 = sc.nextLine();
			
			if(respuesta2.toLowerCase().equals("si")) {
				System.out.print("Ingrese el id del viaje: ");
				
				String id = sc.nextLine();
				
				Viaje viaje = Empresa.buscarViaje(id);
				
				for(Asiento asiento : viaje.listaAsientos()) {
					if(asiento.getNumeroAsiento().contains("B")) {
						System.out.print(asiento.getNumeroAsiento() + " ");
					} else if(asiento.getNumeroAsiento().contains("D")){
						System.out.print(asiento.getNumeroAsiento());
						System.out.println();
					} else {
						if(asiento.getNumeroAsiento().length() == 2) {
							System.out.print(asiento.getNumeroAsiento() + "  ");
						} else {
							System.out.print(asiento.getNumeroAsiento() + " ");
						}
						
					}
				}
			} 
			
		} else {
			System.out.println("¿Desea ver más detalles sobre un viaje? " + 
					"(si/no)");
					
			String respuesta2 = sc.nextLine();
			
			if(respuesta2.toLowerCase().equals("si")) {
				System.out.print("Ingrese el id del viaje: ");
				
				String id = sc.nextLine();
				
				Viaje viaje = Empresa.buscarViaje(id);
				
				for(Asiento asiento : viaje.listaAsientos()) {
					if(asiento.getNumeroAsiento().contains("B")) {
						System.out.print(asiento.getNumeroAsiento() + " ");
					} else if(asiento.getNumeroAsiento().contains("D")){
						System.out.print(asiento.getNumeroAsiento());
						System.out.println();
					} else {
						if(asiento.getNumeroAsiento().length() == 2) {
							System.out.print(asiento.getNumeroAsiento() + "  ");
						} else {
							System.out.print(asiento.getNumeroAsiento() + " ");
						}
						
					}
				}
			} 
		}
	}

	public static void reservarTiquete() {
		System.out.print("Ingrese el nombre completo del origen: ");
		String origen = sc.nextLine();

		System.out.print("Ingrese el nombre completo del destino: ");
		String destino = sc.nextLine();

		System.out.println("Estos son los viajes disponibles para la ruta " + origen + " --> " + destino + ":");

		ArrayList<Viaje> viajesDisponibles = Viaje.buscarViajes(origen, destino);

		for (Viaje viaje : viajesDisponibles) {
			System.out.println((viajesDisponibles.indexOf(viaje) + 1) + ". " + "Número: " + viaje.getId());
		}

		System.out.print("Ingrese el número del viaje: ");
		int opcion = sc.nextInt();
		sc.nextLine();

		Viaje viajeSeleccionado = viajesDisponibles.get(opcion - 1);
		Bus bus = viajeSeleccionado.getBus();
		ArrayList<Asiento> asientos = bus.getAsientos();

		System.out.println("Estos son los asientos disponibles para el viaje seleccionado:");

		String letras = "ABCD";

		for (int i = 0; i < 4; i++) {
			char caracter = letras.charAt(i);
			for (Asiento asiento : asientos) {
				if (asiento.getNumeroAsiento().contains(Character.toString(caracter))) {
					if (asiento.isReservado() == true) {
						System.out.print("   ");
					} else {
						System.out.print(asiento.getNumeroAsiento() + " ");
					}

				}
			}
			if (caracter == 'B') {
				System.out.println();
			}
			System.out.println();
		}

		System.out.print("Ingrese el número del asiento que desea: ");
		String numeroAsiento = sc.nextLine();

		Asiento asientoSeleccionado = null;

		for (Asiento asiento : asientos) {
			if (asiento.getNumeroAsiento().equals(numeroAsiento)) {
				asientoSeleccionado = asiento;
				asiento.setReservado(true);
			}
		}

		System.out.println("Ingrese sus datos:");

		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Número de identificación: ");
		String numeroIdentificacion = sc.nextLine();
		System.out.print("Correo: ");
		String correo = sc.nextLine();
		System.out.print("Teléfono: ");
		String telefono = sc.nextLine();

		Pasajero pasajero = new Pasajero(nombre, numeroIdentificacion, correo, telefono);

		Tiquete tiquete = new Tiquete(pasajero, viajeSeleccionado, asientoSeleccionado);

		System.out.println("Confirmación de reserva del tiquete:");
		System.out.println("Origen: " + origen);
		System.out.println("Destino: " + destino);
		System.out.println("Nombre del pasajero: " + nombre);

		sc.nextLine();
	}

	public static void gestionarTiquetes() {
		System.out.print("Ingrese el número de identificación del pasajero: ");
		String numeroIdentificacion = sc.nextLine();

		Pasajero pasajero = Pasajero.buscarPasajero(numeroIdentificacion);

		if (pasajero == null) {
			System.out.println("No hay tiquetes asociados con el número de identificación");
		} else {
			ArrayList<Tiquete> tiquetesValidos = Tiquete.buscarTiquetesValidos(pasajero.getTiquetes());
			ArrayList<Tiquete> tiquetesVencidos = Tiquete.buscarTiquetesVencidos(pasajero.getTiquetes());

			System.out.println("Tiquetes validos:");

			for (Tiquete tiquete : tiquetesValidos) {
				System.out.println(tiquete.toString());
			}

			System.out.println("Tiquetes vencidos:");

			for (Tiquete tiquete : tiquetesVencidos) {
				System.out.println(tiquete.toString());
			}

			System.out.print("Escoja el tiquete ingresando el número de referencia:");
			String numeroReferencia = sc.nextLine();

			Tiquete tiqueteEscogido = Tiquete.buscarTiquete(numeroReferencia);

			for (Tiquete tiquete : tiquetesVencidos) {
				if (tiqueteEscogido == tiquete) {
					System.out.println(tiqueteEscogido);
				}
			}

			for (Tiquete tiquete : tiquetesValidos) {
				if (tiqueteEscogido == tiquete) {
					System.out.println("1. Cancelarlo");
					System.out.println("2. Modificarlo");
				}
			}

			System.out.print("Elija una opción: ");
			int opcion = sc.nextInt();
			sc.nextLine();

			if (opcion == 1) {

			} else {

			}

		}

	}

	public static void salirDelSistema() {
		System.out.println("Ten un buen viaje");
		Serializador.limpiarArchivos();
		System.exit(0);
	}

	public static void main(String[] args) {
		int opcion;
		
		Auxiliar.crearInstancias();

		do {
			System.out.println("¿Qué operación desea realizar?");
			
			System.out.println();
			
			System.out.println("1. Ver viajes disponibles");
			System.out.println("2. Reservar tiquete");
			System.out.println("3. Gestionar tiquetes");
			System.out.println("4. Agregar servicio de hospedaje");
			System.out.println("5. Opciones de administrador");
			System.out.println("6. Salir");
			
			System.out.println();
			
			System.out.print("Ingrese el número de la operación: ");

			opcion = sc.nextInt();
			sc.nextLine();
			
			System.out.println();

			switch (opcion) {
			case 1:
				verViajes();
				break;
			case 2:
				reservarTiquete();
				break;
			case 3:
				gestionarTiquetes();
				break;
			case 6:
				salirDelSistema();
				break;
			}
		} while (opcion != 6);

	}

}
