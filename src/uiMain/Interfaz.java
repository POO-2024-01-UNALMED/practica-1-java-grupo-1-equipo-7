package uiMain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Habitacion;
import gestorAplicación.gestion.Hospedaje;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Tiquete;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.personas.Pasajero;
import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.Bus;

public class Interfaz{
	static Scanner sc = new Scanner(System.in);
	
	public static void chequearAsientos() {
		for(Asiento asiento : Asiento.getAsientos()) {
			if(asiento.getFechaReserva() != null) {
				if(asiento.getFechaReserva().isEqual(LocalDateTime.now()) 
						|| asiento.getFechaReserva().
						isBefore(LocalDateTime.now())) {
					asiento.setReservado(false);
					asiento.setFechaReserva(null);
				} else {
					asiento.setReservado(true);
					
					Duration duration = 
							Duration.between(LocalDateTime.now(), 
							asiento.getFechaReserva());
					
					ScheduledExecutorService service = 
							Executors.newScheduledThreadPool(1);
					
					Runnable task = () -> {
						asiento.setReservado(false);;
					};
					
					service.schedule(task, duration.toMinutes(), 
							TimeUnit.MINUTES);
				}
			}
		}
	}

	public static void verViajes() {
		for	(Empresa empresa : Empresa.getEmpresas()) {
			System.out.println("Vuelos disponibles de la empresa " 
			+ empresa.getNombre());
			
			for (int i = 0; i < 88; i++) {
				System.out.print("-");
			}

			System.out.println();

			System.out.print("    FECHA          ORIGEN          DESTINO" 
			+ "         HORA DE SALIDA     ID       PLACA     ");

			System.out.println();

			for (int i = 0; i < 88; i++) {
				System.out.print("-");
			}

			System.out.println();

			for(Viaje viaje : empresa.getViajes()) {
				System.out.print(viaje.toString());
				System.out.println();
			}
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

				for (int i = 0; i < 88; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA          ORIGEN          DESTINO" 
				+ "         HORA DE SALIDA     ID       PLACA     ");

				System.out.println();

				for (int i = 0; i < 88; i++) {
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

				viajes = Empresa.buscarViajes(origen.toUpperCase(), "");

				System.out.println("Vuelos filtrados por " 
				+ categoria.toUpperCase());

				for (int i = 0; i < 88; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA          ORIGEN          DESTINO" 
				+ "         HORA DE SALIDA     ID       PLACA     ");

				System.out.println();

				for (int i = 0; i < 88; i++) {
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

				viajes = Empresa.buscarViajes("", destino.toUpperCase());

				System.out.println("Vuelos filtrados por " 
				+ categoria.toUpperCase());

				for (int i = 0; i < 88; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA          ORIGEN          DESTINO" 
				+ "         HORA DE SALIDA     ID       PLACA     ");

				System.out.println();

				for (int i = 0; i < 88; i++) {
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

				for (int i = 0; i < 88; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA          ORIGEN          DESTINO" 
				+ "         HORA DE SALIDA     ID       PLACA     ");

				System.out.println();

				for (int i = 0; i < 88; i++) {
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

				for (int i = 0; i < 88; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA          ORIGEN          DESTINO" 
				+ "         HORA DE SALIDA     ID       PLACA     ");

				System.out.println();

				for (int i = 0; i < 88; i++) {
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

				for (int i = 0; i < 88; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.print("    FECHA          ORIGEN          DESTINO" 
				+ "         HORA DE SALIDA     ID       PLACA     ");

				System.out.println();

				for (int i = 0; i < 88; i++) {
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
					
					Duration duration = 
							Duration.between(LocalDateTime.now(), fechaReserva);
					
					ScheduledExecutorService service = 
							Executors.newScheduledThreadPool(1);
					
					Runnable task = () -> {
						viaje.liberarAsiento(asiento);
					};
					
					service.schedule(task, duration.toMinutes(), 
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
					
					Duration duration = 
							Duration.between(LocalDateTime.now(), fechaReserva);
					
					ScheduledExecutorService service = 
							Executors.newScheduledThreadPool(1);
					
					Runnable task = () -> {
						viaje.liberarAsiento(asiento);
					};
					
					service.schedule(task, duration.toMinutes(), 
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
		
			for (int i = 0; i < 80; i++) {
				System.out.print("-");
			}

			System.out.println();

			System.out.print("    FECHA          ORIGEN       DESTINO    " 
			+ "HORA DE SALIDA     ID       PLACA     ");

			System.out.println();

			for (int i = 0; i < 80; i++) {
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
		
		Pasajero pasajero = new Pasajero(nombre, idPasajero, correo, telefono);
		
		LocalDateTime fechaCompra = LocalDateTime.now();
		
		pasajero.agregarTiquete(new Tiquete(pasajero, viaje, 
				viaje.buscarAsiento(asiento), fechaCompra));

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
		
		System.out.println();

		Pasajero pasajero = Pasajero.buscarPasajero(idPasajero);

		if (pasajero == null) {
			System.out.println("No hay tiquetes asociados " 
			+ "con el número de identificación");
			
			System.out.println();
		} else {
			System.out.println("TIQUETES VALIDOS");
			
			for (int i = 0; i < 80; i++) {
				System.out.print("-");
			}

			System.out.println();

			System.out.print("    NOMBRE     ASIENTO     FECHA DE COMPRA" 
			+ "     NUMERO DE RESERVA");

			System.out.println();

			for (int i = 0; i < 80; i++) {
				System.out.print("-");
			}
			
			System.out.println();
			
			ArrayList<Tiquete> tiquetesValidos = 
					Tiquete.buscarTiquetes(pasajero.getTiquetes(), "validos");
			
			for(Tiquete tiquete : tiquetesValidos) {
				System.out.println(tiquete.toString());
			}
			
			System.out.println();
			
			System.out.println("TIQUETES VENCIDOS");
			
			for (int i = 0; i < 80; i++) {
				System.out.print("-");
			}

			System.out.println();

			System.out.print("    NOMBRE     ASIENTO     FECHA DE COMPRA" 
			+ "     NUMERO DE RESERVA");

			System.out.println();

			for (int i = 0; i < 80; i++) {
				System.out.print("-");
			}
			
			System.out.println();

			ArrayList<Tiquete> tiquetesVencidos = 
					Tiquete.buscarTiquetes(pasajero.getTiquetes(), "vencidos");
			
			for (Tiquete tiquete : tiquetesVencidos) {
				System.out.println(tiquete.toString());
			}
			
			System.out.println();

			System.out.print("Escoja el tiquete ingresando " 
			+ "el número de reserva: ");
			
			String numeroReserva = sc.nextLine();
			
			for (Tiquete tiquete : tiquetesVencidos) {
				if (tiquete.getNumeroReserva().equals(numeroReserva)) {
					System.out.println(tiquete);
					break;
				} 
			}
			
			for (Tiquete tiquete : tiquetesValidos) {
				if (tiquete.getNumeroReserva().equals(numeroReserva)) {
					System.out.println("¿Qué desea hacer?");
					
					System.out.println();
					
					System.out.println("1. Cancelarlo");
					
					System.out.println("2. Modificarlo");
					
					System.out.println();
					
					System.out.println("Ingrese el número: ");
					
					String respuesta1 = sc.nextLine();
					
					if (respuesta1.equals("1")) {
						tiquete.getViaje().liberarAsiento(tiquete.
								getAsiento().getNumeroAsiento());
						
						for(Tiquete tiqueteCancelado : pasajero.getTiquetes()) {
							if(tiqueteCancelado.equals(tiquete)) {
								pasajero.getTiquetes().remove(tiqueteCancelado);
								break;
							}
						}
					} else {
						System.out.println("¿Qué desea hacer?");
						
						System.out.println();
						
						System.out.println("1. Cambiar de asiento");
						
						System.out.println("2. Elegir otro viaje");
						
						System.out.println();
						
						System.out.println("Ingrese el número: ");
						
						String respuesta2 = sc.nextLine();
						
						System.out.println();
						
						if(respuesta2.equals("1")) {
							System.out.println("Asientos disponibles:");

							System.out.println();

							Viaje viaje = tiquete.getViaje();

							for (Asiento asiento : viaje.listaAsientos()) {
								if (asiento.getNumeroAsiento().length() == 2) {
									if (asiento.isReservado()) {
										if (asiento.getNumeroAsiento().
												contains("D")) {
											System.out.print("  ");
											
											System.out.println();
										} else {
											System.out.print("    ");
										}
									} else {
										if (asiento.getNumeroAsiento().
												contains("D")) {
											System.out.print(asiento.
													getNumeroAsiento());
											
											System.out.println();
										} else {
											System.out.print(asiento.
													getNumeroAsiento() 
											+ "  ");
										}
									}

								} else {
									if (asiento.isReservado()) {
										if (asiento.getNumeroAsiento().
												contains("D")) {
											System.out.print("   ");
											
											System.out.println();
										} else {
											System.out.print("    ");
										}
									} else {
										if (asiento.getNumeroAsiento().
												contains("D")) {
											System.out.print(asiento.
													getNumeroAsiento());
											
											System.out.println();
										} else {
											System.out.print(asiento.
													getNumeroAsiento() 
											+ " ");
										}
									}
								}
							}

							System.out.println();
							
							System.out.println("Ingrese el número del asiento");
							
							String asiento = sc.nextLine();
							
							tiquete.cambiarAsiento(viaje.buscarAsiento(asiento),
									tiquete.getAsiento());
						} else {
							Viaje viaje = tiquete.getViaje();
							
							viaje.liberarAsiento(tiquete.getAsiento().
									getNumeroAsiento());
							
							reservarTiquete();
						}
					}
					
					break;
				} 
			}
		}
	}
	
	public static void hospedaje() {
		System.out.print("Ingrese el número de " 
		+ "identificación del pasajero: ");
		
		String idPasajero = sc.nextLine();
		
		System.out.println();

		Pasajero pasajero = Pasajero.buscarPasajero(idPasajero);

		if (pasajero == null) {
			System.out.println("El pasajero no ha reservado tiquetes para " 
			+ "ningún viaje");
			
			System.out.println();
		} else {
			System.out.println("¿Para que viaje desea agregar el servicio " 
			+ "de hospedaje?");
			
			for (int i = 0; i < 80; i++) {
				System.out.print("-");
			}

			System.out.println();

			System.out.print("    FECHA          ORIGEN       DESTINO    " 
			+ "HORA DE SALIDA     ID       PLACA     ");

			System.out.println();

			for (int i = 0; i < 80; i++) {
				System.out.print("-");
			}

			System.out.println();
			
			for (Tiquete tiquete : pasajero.getTiquetes()) {
				System.out.println(tiquete.getViaje().toString());
			}
			
			System.out.println();
			
			System.out.print("Ingrese el id del viaje: ");
			
			String id = sc.nextLine();
			
			System.out.println();
			
			Viaje viaje = Empresa.buscarViaje(id);
			
			System.out.println("Hospedajes disponibles:");
			
			for (int i = 0; i < 32; i++) {
				System.out.print("-");
			}

			System.out.println();

			System.out.print("    NOMBRE	CALIFICACION");

			System.out.println();

			for (int i = 0; i < 32; i++) {
				System.out.print("-");
			}
			
			System.out.println();

			for (Hospedaje hospedaje : viaje.getHospedajes()) {
				System.out.println(hospedaje.toString());
			}
			
			System.out.println();
		
			System.out.print("Ingrese el nombre del hospedaje que desea: ");
			
			String nombre = sc.nextLine();
			
			System.out.println();
			
			Hospedaje hospedaje = viaje.buscarHospedaje(nombre);
			
			System.out.println("Habitaciones disponibles:");
			
			for (int i = 0; i < 60; i++) {
				System.out.print("-");
			}

			System.out.println();

			System.out.print("    PISO     NUMERO DE HABITACIÓN" 
			+ "     RESERVADA     DISPONIBLE EN     CATEGORIA");

			System.out.println();

			for (int i = 0; i < 60; i++) {
				System.out.print("-");
			}
			
			System.out.println();
			
			for (Habitacion habitacion : hospedaje.getHabitaciones()) {
				System.out.println(habitacion.toString());
			}
			
			System.out.print("Ingrese el número de la habitación: ");
			
			String numeroHabitacion = sc.nextLine();
			
			System.out.println();
			
			System.out.println("¿Por cuánto tiempo desea quedarse? " 
			+ "(horas/dias)");
			
			String tiempo = sc.nextLine();
			
			System.out.println();
			
			ScheduledExecutorService service = 
					Executors.newScheduledThreadPool(1);
			
			Runnable task = () -> {
				
			};
			
			service.schedule(task, Integer.valueOf(tiempo), 
							TimeUnit.MINUTES);
			
			Tiquete tiquete = pasajero.buscarTiquete(viaje);	
			
			tiquete.setHospedaje(hospedaje);
		}
	}
	
	public static void administrador() {
		System.out.println("Bienvenido Administrador, ¿Qué desea modificar?");
		
		System.out.println();
		
		System.out.println("1. Empresas");
		System.out.println("2. Hospedajes");
		System.out.println("3. Terminales");
		System.out.println("4. Viajes");
		System.out.println("5. Personal");
		System.out.println("6. Vehiculos");
		System.out.println("7. Volver");
		
		System.out.println();
		
		String respuesta1 = sc.nextLine();
		
		System.out.println();
		
		switch (respuesta1) {
		
		case "1":
			System.out.println("Ingrese una opción");
			
			System.out.println();
			
			System.out.println("1. Agregar");
			System.out.println("2. Ver");
			System.out.println("3. Eliminar");
			System.out.println("4. Volver");
			
			System.out.println();
			
			String empresas = sc.nextLine();
			
			switch (empresas) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Empresa");
				System.out.println("2. Conductores a una empresa");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String empresasAgregar = sc.nextLine();
				break;
			case "2":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Empresa");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String empresasVer = sc.nextLine();
				break;
			case "3":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Empresa");
				System.out.println("2. Conductores de una empresa");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String empresasEliminar = sc.nextLine();
				break;
			case "4":
				administrador();
			}
			break;
		case "2":
			System.out.println("Ingrese una opción");
			
			System.out.println();
			
			System.out.println("1. Agregar");
			System.out.println("2. Ver");
			System.out.println("3. Eliminar");
			System.out.println("4. Volver");
			
			System.out.println();
			
			String hospedajes = sc.nextLine();
			
			
			switch (hospedajes) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Hospedaje");
				System.out.println("2. habitaciones a un hospedaje");
				System.out.println("3. Calificación a un hospedaje");
				System.out.println("4. Volver");
				
				System.out.println();
				
				String hospedajesAgregar = sc.nextLine();
				break;
			case "2":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Hospedajes");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String hospedajesVer = sc.nextLine();
				break;
			case "3":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Hospedaje");
				System.out.println("2. Habitaciones de un hospedaje");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String hospedajesEliminar = sc.nextLine();
				break;
			case "4":
				administrador();
			}
			
			break;
		case "3":
			System.out.println("Ingrese una opción");
			
			System.out.println();
			
			System.out.println("1. Agregar");
			System.out.println("2. Anclar");
			System.out.println("3. Ver");
			System.out.println("4. Eliminar");
			System.out.println("5. Volver");
			
			System.out.println();
			
			String terminales = sc.nextLine();
			
			
			switch(terminales) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Terminal");
				System.out.println("2. Empresa a una terminal");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String terminalesAgregar = sc.nextLine();
				break;
			case "2":
				System.out.println("¿Qué desea anclar?");
				
				System.out.println();
				
				System.out.println("1. Revisor a una terminal");
				System.out.println("2. Aseador a una terminal");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String terminalesAnclar = sc.nextLine();
				break;
			case "3":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Terminales");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String terminalesVer = sc.nextLine();
				break;
			case "4":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Terminal");
				System.out.println("2. Empresa de una terminal");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String terminalesEliminar = sc.nextLine();
				break;
			case "5":
				administrador();
			}
			break;
		case "4":
			System.out.println("Ingrese una opción");
			
			System.out.println();
			
			System.out.println("1. Agregar");
			System.out.println("2. Añadir");
			System.out.println("3. Modificar");
			System.out.println("4. Ver");
			System.out.println("5. Eliminar");
			System.out.println("6. Volver");
			
			System.out.println();
			
			String viaje = sc.nextLine();
			
			switch (viaje) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Viaje");
				System.out.println("2. Tiquete");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String viajesAgregar = sc.nextLine();
				break;
			case "2":
				System.out.println("¿Qué desea añadir?");
				
				System.out.println();
				
				System.out.println("1. Hospedajes");
				System.out.println("2. Pasajeros");
				System.out.println("3. Revisores");
				System.out.println("4. Volver");
				
				System.out.println();
				
				String viajesAñadir = sc.nextLine();
				break;
			case "3":
				System.out.println("¿Qué desea modificar?");
				
				System.out.println();
				
				System.out.println("1. Viaje");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String viajesModificar = sc.nextLine();
				break;
			case "4":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Viajes");
				System.out.println("2. Tiquetes");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String viajesVer = sc.nextLine();
			
				break;
			case "5":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Viajes");
				System.out.println("2. Revisores");
				System.out.println("3. Hospedajes");
				System.out.println("4. Pasajeros");
				System.out.println("5. Volver");
				
				System.out.println();
				
				String viajesEliminar = sc.nextLine();
				break;
			case "6":
				administrador();
			}
			break;
	
		case "5":
			System.out.println("Ingrese una opción");
			
			System.out.println();
			
			System.out.println("1. Agregar");
			System.out.println("2. Modificar");
			System.out.println("3. Ver");
			System.out.println("4. Eliminar");
			System.out.println("5. Volver");
			
			System.out.println();
			
			String personal = sc.nextLine();
			
			switch (personal) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Pasajero");
				System.out.println("2. Conductor");
				System.out.println("3. Revisor");
				System.out.println("4. Aseador");
				System.out.println("5. Volver");
				
				System.out.println();
				
				String personalAgregar = sc.nextLine();
				break;
			case "2":
				System.out.println("¿Qué desea modificar?");
				
				System.out.println();
				
				System.out.println("1. Pasajero");
				System.out.println("2. Conductor");
				System.out.println("3. Revisor");
				System.out.println("4. Aseador");
				System.out.println("5. Volver");
				
				System.out.println();
				
				String personalmodificar = sc.nextLine();
				break;
			case "3":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Pasajeros");
				System.out.println("2. Conductores");
				System.out.println("3. Revisores");
				System.out.println("4. Aseadores");
				System.out.println("5. Volver");
				
				System.out.println();
				
				String personalVer = sc.nextLine();
				break;
			case "4":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Pasajero");
				System.out.println("2. Conductor");
				System.out.println("3. Revisor");
				System.out.println("4. Aseador");
				System.out.println("5. Volver");
				
				System.out.println();
				
				String personalEliminar = sc.nextLine();
				break;
			case "5":
				administrador();
			}
			break;
		case "6":
			System.out.println("Ingrese una opción");
			
			System.out.println();
			
			System.out.println("1. Agregar");
			System.out.println("2. Modificar");
			System.out.println("3. Ver");
			System.out.println("4. Eliminar");
			System.out.println("5. Volver");
			
			System.out.println();
			
			String vehiculos = sc.nextLine();
			
			switch (vehiculos) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Bus");
				System.out.println("2. MiniVan");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String vehiculosAgregar = sc.nextLine();
				break;
			case "2":
				System.out.println("¿Qué desea modificar?");
				
				System.out.println();
				
				System.out.println("1. Bus");
				System.out.println("2. MiniVan");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String vehiculosModificar = sc.nextLine();
				break;
			case "3":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Bus");
				System.out.println("2. MiniVan");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String vehiculosVer = sc.nextLine();
				break;
			case "4":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Bus");
				System.out.println("2. MiniVan");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String vehiculosEliminar = sc.nextLine();
				break;
			case "5":
				administrador();
			}
			break;
		case "7":
			break;
		}
	}

	public static void salirDelSistema(ArrayList<Empresa> empresas) {
		System.out.println("Ten un buen viaje");
		Serializador.limpiarArchivos();
		Serializador.serializar(empresas);
//		Serializador.serializar(viaje1);
//		Serializador.serializar(bus1);
//		Serializador.serializar(viaje2);
//		Serializador.serializar(bus2);
		System.exit(0);
	}

	public static void main(String[] args) {
		Terminal medellin = new Terminal("MEDELLIN");
		Terminal bogota = new Terminal("BOGOTA");
		Terminal cali = new Terminal("CALI");
		Terminal bucaramanga = new Terminal("BUCARAMANGA");
		Terminal pereira = new Terminal("PEREIRA");
		Terminal santaMarta = new Terminal("SANTA MARTA");
		
		ArrayList<Empresa> empresas = new ArrayList<Empresa>();
		
		Empresa empresa1 = new Empresa("Coord");
		Empresa empresa2 = new Empresa("Telm");
		Viaje viaje1 = new Viaje(medellin, bogota, "0001");
		Viaje viaje2 = new Viaje(medellin, cali, "0002");
		Viaje viaje3 = new Viaje(santaMarta, bucaramanga, "0003");
		Viaje viaje4 = new Viaje(pereira, medellin, "0004");
		Hospedaje hospedaje1 = new Hospedaje("Hostal", 4, 5);
		Bus bus1 = new Bus("0001", 12);
		Bus bus2 = new Bus("0002", 13);
		Bus bus3 = new Bus("0003", 12);
		Bus bus4 = new Bus("0004", 13);
		
		viaje2.getHospedajes().add(hospedaje1);
		
		empresas.add(empresa1);
		empresas.add(empresa2);
		
		empresa1.getViajes().add(viaje1);
		empresa1.getViajes().add(viaje3);
		
		viaje1.setEmpresa(empresa1);
		viaje1.setBus(bus1);
		viaje3.setEmpresa(empresa1);
		viaje3.setBus(bus3);
		
		viaje1.setFecha(LocalDate.parse("2024-08-20"));
		viaje1.setHora(LocalTime.of(15, 37));
		viaje3.setFecha(LocalDate.parse("2024-08-25"));
		viaje3.setHora(LocalTime.of(15, 37));
		
		empresa2.getViajes().add(viaje2);
		empresa2.getViajes().add(viaje4);
		
		viaje2.setEmpresa(empresa2);
		viaje2.setBus(bus2);
		viaje4.setEmpresa(empresa2);
		viaje4.setBus(bus4);
		
		viaje2.setFecha(LocalDate.parse("2024-08-21"));
		viaje2.setHora(LocalTime.of(15, 37));
		viaje4.setFecha(LocalDate.parse("2024-08-26"));
		viaje4.setHora(LocalTime.of(15, 37));
		
//		ArrayList<Empresa> empresas = new ArrayList<Empresa>();
//		
//		Empresa empresa1 = new Empresa();
//		Viaje viaje1 = new Viaje();
//		Bus bus1 = new Bus();
//		
//		Empresa empresa2 = new Empresa();
//		Viaje viaje2 = new Viaje();
//		Bus bus2 = new Bus();
//		
//		empresas.add(empresa2);
//		empresas.add(empresa1);
		
		chequearAsientos();
			
		String opcion;

		do {
			System.out.println("MENU PRINCIPAL");
			
			System.out.println();
			
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

			opcion = sc.nextLine();
			
			System.out.println();

			switch (opcion) {
			case "1":
				verViajes();
				break;
			case "2":
				reservarTiquete();
				break;
			case "3":
				gestionarTiquetes();
				break;
			case "4":
				hospedaje();
				break;
			case "5":
				administrador();
				break;
			case "6":
				salirDelSistema(empresas);
				break;
			default:
				System.out.println("Opción no válida");
				System.out.println();
				break;
			}
			
		} while (!opcion.equals("6"));
	}
}