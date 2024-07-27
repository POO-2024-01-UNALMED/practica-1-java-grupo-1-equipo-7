package uiMain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import baseDatos.Serializador;
import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Tiquete;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.personas.Pasajero;
import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.Bus;

public class Interfaz{
	static Scanner sc = new Scanner(System.in);

	public static void verViajes() {
		for (Viaje viaje : Empresa.listaViajes()) {
			System.out.println("Vuelos disponibles de la empresa " 
			+ viaje.getEmpresa().getNombre());

			for (int i = 0; i < 72; i++) {
				System.out.print("-");
			}

			System.out.println();

			System.out.print("    FECHA     ORIGEN     DESTINO     " 
			+ "HORA DE SALIDA     ID     PLACA    ");

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

		if (respuesta1.toLowerCase().equals("si")) {
			System.out.println("¿Por cuál categoría desea filtrar?");

			String categoria = sc.nextLine();

			System.out.println();

			ArrayList<Viaje> viajes;

			switch (categoria.toUpperCase()) {
			case "FECHA":
				System.out.print("Ingrese la fecha en formato dd-mm-aaaa: ");

				String fecha = sc.nextLine();

				System.out.println();

				DateTimeFormatter formateoFecha = 
						DateTimeFormatter.ofPattern("dd-MM-yyyy");

				LocalDate nuevaFecha = LocalDate.parse(fecha, formateoFecha);

				viajes = Empresa.buscarViajes(nuevaFecha);

				System.out.println("Vuelos filtrados por " 
				+ categoria.toUpperCase());

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " 
				+ "HORA DE SALIDA     ID     PLACA    ");

				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				for (Viaje viaje : viajes) {
					System.out.println(viaje.toString());
				}

				System.out.println();
				
				System.out.println();
			
				break;

			case "ORIGEN":
				System.out.print("Ingrese el origen: ");

				String origen = sc.nextLine();

				System.out.println();

				viajes = Empresa.buscarViajes(origen, "");

				System.out.println("Vuelos filtrados por " 
				+ categoria.toUpperCase());

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " 
				+ "HORA DE SALIDA     ID     PLACA    ");

				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				for (Viaje viaje : viajes) {
					System.out.println(viaje.toString());
				}

				System.out.println();
				
				System.out.println();

				break;

			case "DESTINO":
				System.out.print("Ingrese el destino: ");

				String destino = sc.nextLine();

				System.out.println();

				viajes = Empresa.buscarViajes("", destino);

				System.out.println("Vuelos filtrados por " 
				+ categoria.toUpperCase());

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " 
				+ "HORA DE SALIDA     ID     PLACA    ");

				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				for (Viaje viaje : viajes) {
					System.out.println(viaje.toString());
				}

				System.out.println();
				
				System.out.println();

				break;

			case "HORA DE SALIDA":
				System.out.print("Ingrese la hora de salida " 
				+ "en formato 24 horas: ");

				String hora = sc.nextLine();

				System.out.println();

				DateTimeFormatter formateoHora = 
						DateTimeFormatter.ofPattern("HH:mm");

				LocalTime nuevaHora = 
						LocalTime.parse(hora, formateoHora);

				viajes = Empresa.buscarViajes(nuevaHora);

				System.out.println("Vuelos filtrados por " 
				+ categoria.toUpperCase());

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " 
				+ "HORA DE SALIDA     ID     PLACA    ");

				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				for (Viaje viaje : viajes) {
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

				System.out.println("Vuelos filtrados por " 
				+ categoria.toUpperCase());

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " 
				+ "HORA DE SALIDA     ID     PLACA    ");

				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				for (Viaje viaje : viajes) {
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

				System.out.println("Vuelos filtrados por " 
				+ categoria.toUpperCase());

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA     ORIGEN     DESTINO     " 
				+ "HORA DE SALIDA     ID     PLACA    ");

				System.out.println();

				for (int i = 0; i < 72; i++) {
					System.out.print("-");
				}

				System.out.println();

				for (Viaje viaje : viajes) {
					System.out.println(viaje.toString());
				}

				System.out.println();
				
				System.out.println();

				break;
			}

			System.out.println("¿Desea ver más detalles sobre un viaje? " 
			+ "(si/no)");

			String respuesta2 = sc.nextLine();

			System.out.println();

			if (respuesta2.toLowerCase().equals("si")) {
				System.out.print("Ingrese el id del viaje: ");

				String id = sc.nextLine();

				System.out.println();

				System.out.println("Asientos disponibles:");

				System.out.println();

				Viaje viaje = Empresa.buscarViaje(id);

				for (Asiento asiento : viaje.listaAsientos()) {
					if (asiento.getNumeroAsiento().length() == 2) {
						if (asiento.isReservado()) {
							if (asiento.getNumeroAsiento().contains("D")) {
								System.out.print("  ");
								
								System.out.println();
							} else {
								System.out.print("    ");
							}
						} else {
							if (asiento.getNumeroAsiento().contains("D")) {
								System.out.print(asiento.getNumeroAsiento());
								
								System.out.println();
							} else {
								System.out.print(asiento.getNumeroAsiento() 
								+ "  ");
							}
						}

					} else {
						if (asiento.isReservado()) {
							if (asiento.getNumeroAsiento().contains("D")) {
								System.out.print("   ");
								
								System.out.println();
							} else {
								System.out.print("    ");
							}
						} else {
							if (asiento.getNumeroAsiento().contains("D")) {
								System.out.print(asiento.getNumeroAsiento());
								
								System.out.println();
							} else {
								System.out.print(asiento.getNumeroAsiento() 
								+ " ");
							}
						}
					}
				}

				System.out.println();

				System.out.println("¿Desea reservar un asiento por un " 
				+ "cierto período de tiempo? (si/no)");

				String respuesta3 = sc.nextLine();

				System.out.println();

				if (respuesta3.toLowerCase().equals("si")) {
					System.out.print("Ingrese el número del asiento: ");

					String asiento = sc.nextLine();

					System.out.println();
					
					viaje.reservarAsiento(asiento);

					System.out.println("¿Por cuánto tiempo desea reservarlo?" 
					+ " (minutos/horas/dias)");

					String tiempo = sc.nextLine();

					System.out.println();
					
					String[] arrayTiempo = tiempo.split("[\s]");
					
					String cantidad = arrayTiempo[0];
					
					LocalDateTime fechaReserva;

					if (tiempo.toLowerCase().contains("minutos") 
							|| tiempo.toLowerCase().contains("minuto")) {
						fechaReserva = LocalDateTime.now().
								plusMinutes(Integer.valueOf(cantidad));
					} else if (tiempo.toLowerCase().contains("horas") 
									|| tiempo.toLowerCase().contains("hora")) {
						fechaReserva = LocalDateTime.now().
								plusHours(Integer.valueOf(cantidad));
					} else {
						fechaReserva = LocalDateTime.now().
								plusDays(Integer.valueOf(cantidad));
					}

					LocalDateTime fechaViaje = 
							LocalDateTime.
							of(viaje.getFecha(), viaje.getHora());

					while (fechaViaje.isBefore(fechaReserva)) {
						System.out.println("La reserva debe ser " 
						+ "antes del viaje ");

						System.out.println();
						
						System.out.println("¿Por cuánto tiempo desea " 
						+ "reservarlo? (minutos/horas/dias)");

						tiempo = sc.nextLine();
						
						arrayTiempo = tiempo.split("[\s]");
						
						cantidad = arrayTiempo[0];

						System.out.println();

						if (tiempo.toLowerCase().contains("minutos") 
								|| tiempo.toLowerCase().contains("minuto")) {
							fechaReserva = LocalDateTime.now().
									plusMinutes(Integer.valueOf(cantidad));
						} else if (tiempo.toLowerCase().contains("horas") 
										|| tiempo.toLowerCase().
												contains("hora")) {
							fechaReserva = LocalDateTime.now().
									plusHours(Integer.valueOf(cantidad));
						} else {
							fechaReserva = LocalDateTime.now().
									plusDays(Integer.valueOf(cantidad));
						}
					}
					
					ScheduledExecutorService service = 
							Executors.newScheduledThreadPool(1);
					
					Runnable task = () -> {
						viaje.descongelarAsiento(asiento);
					};
					
					service.schedule(task, Integer.valueOf(cantidad), 
									TimeUnit.MINUTES);
				}
			}
		} else {
			System.out.println("¿Desea ver más detalles sobre un viaje? " 
			+ "(si/no)");

			String respuesta2 = sc.nextLine();

			System.out.println();

			if (respuesta2.toLowerCase().equals("si")) {
				System.out.print("Ingrese el id del viaje: ");

				String id = sc.nextLine();

				System.out.println();

				System.out.println("Asientos disponibles:");

				System.out.println();

				Viaje viaje = Empresa.buscarViaje(id);

				for (Asiento asiento : viaje.listaAsientos()) {
					if (asiento.getNumeroAsiento().length() == 2) {
						if (asiento.isReservado()) {
							if (asiento.getNumeroAsiento().contains("D")) {
								System.out.print("  ");
								
								System.out.println();
							} else {
								System.out.print("    ");
							}
						} else {
							if (asiento.getNumeroAsiento().contains("D")) {
								System.out.print(asiento.getNumeroAsiento());
								
								System.out.println();
							} else {
								System.out.print(asiento.getNumeroAsiento() 
								+ "  ");
							}
						}

					} else {
						if (asiento.isReservado()) {
							if (asiento.getNumeroAsiento().contains("D")) {
								System.out.print("   ");
								
								System.out.println();
							} else {
								System.out.print("    ");
							}
						} else {
							if (asiento.getNumeroAsiento().contains("D")) {
								System.out.print(asiento.getNumeroAsiento());
								
								System.out.println();
							} else {
								System.out.print(asiento.getNumeroAsiento() 
								+ " ");
							}
						}
					}
				}

				System.out.println();

				System.out.println("¿Desea reservar un asiento por un " 
				+ "cierto período de tiempo? (si/no)");

				String respuesta3 = sc.nextLine();

				System.out.println();

				if (respuesta3.toLowerCase().equals("si")) {
					System.out.print("Ingrese el número del asiento: ");

					String asiento = sc.nextLine();

					System.out.println();
					
					viaje.reservarAsiento(asiento);

					System.out.println("¿Por cuánto tiempo desea reservarlo?" 
					+ " (minutos/horas/dias)");

					String tiempo = sc.nextLine();

					System.out.println();
					
					String[] arrayTiempo = tiempo.split("[\s]");
					
					String cantidad = arrayTiempo[0];
					
					LocalDateTime fechaReserva;

					if (tiempo.toLowerCase().contains("minutos") 
							|| tiempo.toLowerCase().contains("minuto")) {
						fechaReserva = LocalDateTime.now().
								plusMinutes(Integer.valueOf(cantidad));
					} else if (tiempo.toLowerCase().contains("horas") 
									|| tiempo.toLowerCase().contains("hora")) {
						fechaReserva = LocalDateTime.now().
								plusHours(Integer.valueOf(cantidad));
					} else {
						fechaReserva = LocalDateTime.now().
								plusDays(Integer.valueOf(cantidad));
					}

					LocalDateTime fechaViaje = 
							LocalDateTime.
							of(viaje.getFecha(), viaje.getHora());

					while (fechaViaje.isBefore(fechaReserva)) {
						System.out.println("La reserva debe ser " 
						+ "antes del viaje ");

						System.out.println();
						
						System.out.println("¿Por cuánto tiempo desea " 
						+ "reservarlo? (minutos/horas/dias)");

						tiempo = sc.nextLine();
						
						arrayTiempo = tiempo.split("[\s]");
						
						cantidad = arrayTiempo[0];

						System.out.println();

						if (tiempo.toLowerCase().contains("minutos") 
								|| tiempo.toLowerCase().contains("minuto")) {
							fechaReserva = LocalDateTime.now().
									plusMinutes(Integer.valueOf(cantidad));
						} else if (tiempo.toLowerCase().contains("horas") 
										|| tiempo.toLowerCase().
												contains("hora")) {
							fechaReserva = LocalDateTime.now().
									plusHours(Integer.valueOf(cantidad));
						} else {
							fechaReserva = LocalDateTime.now().
									plusDays(Integer.valueOf(cantidad));
						}
					}
					
					ScheduledExecutorService service = 
							Executors.newScheduledThreadPool(1);
					
					Runnable task = () -> {
						viaje.descongelarAsiento(asiento);
					};
					
					service.schedule(task, Integer.valueOf(cantidad), 
									TimeUnit.MINUTES);
				}
			}
		}
	}

	public static void reservarTiquete() {
		System.out.print("Ingrese el origen: ");
		
		String origen = sc.nextLine();
		
		System.out.println();

		System.out.print("Ingrese el destino: ");
		
		String destino = sc.nextLine();
		
		System.out.println();

		System.out.println("Estos son los viajes disponibles para la ruta " 
		+ origen.toUpperCase() + " --> " + destino.toUpperCase() + ":");
		
		for (Viaje viaje : Empresa.buscarViajes(origen.toUpperCase(), 
				destino.toUpperCase())) {
		
			for (int i = 0; i < 72; i++) {
				System.out.print("-");
			}

			System.out.println();

			System.out.print("    FECHA     ORIGEN     DESTINO     " 
			+ "HORA DE SALIDA     ID     PLACA    ");

			System.out.println();

			for (int i = 0; i < 72; i++) {
				System.out.print("-");
			}

			System.out.println();

			System.out.print(viaje.toString());

			System.out.println();
			System.out.println();
		}

		System.out.print("Ingrese el id del viaje: ");
		
		String id = sc.nextLine();
		
		System.out.println();

		Viaje viaje = Empresa.buscarViaje(id);

		System.out.println("Asientos disponibles:");
		
		System.out.println();
		
		for (Asiento asiento : viaje.listaAsientos()) {
			if (asiento.getNumeroAsiento().length() == 2) {
				if (asiento.isReservado()) {
					if (asiento.getNumeroAsiento().contains("D")) {
						System.out.print("  ");
						
						System.out.println();
					} else {
						System.out.print("    ");
					}
				} else {
					if (asiento.getNumeroAsiento().contains("D")) {
						System.out.print(asiento.getNumeroAsiento());
						
						System.out.println();
					} else {
						System.out.print(asiento.getNumeroAsiento() 
						+ "  ");
					}
				}

			} else {
				if (asiento.isReservado()) {
					if (asiento.getNumeroAsiento().contains("D")) {
						System.out.print("   ");
						
						System.out.println();
					} else {
						System.out.print("    ");
					}
				} else {
					if (asiento.getNumeroAsiento().contains("D")) {
						System.out.print(asiento.getNumeroAsiento());
						
						System.out.println();
					} else {
						System.out.print(asiento.getNumeroAsiento() 
						+ " ");
					}
				}
			}
		}
		
		System.out.println();
		
		System.out.print("Ingrese el número del asiento: ");
		
		String asiento = sc.nextLine();
		
		System.out.println();
		
		viaje.reservarAsiento(asiento);

		System.out.println("Ingrese sus datos:");
		
		System.out.println();

		System.out.print("Nombre: ");
		
		String nombre = sc.nextLine();
		
		System.out.print("Número de identificación: ");
		
		String idPasajero = sc.nextLine();

		System.out.print("Correo: ");
		
		String correo = sc.nextLine();
		
		System.out.print("Teléfono: ");
		
		String telefono = sc.nextLine();
		
		System.out.println();
		
		new Tiquete(new Pasajero(nombre, idPasajero, correo, telefono), 
				viaje);

		System.out.println("Confirmación de reserva del tiquete:");
		
		System.out.println();
		
		System.out.println("Nombre del pasajero: " + nombre);
		
		System.out.println("Número de identificación: " + idPasajero);
		
		System.out.println("Origen: " + origen);
		
		System.out.println("Destino: " + destino);
		
		System.out.println();
	}

	public static void gestionarTiquetes() {
		System.out.print("Ingrese el número de identificación del pasajero: ");
		
		String idPasajero = sc.nextLine();

		Pasajero pasajero = Pasajero.buscarPasajero(idPasajero);

		if (pasajero == null) {
			System.out.println("No hay tiquetes asociados " + 
			"con el número de identificación");
		} else {
			ArrayList<Tiquete> tiquetesValidos = 
					Tiquete.buscarTiquetesValidos(pasajero.getTiquetes());
			ArrayList<Tiquete> tiquetesVencidos = 
					Tiquete.buscarTiquetesVencidos(pasajero.getTiquetes());

			System.out.println("Tiquetes validos:");

			for (Tiquete tiquete : tiquetesValidos) {
				System.out.println(tiquete.toString());
			}

			System.out.println("Tiquetes vencidos:");

			for (Tiquete tiquete : tiquetesVencidos) {
				System.out.println(tiquete.toString());
			}

			System.out.print("Escoja el tiquete ingresando " 
			+ "el número de referencia:");
			
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
		
		viaje.setFecha(LocalDate.parse("2024-07-26"));
		viaje.setHora(LocalTime.of(15, 37));
		
		int opcion;

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
