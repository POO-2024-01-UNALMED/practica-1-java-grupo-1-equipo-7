package uiMain;

import java.text.Normalizer;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.ToLongBiFunction;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Habitacion;
import gestorAplicación.gestion.Hospedaje;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Tiquete;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.personas.Conductor;
import gestorAplicación.personas.Pasajero;
import gestorAplicación.personas.Persona;
import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.Bus;
import gestorAplicación.transporte.TipoAsiento;
import gestorAplicación.transporte.Vehiculo;

public class Interfaz {
	static Scanner sc = new Scanner(System.in);
	
	public static String input() {
		String input = sc.nextLine();
		
		return Normalizer.normalize(input, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static void chequearAsientosYHabitaciones() {
		for(Empresa empresa : Empresa.getEmpresas()) {
			for (Viaje viaje : empresa.getViajes()) {
				for (Asiento asiento : viaje.listaAsientos()) {
					if(asiento.getFechaReserva() != null) {
						System.out.println(asiento.getFechaReserva());
						if(asiento.getFechaReserva().isEqual(LocalDateTime.now()) 
								|| asiento.getFechaReserva().
								isBefore(LocalDateTime.now())) {
							asiento.liberar();
						} else {
							Duration duration = 
									Duration.between(LocalDateTime.now(), 
									asiento.getFechaReserva());
							
							ScheduledExecutorService service = 
									Executors.newScheduledThreadPool(1);
							
							Runnable task = () -> {
								asiento.liberar();
							};
							
							service.schedule(task, duration.toMinutes(), 
									TimeUnit.MINUTES);
						}
					} 
				}
			}
		}
		
		/*for(Habitacion habitacion : Habitacion.getHabitaciones()) {
			if(habitacion.getFechaReserva() != null) {
				if(habitacion.getFechaReserva().isEqual(LocalDateTime.now()) 
						|| habitacion.getFechaReserva().
						isBefore(LocalDateTime.now())) {
					habitacion.liberar();
				} else {
					Duration duration = 
							Duration.between(LocalDateTime.now(), 
							habitacion.getFechaReserva());
					
					ScheduledExecutorService service = 
							Executors.newScheduledThreadPool(2);
					
					Runnable task = () -> {
						habitacion.liberar();
					};
					
					service.schedule(task, duration.toMinutes(), 
							TimeUnit.MINUTES);
				}
			}
		}*/
	}

	public static void verViajes() {
		for	(Empresa empresa : Empresa.getEmpresas()) {
			System.out.println("Viajes disponibles de la empresa " 
			+ empresa.getNombre());
			
			for (int i = 0; i < 92; i++) {
				System.out.print("-");
			}


			System.out.println();

			System.out.println("    FECHA          ORIGEN          DESTINO" 
			+ "         HORA DE SALIDA     ID      PLACA BUS");
			

			for (int i = 0; i < 92; i++) {
				System.out.print("-");
			}

			System.out.println();

			for(Viaje viaje : empresa.getViajes()) {
				System.out.println(viaje);
				
			}
			
			System.out.println();
		}
		
		System.out.println("¿Desea filtrar por alguna categoría (si/no)");
		
		String respuesta1 = input();

		System.out.println();
		
		boolean ok = true;
		
		if (respuesta1.toLowerCase().equals("si")) {
			System.out.println("¿Por cuál categoría desea filtrar?");

			String categoria = input();

			System.out.println();

			ArrayList<Viaje> viajes;

			switch (categoria.toUpperCase()) {
			case "FECHA":
				System.out.print("Ingrese la fecha en formato dd-mm-aaaa: ");

				String fecha = input();

				System.out.println();

				DateTimeFormatter formateoFecha = 
						DateTimeFormatter.ofPattern("dd-MM-yyyy");

				LocalDate nuevaFecha = LocalDate.parse(fecha, formateoFecha);
				
				viajes = Empresa.buscarViajes(nuevaFecha);
				
				if (viajes.isEmpty()) {
					System.out.println("No se encontraron viajes");
					System.out.println();
					ok = false;
					break;
				} else {
					System.out.println("Vuelos filtrados por " 
					+ categoria.toUpperCase());

					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}

					System.out.println();

					System.out.println("    FECHA          ORIGEN          DESTINO" 
					+ "         HORA DE SALIDA     ID      PLACA BUS");

					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}

					System.out.println();

					for (Viaje viaje : viajes) {
						System.out.println(viaje);
					}

					System.out.println();
		
					break;
				}
			case "ORIGEN":
				System.out.print("Ingrese el origen: ");

				String origen = input();

				System.out.println();

				viajes = Empresa.buscarViajes(origen.toUpperCase(), "");
				
				if (viajes.isEmpty()) {
					System.out.println("No se encontraron viajes");
					System.out.println();
					ok = false;
					break;
				} else {
					System.out.println("Vuelos filtrados por " 
					+ categoria.toUpperCase());

					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}

					System.out.println();

					System.out.println("    FECHA          ORIGEN          DESTINO" 
					+ "         HORA DE SALIDA     ID      PLACA BUS");

					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}

					System.out.println();

					for (Viaje viaje : viajes) {
						System.out.println(viaje);
					}
		
					System.out.println();

					break;
				}
			case "DESTINO":
				System.out.print("Ingrese el destino: ");

				String destino = input();

				System.out.println();

				viajes = Empresa.buscarViajes("", destino.toUpperCase());
				
				if (viajes.isEmpty()) {
					System.out.println("No se encontraron viajes");
					System.out.println();
					ok = false;
					break;
				} else {
					System.out.println("Vuelos filtrados por " 
					+ categoria.toUpperCase());
	
					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}
	
					System.out.println();
	
					System.out.println("    FECHA          ORIGEN          DESTINO" 
					+ "         HORA DE SALIDA     ID      PLACA BUS");
	
					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}
	
					System.out.println();
	
					for (Viaje viaje : viajes) {
						System.out.println(viaje);
					}
	
					System.out.println();
	
					break;
				}
			case "HORA DE SALIDA":
				System.out.print("Ingrese la hora de salida " 
				+ "en formato 24 horas: ");

				String hora = input();

				System.out.println();

				DateTimeFormatter formateoHora = 
						DateTimeFormatter.ofPattern("HH:mm");

				LocalTime nuevaHora = 
						LocalTime.parse(hora, formateoHora);

				viajes = Empresa.buscarViajes(nuevaHora);
				
				if (viajes.isEmpty()) {
					System.out.println("No se encontraron viajes");
					System.out.println();
					ok = false;
					break;
				} else {
					System.out.println("Vuelos filtrados por " 
					+ categoria.toUpperCase());
	
					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}
	
					System.out.println();
	
					System.out.println("    FECHA          ORIGEN          DESTINO" 
					+ "         HORA DE SALIDA     ID      PLACA BUS");
	
					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}
	
					System.out.println();
	
					for (Viaje viaje : viajes) {
						System.out.println(viaje);
					}
	
					System.out.println();
	
					break;
				}
			case "ID":
				System.out.print("Ingrese el id del viaje: ");

				String id = input();

				System.out.println();

				viajes = Empresa.buscarViajes(id);
				
				if (viajes.isEmpty()) {
					System.out.println("No se encontraron viajes");
					System.out.println();
					ok = false;
					break;
				} else {
					System.out.println("Vuelos filtrados por " 
					+ categoria.toUpperCase());
	
					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}
	
					System.out.println();
	
					System.out.println("    FECHA          ORIGEN          DESTINO" 
							+ "         HORA DE SALIDA     ID      PLACA BUS");
	
					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}
	
					System.out.println();
	
					for (Viaje viaje : viajes) {
						System.out.println(viaje);
					}
	
					System.out.println();
	
					break;
				}
			case "PLACA":
				System.out.print("Ingrese la placa del vehiculo: ");

				String placa = input();

				System.out.println();

				viajes = Empresa.buscarViajes(placa);

				if (viajes.isEmpty()) {
					System.out.println("No se encontraron viajes");
					System.out.println();
					ok = false;
					break;
				} else {
					System.out.println("Vuelos filtrados por " 
					+ categoria.toUpperCase());
	
					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}
	
					System.out.println();
	
					System.out.println("    FECHA          ORIGEN          DESTINO" 
							+ "         HORA DE SALIDA     ID      PLACA BUS");
	
					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}
	
					System.out.println();
	
					for (Viaje viaje : viajes) {
						System.out.println(viaje);
					}
					
					System.out.println();
	
					break;
				}
			}
			
			if (ok) {
				System.out.println("¿Desea ver más detalles sobre un viaje? " 
				+ "(si/no)");
	
				String respuesta2 = input();
	
				System.out.println();
	
				if (respuesta2.toLowerCase().equals("si")) {
					System.out.print("Ingrese el id del viaje: ");
	
					String id = input();
	
					System.out.println();
					
					Viaje viaje = Empresa.buscarViaje(id);
					
					if (viaje == null) {
						System.out.println("No se encontró ningún viaje con el número de id");
						System.out.println();
					} else {
						if (!viaje.tieneSillas()) {
							System.out.println("No hay sillas disponibles");
							System.out.println();
							System.out.println("¿Qué desea hacer?");
							System.out.println();
							System.out.println("1. Ver otros viajes");
							System.out.println();
							System.out.println("2. Ir al menú principal");
							
							String opcion = input();
							
							if (opcion.equals("1")) {
								verViajes();
							} else if (opcion.equals("2")) {
								
							}
						} else {
							System.out.println("Asientos disponibles:");
			
							System.out.println();
			
							int[] tiposAsientoFila = viaje.tiposAsiento();
							int fila = 1;
							int indice = 0;
							
							System.out.println(TipoAsiento.PREFERENCIAL);
									
							System.out.println("	       --");
				
							for (Asiento asiento : viaje.listaAsientos()) {
								if (asiento.getNumero().length() == 2) {
									if (asiento.isReservado()) {
										if (asiento.getNumero().contains("D")) {
											if (fila == tiposAsientoFila[indice]) {
												System.out.println(asiento.
														getNumero() + "   |");
												System.out.println("	       --");
												
												if (indice == 0) {
													System.out.
														println(TipoAsiento.PREMIUM);
													System.out.println("	       --");
												} else {
													System.out.
														println(TipoAsiento.ESTANDAR);
													System.out.println("	       --");
												}
												
												if(indice < tiposAsientoFila.length - 1) {
													indice++;
												}
											} else {
												System.out.println("  " + "   |");
											}
											fila++;
										} else {
											System.out.print("    ");
										}
									} else {
										if (asiento.getNumero().contains("D")) {
											if (fila == tiposAsientoFila[indice]) {
												System.out.println(asiento.
														getNumero() + "   |");
												System.out.println("	       --");
												
												if (indice == 0) {
													System.out.
														println(TipoAsiento.PREMIUM);
													System.out.println("	       --");
												} else {
													System.out.
														println(TipoAsiento.ESTANDAR);
													System.out.println("	       --");
												}
												
												if(indice < tiposAsientoFila.length - 1) {
													indice++;
												}
											} else {
												System.out.print(asiento.getNumero() 
												+ "   |");
												System.out.println();
											}
											fila++;
										} else {
										
											System.out.print(asiento.getNumero() 
											+ "  ");
										}
									}
				
								} else {
									if (asiento.isReservado()) {
										if (asiento.getNumero().contains("D")) {
											if (fila == tiposAsientoFila[indice]) {
												System.out.println("   " + "  |");
												System.out.println("	       --");
												
												if (indice == 0) {
													System.out.
														println(TipoAsiento.PREMIUM);
													System.out.println("	       --");
												} else {
													System.out.
														println(TipoAsiento.ESTANDAR);
													System.out.println("	       --");
												}
												
												if(indice < tiposAsientoFila.length - 1) {
													indice++;
												}
											} else {
												System.out.print("   " + "  |");
												System.out.println();
											}
											fila++;
										} else {
											System.out.print("    ");
										}
									} else {
										if (asiento.getNumero().contains("D")) {
											if (fila == tiposAsientoFila[indice]) {
												System.out.println(asiento.
														getNumero() + "  |");
												System.out.println("	       --");
												
												if (indice == 0) {
													System.out.
														println(TipoAsiento.PREMIUM);
													System.out.println("	       --");
												} else {
													System.out.
														println(TipoAsiento.ESTANDAR);
													System.out.println("	       --");
												}
												
												if(indice < tiposAsientoFila.length - 1) {
													indice++;
												}
											} else {
												System.out.print(asiento.getNumero() 
												+ "  |");
												System.out.println();
											}
											fila++;
										} else {
											System.out.print(asiento.getNumero() 
											+ " ");
										}
									}
								}
							}
							
							System.out.println("	       --");
							
							System.out.println();
			
							System.out.println("¿Desea reservar un asiento por un " 
							+ "cierto período de tiempo? (si/no)");
			
							String respuesta3 = input();
			
							System.out.println();
			
							if (respuesta3.toLowerCase().equals("si")) {
								System.out.print("Ingrese el número del asiento: ");
			
								String numeroAsiento = input();
								
								if (viaje.buscarAsiento(numeroAsiento) == null ||
										viaje.buscarAsiento(numeroAsiento).isReservado() ) {
									while (true) {
										System.out.println();
										System.out.println("El asiento no está disponible");
										System.out.println();
										System.out.print("Ingrese otro número de asiento: ");
										System.out.println();
										
										numeroAsiento = input();
										
										if (viaje.buscarAsiento(numeroAsiento) != null 
												&& !viaje.buscarAsiento(numeroAsiento).isReservado()) {
											System.out.println();
											break;
										}
									}
								}
								
								final String numeroAsiento2 = numeroAsiento;
								
								System.out.println();
								
								System.out.println("¿Por cuánto tiempo desea reservarlo?" 
								+ " (minutos/horas/dias)");
			
								String tiempo = input();
			
								System.out.println();
								
								String[] arrayTiempo = tiempo.split("[\s]");
								
								String cantidad = arrayTiempo[0];
								
								LocalDateTime fechaReserva = null;
								
								LocalDateTime fechaViaje 
										= LocalDateTime.of(viaje.getFecha(), viaje.getHora());
								
								boolean ok2 = true;
								
								if (tiempo.toLowerCase().contains("minutos") 
										|| tiempo.toLowerCase().contains("minuto")) {
									fechaReserva = LocalDateTime.now().
											plusMinutes(Integer.valueOf(cantidad));
								} else if (tiempo.toLowerCase().contains("horas") 
												|| tiempo.toLowerCase().contains("hora")) {
									fechaReserva = LocalDateTime.now().
											plusHours(Integer.valueOf(cantidad));
								} else if (tiempo.toLowerCase().contains("dias")
												|| tiempo.toLowerCase().contains("dia"))  {
									fechaReserva = LocalDateTime.now().
											plusDays(Integer.valueOf(cantidad));
								} else {
									System.out.println("Tiempo no válido");
									System.out.println();
									ok2 = false;
								}
								
								if (ok2) {
									boolean ok3 = true;
									
									while (fechaViaje.isBefore(fechaReserva)) {
										System.out.println("La reserva debe ser " 
										+ "antes del viaje (ó Ingrese 'E' para volver al menú principal)" );
						
										System.out.println();
										
										System.out.println("¿Por cuánto tiempo desea " 
										+ "reservarlo? (minutos/horas/dias)");
				
										tiempo = input();
									
										if (tiempo.toUpperCase().equals("E")){
											ok3 = false;
											System.out.println();
											break;
										}
										
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
										} else if (tiempo.toLowerCase().contains("dias")
												|| tiempo.toLowerCase().contains("dia"))  {
											fechaReserva = LocalDateTime.now().
													plusDays(Integer.valueOf(cantidad));
										} else {
											System.out.println("Tiempo no válido");
											System.out.println();
											ok3 = false;
											break;
										}
									}
									
									if (ok3) {
										viaje.reservarAsiento(numeroAsiento2, fechaReserva);
										
										Duration duration = 
												Duration.between(LocalDateTime.now(), fechaReserva);
										
										ScheduledExecutorService service = 
												Executors.newScheduledThreadPool(1);
										
										Runnable task = () -> {
											viaje.liberarAsiento(numeroAsiento2);
										};
										
										service.schedule(task, duration.toMinutes(), 
														TimeUnit.MINUTES);
										
										System.out.println("Asiento reservado exitosamente");
										System.out.println();
									}
								}
							}
						}
					}
				}
			}
		} else {
			System.out.println("¿Desea ver más detalles sobre un viaje? " 
			+ "(si/no)");
	
			String respuesta2 = input();
	
			System.out.println();
	
			if (respuesta2.toLowerCase().equals("si")) {
				System.out.print("Ingrese el id del viaje: ");
	
				String id = input();
	
				System.out.println();
				
				Viaje viaje = Empresa.buscarViaje(id);
				
				if (viaje == null) {
					System.out.println("No se encontró ningún viaje con número de id " + id);
					System.out.println();
				} else {
					if (!viaje.tieneSillas()) {
						System.out.println("No hay sillas disponibles");
						System.out.println();
						System.out.println("¿Qué desea hacer?");
						System.out.println();
						System.out.println("1. Ver otros viajes");
						System.out.println();
						System.out.println("2. Ir al menú principal");
						
						String opcion = input();
						
						if (opcion.equals("1")) {
							verViajes();
						} else if (opcion.equals("2")) {
							
						}
					} else {
						System.out.println("Asientos disponibles:");
			
						System.out.println();
			
						int[] tiposAsientoFila = viaje.tiposAsiento();
						int fila = 1;
						int indice = 0;
						
						System.out.println(TipoAsiento.PREFERENCIAL);
								
						System.out.println("	       --");
			
						for (Asiento asiento : viaje.listaAsientos()) {
							if (asiento.getNumero().length() == 2) {
								if (asiento.isReservado()) {
									if (asiento.getNumero().contains("D")) {
										if (fila == tiposAsientoFila[indice]) {
											System.out.println(asiento.
													getNumero() + "   |");
											System.out.println("	       --");
											
											if (indice == 0) {
												System.out.
													println(TipoAsiento.PREMIUM);
												System.out.println("	       --");
											} else {
												System.out.
													println(TipoAsiento.ESTANDAR);
												System.out.println("	       --");
											}
											
											if(indice < tiposAsientoFila.length - 1) {
												indice++;
											}
										} else {
											System.out.println("  " + "   |");
										}
										fila++;
									} else {
										System.out.print("    ");
									}
								} else {
									if (asiento.getNumero().contains("D")) {
										if (fila == tiposAsientoFila[indice]) {
											System.out.println(asiento.
													getNumero() + "   |");
											System.out.println("	       --");
											
											if (indice == 0) {
												System.out.
													println(TipoAsiento.PREMIUM);
												System.out.println("	       --");
											} else {
												System.out.
													println(TipoAsiento.ESTANDAR);
												System.out.println("	       --");
											}
											
											if(indice < tiposAsientoFila.length - 1) {
												indice++;
											}
										} else {
											System.out.print(asiento.getNumero() 
											+ "   |");
											System.out.println();
										}
										fila++;
									} else {
									
										System.out.print(asiento.getNumero() 
										+ "  ");
									}
								}
			
							} else {
								if (asiento.isReservado()) {
									if (asiento.getNumero().contains("D")) {
										if (fila == tiposAsientoFila[indice]) {
											System.out.println("   " + "  |");
											System.out.println("	       --");
											
											if (indice == 0) {
												System.out.
													println(TipoAsiento.PREMIUM);
												System.out.println("	       --");
											} else {
												System.out.
													println(TipoAsiento.ESTANDAR);
												System.out.println("	       --");
											}
											
											if(indice < tiposAsientoFila.length - 1) {
												indice++;
											}
										} else {
											System.out.print("   " + "  |");
											System.out.println();
										}
										fila++;
									} else {
										System.out.print("    ");
									}
								} else {
									if (asiento.getNumero().contains("D")) {
										if (fila == tiposAsientoFila[indice]) {
											System.out.println(asiento.
													getNumero() + "  |");
											System.out.println("	       --");
											
											if (indice == 0) {
												System.out.
													println(TipoAsiento.PREMIUM);
												System.out.println("	       --");
											} else {
												System.out.
													println(TipoAsiento.ESTANDAR);
												System.out.println("	       --");
											}
											
											if(indice < tiposAsientoFila.length - 1) {
												indice++;
											}
										} else {
											System.out.print(asiento.getNumero() 
											+ "  |");
											System.out.println();
										}
										fila++;
									} else {
										System.out.print(asiento.getNumero() 
										+ " ");
									}
								}
							}
						}
						
						System.out.println("	       --");
			
						System.out.println();
			
						System.out.println("¿Desea reservar un asiento por un " 
						+ "cierto período de tiempo? (si/no)");
			
						String respuesta3 = input();
			
						System.out.println();
			
						if (respuesta3.toLowerCase().equals("si")) {
							System.out.print("Ingrese el número del asiento: ");
			
							String numeroAsiento = input();
			
							System.out.println();
							
							if (viaje.buscarAsiento(numeroAsiento) == null||
									viaje.buscarAsiento(numeroAsiento).isReservado()) {
								while (true) {
									System.out.println();
									System.out.println("El asiento no está disponible");
									System.out.println();
									System.out.print("Ingrese otro número de asiento: ");
									
									numeroAsiento = input();
									
									if (viaje.buscarAsiento(numeroAsiento) != null 
											&& !viaje.buscarAsiento(numeroAsiento).isReservado()) {
										System.out.println();
										break;
									}
								}
							}
							
							final String numeroAsiento2 = numeroAsiento;
			
							System.out.println("¿Por cuánto tiempo desea reservarlo?" 
							+ " (minutos/horas/dias)");
			
							String tiempo = input();
			
							System.out.println();
							
							String[] arrayTiempo = tiempo.split("[\s]");
							
							String cantidad = arrayTiempo[0];
							
							LocalDateTime fechaReserva = null;
							
							LocalDateTime fechaViaje 
									= LocalDateTime.of(viaje.getFecha(), viaje.getHora());
							
							boolean ok2 = true;
							
							if (tiempo.toLowerCase().contains("minutos") 
									|| tiempo.toLowerCase().contains("minuto")) {
								fechaReserva = LocalDateTime.now().
										plusMinutes(Integer.valueOf(cantidad));
							} else if (tiempo.toLowerCase().contains("horas") 
											|| tiempo.toLowerCase().contains("hora")) {
								fechaReserva = LocalDateTime.now().
										plusHours(Integer.valueOf(cantidad));
							} else if (tiempo.toLowerCase().contains("dias")
											|| tiempo.toLowerCase().contains("dia"))  {
								fechaReserva = LocalDateTime.now().
										plusDays(Integer.valueOf(cantidad));
							} else {
								System.out.println("Tiempo no válido");
								System.out.println();
								ok2 = false;
							}
							
							if (ok2) {
								boolean ok3 = true;
								
								while (fechaViaje.isBefore(fechaReserva)) {
									System.out.println("La reserva debe ser " 
									+ "antes del viaje (ó Ingrese 'E' para volver al menú principal)" );
					
									System.out.println();
									
									System.out.println("¿Por cuánto tiempo desea " 
									+ "reservarlo? (minutos/horas/dias)");
			
									tiempo = input();
								
									if (tiempo.toUpperCase().equals("E")){
										System.out.println();
										ok3 = false;
										break;
									}
									
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
									} else if (tiempo.toLowerCase().contains("dias")
											|| tiempo.toLowerCase().contains("dia"))  {
										fechaReserva = LocalDateTime.now().
												plusDays(Integer.valueOf(cantidad));
									} else {
										System.out.println("Tiempo no válido");
										System.out.println();
										ok3 = false;
										break;
									}
								}
								
								if (ok3) {
									viaje.reservarAsiento(numeroAsiento2, fechaReserva);
									
									Duration duration = 
											Duration.between(LocalDateTime.now(), fechaReserva);
									
									ScheduledExecutorService service = 
											Executors.newScheduledThreadPool(1);
									
									Runnable task = () -> {
										viaje.liberarAsiento(numeroAsiento2);
									};
									
									service.schedule(task, duration.toMinutes(), 
													TimeUnit.MINUTES);
									
									System.out.println("Asiento reservado exitosamente");
									System.out.println();
								}
							}
						}
					}
				}
			}
		}
	}

	public static void reservarTiquete() {
		System.out.print("Ingrese el origen: ");
		
		String origen = input();
		
		System.out.println();

		System.out.print("Ingrese el destino: ");
		
		String destino = input();
		
		System.out.println();
		
		ArrayList<Viaje> viajes = Empresa.buscarViajes(origen.toUpperCase(), 
				destino.toUpperCase());
		
		if (viajes.isEmpty()) {
			System.out.println("No se encontraron viajes disponibles para reservar");
			System.out.println();
		} else {
			System.out.println("Estos son los viajes disponibles para la ruta " 
			+ origen.toUpperCase() + " --> " + destino.toUpperCase() + ":");
			
			for (Viaje viaje : viajes) {
			
				for (int i = 0; i < 92; i++) {
					System.out.print("-");
				}
	
				System.out.println();
	
				System.out.println("    FECHA          ORIGEN          DESTINO" 
						+ "         HORA DE SALIDA     ID      PLACA BUS");
	
				for (int i = 0; i < 92; i++) {
					System.out.print("-");
				}
	
				System.out.println();
	
				System.out.println(viaje);
	
				System.out.println();
			}
	
			System.out.print("Ingrese el id del viaje: ");
			
			String id = input();
			
			System.out.println();
			
			Viaje viaje = Viaje.buscarViaje(viajes, id);
			
			if (viaje == null) {
				System.out.println("No se encontró ningún viaje con número de id " + id 
									+ "disponible para reservar");
				System.out.println();
			} else {
				System.out.println("Asientos disponibles:");
				
				System.out.println();
				
				int[] tiposAsientoFila = viaje.tiposAsiento();
				int fila = 1;
				int indice = 0;
				
				System.out.println(TipoAsiento.PREFERENCIAL);
						
				System.out.println("	       --");
				
				for (Asiento asiento : viaje.listaAsientos()) {
					if (asiento.getNumero().length() == 2) {
						if (asiento.isReservado()) {
							if (asiento.getNumero().contains("D")) {
								if (fila == tiposAsientoFila[indice]) {
									System.out.println(asiento.
											getNumero() + "   |");
									System.out.println("	       --");
									
									if (indice == 0) {
										System.out.
											println(TipoAsiento.PREMIUM);
										System.out.println("	       --");
									} else {
										System.out.
											println(TipoAsiento.ESTANDAR);
										System.out.println("	       --");
									}
									
									if(indice < tiposAsientoFila.length - 1) {
										indice++;
									}
								} else {
									System.out.println("  " + "   |");
								}
								fila++;
							} else {
								System.out.print("    ");
							}
						} else {
							if (asiento.getNumero().contains("D")) {
								if (fila == tiposAsientoFila[indice]) {
									System.out.println(asiento.
											getNumero() + "   |");
									System.out.println("	       --");
									
									if (indice == 0) {
										System.out.
											println(TipoAsiento.PREMIUM);
										System.out.println("	       --");
									} else {
										System.out.
											println(TipoAsiento.ESTANDAR);
										System.out.println("	       --");
									}
									
									if(indice < tiposAsientoFila.length - 1) {
										indice++;
									}
								} else {
									System.out.print(asiento.getNumero() 
									+ "   |");
									System.out.println();
								}
								fila++;
							} else {
							
								System.out.print(asiento.getNumero() 
								+ "  ");
							}
						}
		
					} else {
						if (asiento.isReservado()) {
							if (asiento.getNumero().contains("D")) {
								if (fila == tiposAsientoFila[indice]) {
									System.out.println("   " + "  |");
									System.out.println("	       --");
									
									if (indice == 0) {
										System.out.
											println(TipoAsiento.PREMIUM);
										System.out.println("	       --");
									} else {
										System.out.
											println(TipoAsiento.ESTANDAR);
										System.out.println("	       --");
									}
									
									if(indice < tiposAsientoFila.length - 1) {
										indice++;
									}
								} else {
									System.out.print("   " + "  |");
									System.out.println();
								}
								fila++;
							} else {
								System.out.print("    ");
							}
						} else {
							if (asiento.getNumero().contains("D")) {
								if (fila == tiposAsientoFila[indice]) {
									System.out.println(asiento.
											getNumero() + "  |");
									System.out.println("	       --");
									
									if (indice == 0) {
										System.out.
											println(TipoAsiento.PREMIUM);
										System.out.println("	       --");
									} else {
										System.out.
											println(TipoAsiento.ESTANDAR);
										System.out.println("	       --");
									}
									
									if(indice < tiposAsientoFila.length - 1) {
										indice++;
									}
								} else {
									System.out.print(asiento.getNumero() 
									+ "  |");
									System.out.println();
								}
								fila++;
							} else {
								System.out.print(asiento.getNumero() 
								+ " ");
							}
						}
					}
				}
			
				System.out.println("	       --");
				
				System.out.println();
				
				System.out.print("Ingrese el número del asiento: ");
				
				String numeroAsiento = input();
				
				
				if (viaje.buscarAsiento(numeroAsiento) == null||
						viaje.buscarAsiento(numeroAsiento).isReservado()) {
					while (true) {
						System.out.println();
						System.out.println("El asiento no está disponible");
						System.out.println();
						System.out.print("Ingrese otro número de asiento:");
						
						numeroAsiento = input();
					
						
						if (!viaje.buscarAsiento(numeroAsiento).isReservado() 
								&& viaje.buscarAsiento(numeroAsiento) != null) {
							System.out.println();
							break;
						}
					}
					
				}

				viaje.reservarAsiento(numeroAsiento, null);
		
				System.out.println("Ingrese sus datos:");
				
				System.out.println();
		
				System.out.print("Nombre completo: ");
				
				String nombre = input();
				
				System.out.print("Número de identificación (6 dígitos): ");
				
				String idPasajero = input();
				
				while (idPasajero.length() != 6) {
					System.out.print("Número de identificación (6 dígitos): ");
					idPasajero = input();
				}
				
				if((Pasajero.buscarPasajero(idPasajero)==null)) {
					System.out.print("Teléfono: ");
					
					String telefono = input();
					
					System.out.print("Correo electrónico: ");
					
					String correo = input();
				
				Pasajero pasajero = new Pasajero(nombre, idPasajero,telefono,correo);
				
				Asiento asiento = viaje.buscarAsiento(numeroAsiento);
				
				Tiquete tiquete = new Tiquete(pasajero, viaje, asiento);
				
				pasajero.agregarTiquete(tiquete);
				
				System.out.println("Tiquete reservado exitosamente");
				
				System.out.println();
	
				for (int i = 0; i < 34; i++) {
					System.out.print("-");
				}
				
				System.out.println();
				
				System.out.println("    Tiquete No." + tiquete.getNumeroReserva());
				
				for (int i = 0; i < 34; i++) {
					System.out.print("-");
				}
	
				System.out.println();
				
				System.out.println("Nombre del pasajero: " + nombre);
				System.out.println("Id del pasajero: " + idPasajero);
				System.out.println("Teléfono: " + telefono);
				System.out.println("Correo: " + correo);
				System.out.println("Asiento: " + asiento);
				System.out.println("Empresa: " + viaje.getEmpresa().getNombre());
				System.out.println("Placa del bus: " + viaje.getBus().getPlaca());
				System.out.println("Id del viaje: " + viaje.getId());
				System.out.println("Fecha y hora: " + viaje.getFecha() + " " + viaje.getHora());
				System.out.println("Origen: " + viaje.getTerminalOrigen().getUbicacion());
				System.out.println("Destino: " + viaje.getTerminalDestino().getUbicacion());
				
				for (int i = 0; i < 34; i++) {
					System.out.print("-");
				}
	
				System.out.println();
				System.out.println();
				}
				else {
					Asiento asiento = viaje.buscarAsiento(numeroAsiento);
					
					Pasajero pasajero=Pasajero.buscarPasajero(idPasajero);
					
					Tiquete tiquete = new Tiquete(Pasajero.buscarPasajero(idPasajero), viaje, asiento);
					
					Pasajero.buscarPasajero(idPasajero).agregarTiquete(tiquete);
					System.out.println("Tiquete reservado exitosamente");
					
					System.out.println();
		
					for (int i = 0; i < 34; i++) {
						System.out.print("-");
					}
					
					System.out.println();
					
					System.out.println("    Tiquete No." + tiquete.getNumeroReserva());
					
					for (int i = 0; i < 34; i++) {
						System.out.print("-");
					}
		
					System.out.println();
					
					System.out.println("Nombre del pasajero: " + nombre);
					System.out.println("Id del pasajero: " + idPasajero);
					System.out.println("Teléfono: " + pasajero.getTelefono());
					System.out.println("Correo: " + pasajero.getCorreo());
					System.out.println("Asiento: " + asiento);
					System.out.println("Empresa: " + viaje.getEmpresa().getNombre());
					System.out.println("Placa del bus: " + viaje.getBus().getPlaca());
					System.out.println("Id del viaje: " + viaje.getId());
					System.out.println("Fecha y hora: " + viaje.getFecha() + " " + viaje.getHora());
					System.out.println("Origen: " + viaje.getTerminalOrigen().getUbicacion());
					System.out.println("Destino: " + viaje.getTerminalDestino().getUbicacion());
					
					for (int i = 0; i < 34; i++) {
						System.out.print("-");
					}
		
					System.out.println();
					System.out.println();
				}
			}
		}
	}

	public static void gestionarTiquetes() {
		System.out.print("Ingrese el número de identificación del pasajero: ");
		
		String idPasajero = input();
		
		System.out.println();

		Pasajero pasajero = Pasajero.buscarPasajero(idPasajero);

		if (pasajero == null || pasajero.getTiquetes().isEmpty()) {
			System.out.println("No hay tiquetes asociados " 
			+ "con el número de identificación");
			
			System.out.println();
		} else {
			ArrayList<Tiquete> tiquetesValidos = 
					pasajero.buscarTiquetes("validos");
			
			ArrayList<Tiquete> tiquetesVencidos = 
					pasajero.buscarTiquetes("vencidos");
			
			if(!tiquetesValidos.isEmpty()) {
				System.out.println("Tiqueres válidos");
				
				for (int i = 0; i < 93; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.println("    NUMERO DE RESERVA     NOMBRE        ASIENTO" 
				+ "             FECHA DEL VIAJE      ID VIAJE     ");

				for (int i = 0; i < 93; i++) {
					System.out.print("-");
				}
				
				System.out.println();

				for(Tiquete tiquete : tiquetesValidos) {
					System.out.println(tiquete);
				}
				
				System.out.println();
			}
			
			if(!tiquetesVencidos.isEmpty()) {
				System.out.println("Tiquetes vencidos");
				
				for (int i = 0; i < 93; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.println("    NUMERO DE RESERVA     NOMBRE        ASIENTO" 
				+ "             FECHA DEL VIAJE      ID VIAJE     ");

				for (int i = 0; i < 93; i++) {
					System.out.print("-");
				}
				
				System.out.println();

				for(Tiquete tiquete : tiquetesVencidos) {
					System.out.println(tiquete);
				}
				
				System.out.println();
			}
			
			System.out.println("¿Desea escoger algún tiquete? (si/no)");
			
			String respuesta1 = input();
			
			System.out.println();
			
			if (respuesta1.toLowerCase().equals("si")) {
				
				System.out.print("Escoja el tiquete ingresando " 
				+ "el número de reserva: ");
				
				String numeroReserva = input();
				
				System.out.println();
				
				if (pasajero.buscarTiquete(numeroReserva) == null)  {
					System.out.println("No se encontró ningún tiquete con el número de reserva " 
							+ numeroReserva);
					System.out.println();
				} else {
				
					for (Tiquete tiquete : tiquetesVencidos) {
						if (tiquete.getNumeroReserva().equals(numeroReserva)) {
							System.out.println("Detalles del viaje");
							
							for (int i = 0; i < 92; i++) {
								System.out.print("-");
							}
		
							System.out.println();
							
							System.out.println("    FECHA          ORIGEN          DESTINO" 
									+ "         HORA DE SALIDA     ID      PLACA BUS");
		
							for (int i = 0; i < 92; i++) {
								System.out.print("-");
							}
							
							System.out.println();
							
							System.out.println(tiquete.getViaje());
							
							System.out.println();
							
							System.out.println("Detalles del tiquete");
							
							for (int i = 0; i < 93; i++) {
								System.out.print("-");
							}
		
							System.out.println();
		
							System.out.println("    NUMERO DE RESERVA     NOMBRE        ASIENTO" 
							+ "             FECHA DEL VIAJE      ID VIAJE     ");
							
							for (int i = 0; i < 93; i++) {
								System.out.print("-");
							}
							
							System.out.println();
							
							System.out.println(tiquete);
							
							System.out.println();
							
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
							
							System.out.print("Ingrese el número de la operación: ");
							
							String respuesta2 = input();
							
							System.out.println();
							
							if (respuesta2.equals("1")) {
								pasajero.cancelarTiquete(tiquete);
								
								System.out.println("Tiquete cancelado " 
								+ "exitosamente");
								
								System.out.println();
							} else {
								System.out.println("¿Qué desea hacer?");
								
								System.out.println();
								
								System.out.println("1. Cambiar de asiento");
								
								System.out.println("2. Elegir otro viaje");
								
								System.out.println();
								
								System.out.print("Ingrese el número " 
								+ "de la operación: ");
								
								String respuesta3 = input();
								
								System.out.println();
								
								if(respuesta3.equals("1")) {
									if (!tiquete.getViaje().tieneSillas()) {
										System.out.println("No hay más asientos disponibles");
										
										
										System.out.println();
										
									} else {
										System.out.println("Asientos disponibles:");
			
										System.out.println();
			
										Viaje viaje = tiquete.getViaje();
			
										int[] tiposAsientoFila = viaje.tiposAsiento();
										int fila = 1;
										int indice = 0;
										
										System.out.println(TipoAsiento.PREFERENCIAL);
												
										System.out.println("	       --");
							
										for (Asiento asiento : viaje.listaAsientos()) {
											if (asiento.getNumero().length() == 2) {
												if (asiento.isReservado()) {
													if (asiento.getNumero().contains("D")) {
														if (fila == tiposAsientoFila[indice]) {
															System.out.println(asiento.
																	getNumero() + "   |");
															System.out.println("	       --");
															
															if (indice == 0) {
																System.out.
																	println(TipoAsiento.PREMIUM);
																System.out.println("	       --");
															} else {
																System.out.
																	println(TipoAsiento.ESTANDAR);
																System.out.println("	       --");
															}
															
															if(indice < tiposAsientoFila.
																	length - 1) {
																indice++;
															}
														} else {
															System.out.println("  " + "   |");
														}
														fila++;
													} else {
														System.out.print("    ");
													}
												} else {
													if (asiento.getNumero().contains("D")) {
														if (fila == tiposAsientoFila[indice]) {
															System.out.println(asiento.
																	getNumero() + "   |");
															System.out.println("	       --");
															
															if (indice == 0) {
																System.out.
																	println(TipoAsiento.PREMIUM);
																System.out.println("	       --");
															} else {
																System.out.
																	println(TipoAsiento.ESTANDAR);
																System.out.println("	       --");
															}
															
															if(indice < tiposAsientoFila.
																	length - 1) {
																indice++;
															}
														} else {
															System.out.print(asiento.getNumero() 
															+ "   |");
															System.out.println();
														}
														fila++;
													} else {
													
														System.out.print(asiento.getNumero() 
														+ "  ");
													}
												}
							
											} else {
												if (asiento.isReservado()) {
													if (asiento.getNumero().contains("D")) {
														if (fila == tiposAsientoFila[indice]) {
															System.out.println("   " + "  |");
															System.out.println("	       --");
															
															if (indice == 0) {
																System.out.
																	println(TipoAsiento.PREMIUM);
																System.out.println("	       --");
															} else {
																System.out.
																	println(TipoAsiento.ESTANDAR);
																System.out.println("	       --");
															}
															
															if(indice < tiposAsientoFila.
																	length - 1) {
																indice++;
															}
														} else {
															System.out.print("   " + "  |");
															System.out.println();
														}
														fila++;
													} else {
														System.out.print("    ");
													}
												} else {
													if (asiento.getNumero().contains("D")) {
														if (fila == tiposAsientoFila[indice]) {
															System.out.println(asiento.
																	getNumero() + "  |");
															System.out.println("	       --");
															
															if (indice == 0) {
																System.out.
																	println(TipoAsiento.PREMIUM);
																System.out.println("	       --");
															} else {
																System.out.
																	println(TipoAsiento.ESTANDAR);
																System.out.println("	       --");
															}
															
															if(indice < tiposAsientoFila.
																	length - 1) {
																indice++;
															}
														} else {
															System.out.print(asiento.getNumero() 
															+ "  |");
															System.out.println();
														}
														fila++;
													} else {
														System.out.print(asiento.getNumero() 
														+ " ");
													}
												}
											}
										}
										
										System.out.println("	       --");
			
										System.out.println();
										
										System.out.print("Ingrese el número del asiento: ");
										
										String numeroAsiento = input();
										
										
										
										if (viaje.buscarAsiento(numeroAsiento) == null||
												viaje.buscarAsiento(numeroAsiento).isReservado()) {
											while (true) {
												System.out.println();
												System.out.println("El asiento no " 
																	+ "está disponible");
												System.out.println();
												System.out.print("Ingrese otro número " 
																	+ "de asiento: ");
												
												numeroAsiento = input();
												
												if (!viaje.buscarAsiento(numeroAsiento).
														isReservado() 
														&& viaje.buscarAsiento(numeroAsiento) 
														!= null) {
													System.out.println();
													break;
												}
											}
										}
										
										tiquete.cambiarAsiento(viaje.buscarAsiento(numeroAsiento));
									}
								} else if(respuesta3.equals("2")) {	
									System.out.print("Ingrese el origen: ");
									
									String origen = input();
									
									System.out.println();
	
									System.out.print("Ingrese el destino: ");
									
									String destino = input();
									
									System.out.println();
	
									System.out.println("Estos son los viajes disponibles " + 
														"para la ruta " + origen.toUpperCase() 
														+ " --> " + destino.toUpperCase() + ":");
									
								
									
										for (int i = 0; i < 92; i++) {
											System.out.print("-");
										}
	
										System.out.println();
	
										System.out.println("    FECHA          ORIGEN          DESTINO" 
												+ "         HORA DE SALIDA     ID      PLACA BUS");
	
										for (int i = 0; i < 92; i++) {
											System.out.print("-");
										}
	
										System.out.println();
										for (Viaje viaje : Empresa.buscarViajes(origen.toUpperCase(), 
												destino.toUpperCase())) {
	
										System.out.println(viaje);}
	
										System.out.println();
									
	
									System.out.print("Ingrese el id del viaje: ");
									
									String id = input();
									
									System.out.println();
	
									Viaje viaje = Empresa.buscarViaje(id);
									
									if (viaje.listaAsientos().isEmpty()) {
										System.out.println("No hay más asientos disponibles");
											break;
										}
	
									System.out.println("Asientos disponibles:");
									
									System.out.println();
									
									int[] tiposAsientoFila = viaje.tiposAsiento();
									int fila = 1;
									int indice = 0;
									
									System.out.println(TipoAsiento.PREFERENCIAL);
												
									System.out.println("	       --");
									
									for (Asiento asiento : viaje.listaAsientos()) {
										if (asiento.getNumero().length() == 2) {
											if (asiento.isReservado()) {
												if (asiento.getNumero().contains("D")) {
													if (fila == tiposAsientoFila[indice]) {
														System.out.println(asiento.
																getNumero() + "   |");
														System.out.println("	       --");
														
														if (indice == 0) {
															System.out.
																println(TipoAsiento.PREMIUM);
															System.out.println("	       --");
														} else {
															System.out.
																println(TipoAsiento.ESTANDAR);
															System.out.println("	       --");
														}
														
														if(indice < tiposAsientoFila.length - 1) {
															indice++;
														}
													} else {
														System.out.println("  " + "   |");
													}
													fila++;
												} else {
													System.out.print("    ");
												}
											} else {
												if (asiento.getNumero().contains("D")) {
													if (fila == tiposAsientoFila[indice]) {
														System.out.println(asiento.
																getNumero() + "   |");
														System.out.println("	       --");
														
														if (indice == 0) {
															System.out.
																println(TipoAsiento.PREMIUM);
															System.out.println("	       --");
														} else {
															System.out.
																println(TipoAsiento.ESTANDAR);
															System.out.println("	       --");
														}
														
														if(indice < tiposAsientoFila.length - 1) {
															indice++;
														}
													} else {
														System.out.print(asiento.getNumero() 
														+ "   |");
														System.out.println();
													}
													fila++;
												} else {
												
													System.out.print(asiento.getNumero() 
													+ "  ");
												}
											}
	
										} else {
											if (asiento.isReservado()) {
												if (asiento.getNumero().contains("D")) {
													if (fila == tiposAsientoFila[indice]) {
														System.out.println("   " + "  |");
														System.out.println("	       --");
														
														if (indice == 0) {
															System.out.
																println(TipoAsiento.PREMIUM);
															System.out.println("	       --");
														} else {
															System.out.
																println(TipoAsiento.ESTANDAR);
															System.out.println("	       --");
														}
														
														if(indice < tiposAsientoFila.length - 1) {
															indice++;
														}
													} else {
														System.out.print("   " + "  |");
														System.out.println();
													}
													fila++;
												} else {
													System.out.print("    ");
												}
											} else {
												if (asiento.getNumero().contains("D")) {
													if (fila == tiposAsientoFila[indice]) {
														System.out.println(asiento.
																getNumero() + "  |");
														System.out.println("	       --");
														
														if (indice == 0) {
															System.out.
																println(TipoAsiento.PREMIUM);
															System.out.println("	       --");
														} else {
															System.out.
																println(TipoAsiento.ESTANDAR);
															System.out.println("	       --");
														}
														
														if(indice < tiposAsientoFila.length - 1) {
															indice++;
														}
													} else {
														System.out.print(asiento.getNumero() 
														+ "  |");
														System.out.println();
													}
													fila++;
												} else {
													System.out.print(asiento.getNumero() 
													+ " ");
												}
											}
										}
									}
									
									System.out.println("	       --");
									
									System.out.println();
									
									System.out.print("Ingrese el número del asiento: ");
									
									String numeroAsiento = input();
									
									System.out.println();
									
									if (viaje.buscarAsiento(numeroAsiento) == null||
											viaje.buscarAsiento(numeroAsiento).isReservado() ) {
										while (true) {
											System.out.println();
											System.out.println("El asiento no está disponible");
											System.out.println();
											System.out.print("Ingrese otro número de asiento: ");
											
											numeroAsiento = input();
											
											if (!viaje.buscarAsiento(numeroAsiento).isReservado() 
													&& viaje.buscarAsiento(numeroAsiento) != null) {
												System.out.println();
												break;
											}
										}
									}
									
									tiquete.cambiarViaje(viaje, numeroAsiento);
								}
								
								System.out.println("Tiquete modificado exitosamente");
								
								System.out.println();
					
								for (int i = 0; i < 34; i++) {
									System.out.print("-");
								}
								
								System.out.println();
								
								System.out.println("    Tiquete No." + tiquete.getNumeroReserva());
								
								for (int i = 0; i < 34; i++) {
									System.out.print("-");
								}
					
								System.out.println();
								
								System.out.println("Nombre del pasajero: " + tiquete.getPasajero().getNombre());
								System.out.println("Id del pasajero: " + tiquete.getPasajero().getId());
								System.out.println("Teléfono: " + tiquete.getPasajero().getTelefono());
								System.out.println("Correo: " + tiquete.getPasajero().getCorreo());
								System.out.println("Asiento: " + tiquete.getAsiento());
								System.out.println("Empresa: " + tiquete.getViaje().getEmpresa().getNombre());
								System.out.println("Placa del bus: " + tiquete.getViaje().getBus().getPlaca());
								System.out.println("Id del viaje: " + tiquete.getViaje().getId());
								System.out.println("Fecha y hora: " + tiquete.getViaje().getFecha() 
										+ " " + tiquete.getViaje().getHora());
								System.out.println("Origen: " 
													+ tiquete.getViaje().getTerminalOrigen().
													getUbicacion());
								System.out.println("Destino: " + tiquete.getViaje().
													getTerminalDestino().getUbicacion());
								
								for (int i = 0; i < 34; i++) {
									System.out.print("-");
								}
					
								System.out.println();
								System.out.println();
							}
							break;
						} 
					}
				}
			}
		}
	}
	
	public static void hospedaje() {
		System.out.print("Ingrese el número de identificación del pasajero: ");
		
		String idPasajero = input();
		
		System.out.println();

		Pasajero pasajero = Pasajero.buscarPasajero(idPasajero);

		if (pasajero == null || pasajero.getTiquetes().isEmpty()) {
			System.out.println("El pasajero no ha reservado tiquetes para " 
			+ "ningún viaje");
			System.out.println();
		} else {
			
			System.out.println("Viajes reservados por "+pasajero.getNombre()+" con número de identificación "+pasajero.getId());
			for (int i = 0; i < 107; i++) {
				System.out.print("-");
			}

			System.out.println();

			System.out.println("    FECHA          ORIGEN          DESTINO" 
			+ "         HORA DE SALIDA     ID      PLACA BUS     ASIENTO");

			for (int i = 0; i < 107; i++) {
				System.out.print("-");
			}

			System.out.println();
			
			for (Tiquete tiquete : pasajero.buscarTiquetes("validos")) {
				System.out.print(tiquete.getViaje());
				System.out.println(tiquete.getAsiento());
			}
			
			System.out.println();
			
			System.out.println("¿Desea agregar el servicio de hospedaje a algún viaje? (si/no)");
				
			String respuesta1 = input();
			
			System.out.println();
			
			if (respuesta1.toLowerCase().equals("si")) {
				
				System.out.print("Ingrese el id del viaje: ");
				
				String id = input();
				
				System.out.println();
				
				Viaje viaje = Empresa.buscarViaje(id);
				
				if (viaje == null) {
					System.out.println("No se encontró ningún viaje con el número de id");
					System.out.println();
				} else {
					System.out.println("Hospedajes disponibles en " 
					+ viaje.getTerminalDestino().getUbicacion() + ":");
					
					for (int i = 0; i < 60; i++) {
						System.out.print("-");
					}
		
					System.out.println();
		
					System.out.println("    NOMBRE     CALIFICACION      HABITACIONES DISPONIBLES");
		
					for (int i = 0; i < 60; i++) {
						System.out.print("-");
					}
					
					System.out.println();
		
					for (Hospedaje hospedaje : viaje.hospedajesDisponibles()) {
						System.out.println(hospedaje);
					}
					
					System.out.println();
				
					System.out.print("Ingrese el nombre del hospedaje que desea: ");
					
					String nombre = input();
					
					System.out.println();
					
					Hospedaje hospedaje = viaje.buscarHospedaje(nombre);
					
					if (hospedaje == null) {
						System.out.println("El hospedaje " + nombre + " no está " 
											+ "disponible para este destino");
						System.out.println();
						
					} else {
						System.out.println("Habitaciones disponibles:");
						
						for (int i = 0; i < 60; i++) {
							System.out.print("-");
						}
			
						System.out.println();
			
						System.out.println("    NUMERO DE HABITACIÓN" 
						+ "     RESERVADA     DISPONIBLE EN");
			
						for (int i = 0; i < 60; i++) {
							System.out.print("-");
						}
						
						System.out.println();
						
						for (Habitacion habitacion : hospedaje.getHabitaciones()) {
							System.out.println(habitacion);
						}
						
						System.out.println();
						
						System.out.print("Ingrese el número de la habitación: ");
						
						String numeroHabitacion = input();
						
						System.out.println();
						
						if (hospedaje.buscarHabitacion(numeroHabitacion) == null||
								hospedaje.buscarHabitacion(numeroHabitacion).isReservada() ) {
							while (true) {
								System.out.println();
								System.out.println("La habitación no está disponible");
								System.out.println();
								System.out.print("Ingrese otro número de habitación: ");
								
								numeroHabitacion = input();
								
								if (hospedaje.buscarHabitacion(numeroHabitacion) != null 
										&& !hospedaje.buscarHabitacion(numeroHabitacion).isReservada()) {
									System.out.println();
									break;
								}
							}
						}
						
						final String numeroHabitacion2 = numeroHabitacion;
						
						System.out.println("¿Por cuánto tiempo desea quedarse? " 
						+ "(horas/dias)");
						
						String tiempo = input();
						
						System.out.println();
						
						LocalDateTime fechaViaje = 
								LocalDateTime.of(viaje.getFecha(), viaje.getHora());
						
						String[] arrayTiempo = tiempo.split("[\s]");
						
						String cantidad = arrayTiempo[0];
						
						LocalDateTime fechaReserva = null;
						
						boolean ok = true;
						
						if (tiempo.toLowerCase().contains("hora") 
								|| tiempo.toLowerCase().contains("horas")) {
							fechaReserva = fechaViaje.
									plusHours(Integer.valueOf(cantidad));
						} else if (tiempo.toLowerCase().contains("dia") 
										|| tiempo.toLowerCase().contains("dias")) {
							fechaReserva = fechaViaje.
									plusDays(Integer.valueOf(cantidad));
						} else {
							System.out.println("Tiempo no válido");
							System.out.println();
							ok = false;
						}
						
						if (ok) {
							hospedaje.reservarHabitacion(numeroHabitacion, fechaReserva, 
									pasajero.buscarTiquete(viaje));
							
							Duration duration = 
									Duration.between(LocalDateTime.now(), fechaReserva);
							
							ScheduledExecutorService service = 
									Executors.newScheduledThreadPool(1);
							
							Runnable task = () -> {
								hospedaje.liberarHabitacion(numeroHabitacion2);
							};
							
							service.schedule(task, duration.toMinutes(), 
											TimeUnit.MINUTES);
							
							System.out.println("La habitación "+numeroHabitacion+
									" en el hospedaje "+hospedaje.getNombre()+" ha sido reservada correctamente");
							System.out.println();
						}
					}
				}
			}
		}
	}
	
	public static void administrador() {
		System.out.println("¿Qué desea modificar?");
		System.out.println();
		
		System.out.println("1. Empresas");
		System.out.println("2. Hospedajes");
		System.out.println("3. Terminales");
		System.out.println("4. Viajes");
		System.out.println("5. Personal");
		System.out.println("6. Buses");
		System.out.println("7. Volver");
		
		System.out.println();
		System.out.println("Ingrese el número de la operación: ");

		String respuesta1 = input();
		
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
			
			String empresas = input();
			System.out.println();
			
			switch (empresas) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Empresa");
				System.out.println("2. Conductores a una empresa");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String empresasAgregar = input();
				System.out.println();
				
				if(empresasAgregar.equals("1")) {
					System.out.println("Introduzca el nombre de la empresa: ");
					String nombre=input();
					Empresa empresa=Empresa.buscarEmpresa(nombre);
					if (empresa!=null) {
						System.out.println("La empresa ya existe");
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						break;
					}
					new Empresa(nombre);
					System.out.println();
					System.out.println("Empresa creada exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
				}
				
				if(empresasAgregar.equals("2")) {
					System.out.println("Introduzca el número de identificación del conductor: ");
					String id=input();
					System.out.println();
					System.out.println("Introduzca el nombre de la empresa: ");
					String nombre=input();
					System.out.println();
					
					if (Conductor.buscarConductor(id)==null) {
						System.out.println("No se ha encontrado a ningún conductor con ese ID");
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					
					Empresa empresa=Empresa.buscarEmpresa(nombre);
					
						if (empresa!=null) {
							empresa.añadirConductor(Conductor.buscarConductor(id));
						}
						else {
							System.out.println("No se ha encontrado la empresa "+nombre);
							System.out.println();
							
							System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
							String accion=input();
							System.out.println();
							
							if (accion.toLowerCase().equals("si")) {
								administrador();
							}
							break;
						}
					
					System.out.println("Conductor "+Conductor.buscarConductor(id).getNombre()+
							" asignado correctamente a la empresa "+nombre);
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
				}
				administrador();
			case "2":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Empresa");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String empresasVer = input();
				System.out.println();
				
				if (empresasVer.equals("1")) {
					System.out.println("Lista de empresas actuales");
					for (int i = 0; i < 28; i++) {
						System.out.print("-");
					}

					System.out.println();

					System.out.println("    Nombre de la empresa      ");

					for (int i = 0; i < 28; i++) {
						System.out.print("-");
					}
					
					System.out.println();

					for(Empresa empresa : Empresa.getEmpresas()) {
						System.out.println("          "+empresa.getNombre());
					}
					
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					break;
				}
				administrador();
			case "3":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Empresa");
				System.out.println("2. Conductores de una empresa");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String empresasEliminar = input();
				System.out.println();
				
				if (empresasEliminar.equals("1")) {
					System.out.println("Ingrese el nombre de la empresa a eliminar");
					String nombre=input();
					Empresa empresa=Empresa.buscarEmpresa(nombre);
					if (empresa==null) {
						System.out.println();
						System.out.println("No se ha encontrado a la empresa"+nombre);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					Empresa.eliminarEmpresa(nombre);
					System.out.println();
					System.out.println("Empresa "+nombre+" eliminada exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					break;
				}
				
				if (empresasEliminar.equals("2")) {
					System.out.println("Introduzca el número de identificación del conductor: ");
					String id=input();
					System.out.println();
					System.out.println("Introduzca el nombre de la empresa: ");
					String nombre=input();
					System.out.println();
					
					if (Conductor.buscarConductor(id)==null) {
						System.out.println("No se ha encontrado a ningún conductor con ese ID");
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					
					Empresa empresa=Empresa.buscarEmpresa(nombre);						
					if (empresa!=null) {
							empresa.eliminarConductor(Conductor.buscarConductor(id));
							System.out.println("Conductor con id "+id+" eliminado correctamente de la empresa "+empresa.getNombre());
							System.out.println();
							System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
							String accion=input();
							System.out.println();
							
							if (accion.toLowerCase().equals("si")) {
								administrador();
							}
							break;
						}
						else {
							System.out.println("No se ha encontrado la empresa "+nombre);
							System.out.println();
							
							System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
							String accion=input();
							System.out.println();
							
							if (accion.toLowerCase().equals("si")) {
								administrador();
							}
							
							break;
						}
					}
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
			
			String hospedajes = input();
			System.out.println();
			
			
			switch (hospedajes) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Hospedaje");
				System.out.println("2. habitaciones a un hospedaje");
				System.out.println("3. Calificación a un hospedaje");
				System.out.println("4. Volver");
				
				System.out.println();
				
				String hospedajesAgregar = input();
				System.out.println();
				
				if (hospedajesAgregar.equals("1")) {
					System.out.println("Ingrese el nombre del hospedaje");
					String nombre=input();
					System.out.println();
					System.out.println("Ingrese la ubicación del hospedaje");
					String ubicacion=input();
					System.out.println();
					
					if (!(Hospedaje.buscarHospedaje(nombre, ubicacion)==null)) {
						System.out.println("El hospedaje ya existe");
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					new Hospedaje(nombre,ubicacion);
					System.out.println("Hospedaje creado exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					break;
				}
				
				if (hospedajesAgregar.equals("2")) {
					System.out.println("Ingrese el nombre del hospedaje");
					String nombre=input();
					System.out.println();
					System.out.println("Ingrese la ubicación del hospedaje");
					String ubicacion=input();
					System.out.println();
					
					
					Hospedaje hospedaje=Hospedaje.buscarHospedaje(nombre, ubicacion);
					
					if (hospedaje==null) {
						System.out.println("El hospedaje no existe");
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					System.out.println("Ingrese la cantidad de pisos (Sólo números enteros)");
					String pisos=input();
					System.out.println();
					System.out.println("Ingrese las habitaciones por piso (Sólo números enteros)");
					String habitaciones=input();
					System.out.println();
					
					hospedaje.crearHabitaciones(Integer.parseInt(pisos), Integer.parseInt(habitaciones));
					System.out.println("Habitaciones agregadas correctamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					break;
					
				}
				
				if (hospedajesAgregar.equals("3")) {
					System.out.println("Ingrese el nombre del hospedaje");
					String nombre=input();
					System.out.println();
					System.out.println("Ingrese la ubicación del hospedaje");
					String ubicacion=input();
					System.out.println();
					
					Hospedaje hospedaje=Hospedaje.buscarHospedaje(nombre, ubicacion);
					
					if (hospedaje==null) {
						System.out.println("El hospedaje no existe");
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						break;
					}
					
					System.out.println("Agregue su calificación del hospedaje del 1 al 5 (Sólo números enteros)");
					String calificar=input();
					System.out.println();
					
					hospedaje.setCalificacion(calificar);
					
					System.out.println("Calificación agregada correctamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					break;
					
				}
				administrador();
			case "2":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Hospedajes");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String hospedajesVer = input();
				System.out.println();
				
				if (hospedajesVer.equals("1")) {
					System.out.println("Lista de hospedajes actuales");
					for (int i = 0; i < 56; i++) {
						System.out.print("-");
					}

					System.out.println();

					System.out.println("    Nombre         Ubicación         Calificación     ");

					for (int i = 0; i < 56; i++) {
						System.out.print("-");
					}
					
					System.out.println();

					for(Hospedaje hospedaje : Hospedaje.getHospedajes()) {
						if (hospedaje!=null) {
						System.out.println("    "+hospedaje.getNombre()+"             "+
					hospedaje.getUbicacion()+"            "+ hospedaje.getCalificacion());
					}
					
					System.out.println();}
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					break;
					
				
				}
				administrador();
			case "3":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Hospedaje");
				System.out.println("2. Habitaciones de un hospedaje");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String hospedajesEliminar = input();
				System.out.println();
				
				if (hospedajesEliminar.equals("1")) {
					System.out.println("Ingrese el nombre del hospedaje a eliminar");
					String nombre=input();
					System.out.println();
					System.out.println("Ingrese la ubicación del hospedaje a eliminar");
					String ubicacion=input();
					System.out.println();
					Hospedaje hospedaje=Hospedaje.buscarHospedaje(nombre,ubicacion);
					if (hospedaje==null) {
						System.out.println("No se ha encontrado el hospedaje "+nombre);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					Hospedaje.eliminarHospedaje(nombre, ubicacion);
					System.out.println("Hospedaje "+nombre+" eliminado exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					break;
					
				}
				
				if (hospedajesEliminar.equals("2")) {
					System.out.println("Ingrese el nombre del hospedaje a eliminar");
					String nombre=input();
					System.out.println();
					System.out.println("Ingrese la ubicación del hospedaje a eliminar");
					String ubicacion=input();
					System.out.println();
					
					Hospedaje hospedaje=Hospedaje.buscarHospedaje(nombre,ubicacion);
					if (hospedaje==null) {
						System.out.println("No se ha encontrado el hospedaje "+nombre);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					
					System.out.println("Ingrese el número de la habitación");
					String habitacion=input();
					System.out.println();
					
					if (hospedaje.buscarHabitacion(habitacion)==null) {
						System.out.println("No se ha encontrado la habitación "+habitacion);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					
					hospedaje.eliminarHabitacion(habitacion);
					System.out.println("Habitación "+habitacion+" eliminada exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					break;
				}

				break;
			case "4":
				administrador();
			}
			
			break;
		case "3":
			System.out.println("Ingrese una opción");
			
			System.out.println();
			
			System.out.println("1. Agregar");
			System.out.println("2. Ver");
			System.out.println("3. Eliminar");
			System.out.println("4. Volver");
			
			System.out.println();
			
			String terminales = input();
			System.out.println();
			
			
			switch(terminales) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Terminal");
				System.out.println("2. Empresa a una terminal");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String terminalesAgregar = input();
				System.out.println();
				
				if (terminalesAgregar.equals("1")) {
					System.out.println("Ingrese el nombre de la terminal");
					String nombre=input();
					
					System.out.println();
					
					System.out.println("Ingrese la ubicación de la terminal");
					String ubicacion=input();
					
					System.out.println();
					
					Terminal terminal=Terminal.buscarTerminal(nombre,ubicacion);
					if (terminal!=null) {
						System.out.println("La terminal ya existe");
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					
					new Terminal(nombre,ubicacion);
					System.out.println("Terminal creada exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
				}
				
				if (terminalesAgregar.equals("2")) {
					System.out.println("Ingrese el nombre de la empresa");
					String nombreEmp=input();
					
					System.out.println();
					
					System.out.println("Ingrese el nombre de la terminal");
					String nombreTer=input();
					
					System.out.println();
					
					System.out.println("Ingrese la ubicación de la terminal");
					String ubicacion=input();
					
					System.out.println();
					
					Terminal terminal=Terminal.buscarTerminal(nombreTer,ubicacion);
					Empresa empresa=Empresa.buscarEmpresa(nombreEmp);
					
					if (terminal==null || empresa==null) {
						System.out.println("No se ha encontrado la terminal "+nombreTer+ " ó la empresa "+nombreEmp);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					
					terminal.agregarEmpresa(empresa);
					System.out.println("Empresa "+nombreEmp+" ha sido vinculada a la terminal "+nombreTer);
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
					
				}
				administrador();
			
			case "2":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Terminales");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String terminalesVer = input();
				
				System.out.println();
				
				if (terminalesVer.equals("1")) {
					System.out.println("Lista de terminales");
					for (int i = 0; i < 35; i++) {
						System.out.print("-");
					}

					System.out.println();

					System.out.println("    Nombre           Ubicación     ");

					for (int i = 0; i < 35; i++) {
						System.out.print("-");
					}
					
					System.out.println();

					for(Terminal terminal : Terminal.getTerminales()) {
						if (terminal!=null) {
						System.out.println("    "+terminal.getNombre()+"             "+
					terminal.getUbicacion()+"            ");
					}
					
					System.out.println();}
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					 break;
				}
				administrador();
			case "3":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Terminal");
				System.out.println("2. Empresa de una terminal");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String terminalesEliminar = input();
				System.out.println();
				
				if (terminalesEliminar.equals("1")) {
					System.out.println("Ingrese el nombre de la terminal a eliminar");
					String nombre=input();
					System.out.println();
					System.out.println("Ingrese la ubicación de la terminal a eliminar");
					String ubicacion=input();
					System.out.println();
					Terminal terminal=Terminal.buscarTerminal(nombre,ubicacion);
					if (terminal==null) {
						System.out.println("No se ha encontrado la terminal "+nombre+" en "+ubicacion);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					Terminal.eliminarTerminal(nombre, ubicacion);
					System.out.println("Terminal "+nombre+" en "+ubicacion+" ha sido eliminada exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
				}
				if (terminalesEliminar.equals("2")) {
					System.out.println("Ingrese el nombre de la terminal");
					String nombreTer=input();
					System.out.println();
					System.out.println("Ingrese la ubicación de la terminal");
					String ubicacion=input();
					System.out.println();
					
					Terminal terminal=Terminal.buscarTerminal(nombreTer,ubicacion);
					if (terminal==null) {
						System.out.println("No se ha encontrado la terminal "+nombreTer+" en "+ubicacion);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					
					System.out.println("Ingrese el nombre de la Empresa");
					String nombreEmp=input();
					System.out.println();
					
					if (Empresa.buscarEmpresa(nombreEmp)==null) {
						System.out.println("No se ha encontrado la empresa "+nombreEmp);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					
					terminal.eliminarEmpresa(nombreEmp);
					System.out.println("Empresa "+nombreEmp+" eliminada exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
				}
				
				administrador();
			case "4":
				administrador();
			}
			break;
		case "4":
			System.out.println("Ingrese una opción");
			
			System.out.println();
			
			System.out.println("1. Agregar");
			System.out.println("2. Modificar");
			System.out.println("3. Ver");
			System.out.println("4. Eliminar");
			System.out.println("5. Volver");
			
			System.out.println();
			
			String viaje = input();
			System.out.println();
			
			switch (viaje) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Viaje");
				System.out.println("2. Tiquete");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String viajesAgregar = input();
				System.out.println();
				
				if (viajesAgregar.equals("1")) {
					
					
					System.out.println("Ingrese el nombre de la terminal de origen");
					String nombreOrigen=input();
					
					System.out.println();
					
					System.out.println("Ingrese la ubicación de la terminal de origen");
					String ubicacionOrigen=input();
					
					System.out.println();
					
					System.out.println("Ingrese el nombre de la terminal de destino");
					String nombreDestino=input();
					
					System.out.println();
					
					System.out.println("Ingrese la ubicación de la terminal de destino");
					String ubicacionDestino=input();
					
					System.out.println();
					
					System.out.println("Ingrese el nombre de la empresa");
					String nombreEmp=input();
					
					System.out.println();
					
					System.out.println("Ingrese la fecha en formato dd-mm-aaaa");
					String fecha=input();
					
					System.out.println();
					
					System.out.println("Ingrese la hora en formato HH:mm:ss");
					String hora=input();
					
					System.out.println();
					
					
					System.out.println("Ingrese la placa del bus");
					String placa=input();
					
					System.out.println();

					System.out.println("Ingrese el nombre del conductor");
					String nombreConductor=input();
					
					
					System.out.println();
					
					System.out.println("Ingrese el id del conductor");
					String idConductor=input();
					
					
					System.out.println();
					
					System.out.println("Ingrese la cantidad de filas de asientos preferenciales");
					String preferencial=input();
					int preferencialmod=Integer.parseInt(preferencial);
					
					System.out.println();
					
					System.out.println("Ingrese la cantidad de filas de asientos premium");
					String premium=input();
					int premiummod=Integer.parseInt(premium);
					
					System.out.println();
					
					System.out.println("Ingrese la cantidad de filas de asientos standart");
					String standart=input();
					int standartmod=Integer.parseInt(standart);
					
					System.out.println();
					
					
					Terminal terminalOrigen=Terminal.buscarTerminal(nombreOrigen,ubicacionOrigen);
					
					if (terminalOrigen==null) {
						terminalOrigen=new Terminal(nombreOrigen,ubicacionOrigen);
					}
					Terminal terminalDestino=Terminal.buscarTerminal(nombreDestino,ubicacionDestino);
					if (terminalDestino==null) {
						terminalDestino=new Terminal(nombreDestino,ubicacionDestino);
					}
					
					Empresa empresa=Empresa.buscarEmpresa(nombreEmp);
					if (empresa==null) {
						empresa=new Empresa(nombreEmp);
					}
					
					Bus bus=Bus.buscarBus(placa);
					if (bus==null) {
						bus=new Bus(preferencialmod+premiummod+standartmod, new int[]{preferencialmod, premiummod+preferencialmod});
						
					}
					
					Conductor conductor=Conductor.buscarConductor(nombreConductor,idConductor);
					if (conductor==null) {
						conductor=new Conductor(nombreConductor,idConductor);
					}
					
					DateTimeFormatter formateoFecha = 
							DateTimeFormatter.ofPattern("dd-MM-yyyy");

					LocalDate fechamod = LocalDate.parse(fecha, formateoFecha);
					
					
					
					DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("HH:mm:ss");
					LocalTime horamod = LocalTime.parse(hora, formatoEntrada);
					
					
					
					empresa.getViajes().add(new Viaje(terminalOrigen,terminalDestino,empresa,fechamod,horamod,conductor,bus));
					
					System.out.println("Viaje creado exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
				}
				
				if (viajesAgregar.equals("2")) {
					
					
					System.out.println("Para crear el pasajero: ");
					System.out.println("Ingrese el nombre del pasajero");
					String nombrePasajero=input();
					
					System.out.println();
					
					System.out.println("Ingrese el id del pasajero");
					String idPasajero=input();
					
					System.out.println();
					
					System.out.println("Ingrese el teléfono del pasajero");
					String telefonoPasajero=input();
					
					System.out.println();
					
					System.out.println("Ingrese el correo del pasajero");
					String correoPasajero=input();
					
					System.out.println();
					
					
					
					System.out.println("Para crear el viaje: ");
					System.out.println("Ingrese el nombre de la terminal de origen");
					String nombreOrigen=input();
					
					System.out.println();
					
					System.out.println("Ingrese la ubicación de la terminal de origen");
					String ubicacionOrigen=input();
					
					System.out.println();
					
					System.out.println("Ingrese el nombre de la terminal de destino");
					String nombreDestino=input();
					
					System.out.println();
					
					System.out.println("Ingrese la ubicación de la terminal de destino");
					String ubicacionDestino=input();
					
					System.out.println();
					
					System.out.println("Ingrese el nombre de la empresa");
					String nombreEmp=input();
					
					System.out.println();
					
					System.out.println("Ingrese la fecha en formato dd-mm-aaaa");
					String fecha=input();
					
					System.out.println();
					
					System.out.println("Ingrese la hora en formato HH:mm:ss");
					String hora=input();
					
					System.out.println();
					
					
					System.out.println("Ingrese la placa del bus");
					String placa=input();
					
					System.out.println();

					System.out.println("Ingrese el nombre del conductor");
					String nombreConductor=input();
					
					
					System.out.println();
					
					System.out.println("Ingrese el id del conductor");
					String idConductor=input();
					
					
					System.out.println();
					
					System.out.println("Ingrese la cantidad de filas de asientos preferenciales");
					String preferencial=input();
					int preferencialmod=Integer.parseInt(preferencial);
					
					System.out.println();
					
					System.out.println("Ingrese la cantidad de filas de asientos premium");
					String premium=input();
					int premiummod=Integer.parseInt(premium);
					
					System.out.println();
					
					System.out.println("Ingrese la cantidad de filas de asientos standart");
					String standart=input();
					int standartmod=Integer.parseInt(standart);
					
					System.out.println();
					
					
					
					
					Terminal terminalOrigen=Terminal.buscarTerminal(nombreOrigen,ubicacionOrigen);
					
					if (terminalOrigen==null) {
						terminalOrigen=new Terminal(nombreOrigen,ubicacionOrigen);
					}
					Terminal terminalDestino=Terminal.buscarTerminal(nombreDestino,ubicacionDestino);
					if (terminalDestino==null) {
						terminalDestino=new Terminal(nombreDestino,ubicacionDestino);
					}
					
					Empresa empresa=Empresa.buscarEmpresa(nombreEmp);
					if (empresa==null) {
						empresa=new Empresa(nombreEmp);
					}
					
					Bus bus=Bus.buscarBus(placa);
					if (bus==null) {
						bus=new Bus(preferencialmod+premiummod+standartmod, new int[]{preferencialmod, premiummod+preferencialmod});
						bus.setPlaca(Vehiculo.generarPlaca());
					}
					
					Conductor conductor=Conductor.buscarConductor(nombreConductor,idConductor);
					if (conductor==null) {
						conductor=new Conductor(nombreConductor,idConductor);
					}
					
					Pasajero pasajero=Pasajero.buscarPasajero(idPasajero);
					if (pasajero==null) {
						pasajero=new Pasajero(nombrePasajero,idPasajero,telefonoPasajero,correoPasajero);
					}
					
					DateTimeFormatter formateoFecha = 
							DateTimeFormatter.ofPattern("dd-MM-yyyy");

					LocalDate fechamod = LocalDate.parse(fecha, formateoFecha);
					
					
					
					DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("HH:mm:ss");
					LocalTime horamod = LocalTime.parse(hora, formatoEntrada);
					
					        
					
					Viaje viajenuevo=Viaje.buscarViaje(terminalOrigen,terminalDestino,empresa,fechamod,horamod,conductor,bus);
					if (viajenuevo==null) {
						viajenuevo=new Viaje(terminalOrigen,terminalDestino,empresa,fechamod,horamod,conductor,bus);
					}
					empresa.getViajes().add(viajenuevo);					
					
					System.out.println("Para crear el asiento: ");
					System.out.println("Ingrese el número del Asiento");
					String numeroAsiento=input();
					
					
					System.out.println();
					
					System.out.println("Ingrese el tipo del Asiento (ESTANDAR,PREFERENCIAL,PREMIUM");
					String tipoAsiento=input();
					tipoAsiento=tipoAsiento.toUpperCase();
					
					
					System.out.println();
					
					Asiento asiento=Asiento.buscarAsiento(numeroAsiento,tipoAsiento);
					if (asiento==null) {
						asiento=new Asiento(numeroAsiento,TipoAsiento.valueOf(tipoAsiento));
					}
					
					System.out.println("Para crear el hospedaje: ");
					System.out.println("Ingrese el nombre del hospedaje");
					String nombreHospedaje=input();
					
					
					System.out.println();
					System.out.println("Ingrese la ubicación del hospedaje");
					String ubicacionHospedaje=input();
					
					
					System.out.println();
					
					Hospedaje hospedaje=Hospedaje.buscarHospedaje(nombreHospedaje, ubicacionHospedaje);
					if (hospedaje==null) {
						hospedaje=new Hospedaje(nombreHospedaje,ubicacionHospedaje);
					}
					
					Tiquete tiquete=Tiquete.buscarTiquete(pasajero,viajenuevo,asiento,hospedaje);
					if (tiquete!=null) {
						System.out.println("El tiquete ya existe");
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					
					pasajero.agregarTiquete(new Tiquete(pasajero,viajenuevo,asiento,hospedaje));
					System.out.println("Tiquete creado correctamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
				}
				administrador();
				
			case "2":
				System.out.println("¿Qué desea modificar?");
				
				System.out.println();
				
				System.out.println("1. Viaje");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String viajesModificar = input();
				System.out.println();
				
				if (viajesModificar.equals("1")) {
					System.out.println("Ingrese el id del viaje que desea modificar");
					String id=input();
					Viaje viajeactual=Viaje.buscarViaje(id);
					
					if (viajeactual==null) {
						System.out.println("No se encontró a ningún viaje con id "+id);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						break;
					}
					
					System.out.println();
					
					System.out.println("¿Qué desea modificar (elija una opción)");
					System.out.println();
					System.out.println("1. Terminal de Origen");
					System.out.println("2. Terminal de Destino");
					System.out.println("3. Empresa");
					System.out.println("4. Fecha de Salida");
					System.out.println("5. Hora de Salida");
					System.out.println("6. Bus");
					System.out.println("7. Conductor");
					System.out.println();
					
					String opcion=input();
					
					System.out.println();
					
					switch(opcion) {
					case "1":
						System.out.println("Ingrese el nombre de la terminal de origen");
						String nombreOrigen=input();
						
						System.out.println();
						
						System.out.println("Ingrese la ubicación de la terminal de origen");
						String ubicacionOrigen=input();
						
						System.out.println();
						
						viajeactual.getTerminalOrigen().setNombre(nombreOrigen);
						viajeactual.getTerminalOrigen().setUbicacion(ubicacionOrigen);
						break;
						
					case "2":
						System.out.println("Ingrese el nombre de la terminal de Destino");
						String nombreDestino=input();
						
						System.out.println();
						
						System.out.println("Ingrese la ubicación de la terminal de Destino");
						String ubicacionDestino=input();
						
						System.out.println();
						
						viajeactual.getTerminalDestino().setNombre(nombreDestino);
						viajeactual.getTerminalDestino().setUbicacion(ubicacionDestino);
						break;
						
					case "3":
						System.out.println("Ingrese el nombre de la empresa");
						String nombre=input();
						
						System.out.println();
						
						viajeactual.getEmpresa().setNombre(nombre);
						break;
					
					case "4":
						System.out.println("Ingrese la fecha en formato dd-mm-aaaa");
						String fecha=input();
						
						System.out.println();
						
						DateTimeFormatter formateoFecha = 
								DateTimeFormatter.ofPattern("dd-MM-yyyy");

						LocalDate fechamod = LocalDate.parse(fecha, formateoFecha);
						
						viajeactual.setFecha(fechamod);
						break;
						
					case "5":
						System.out.println("Ingrese la hora en formato HH:mm:ss");
						String hora=input();
						
						System.out.println();
						
						DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("HH:mm:ss");
						LocalTime horamod = LocalTime.parse(hora, formatoEntrada);
						
						viajeactual.setHora(horamod);
						break;
						
					case "6":
						System.out.println("Ingrese la placa del bus");
						String placa=input();
						
						System.out.println();
						
						System.out.println("Ingrese la cantidad de filas de asientos preferenciales");
						String preferencial=input();
						int preferencialmod=Integer.parseInt(preferencial);
						
						System.out.println();
						
						System.out.println("Ingrese la cantidad de filas de asientos premium");
						String premium=input();
						int premiummod=Integer.parseInt(premium);
						
						System.out.println();
						
						System.out.println("Ingrese la cantidad de filas de asientos standart");
						String standart=input();
						int standartmod=Integer.parseInt(standart);
						
						System.out.println();
						
						viajeactual.getBus().setPlaca(placa);
						viajeactual.getBus().crearAsientos(standartmod+premiummod+preferencialmod);
						break;
						
					case "7":
						
						System.out.println("Ingrese el nombre del conductor");
						String nombreConductor=input();
						
						
						System.out.println();
						
						System.out.println("Ingrese el id del conductor");
						String idConductor=input();
						
						
						System.out.println();
						
						viajeactual.getBus().getConductor().setNombre(nombreConductor);
						viajeactual.getBus().getConductor().setId(idConductor);
						break;
					}
					
					System.out.println("Datos modificados correctamente");
					System.out.println();

					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;

				}
				administrador();
			case "3":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Viajes");
				System.out.println("2. Tiquetes");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String viajesVer = input();
				System.out.println();
				
				if (viajesVer.equals("1")) {
					System.out.println("Viajes disponibles por empresa");
					for	(Empresa empresa : Empresa.getEmpresas()) {
						System.out.println("Viajes de la empresa " 
						+ empresa.getNombre());
						
						for (int i = 0; i < 92; i++) {
							System.out.print("-");
						}


						System.out.println();

						System.out.println("    FECHA          ORIGEN          DESTINO" 
						+ "         HORA DE SALIDA     ID       PLACA BUS");
						

						for (int i = 0; i < 92; i++) {
							System.out.print("-");
						}

						System.out.println();

						for(Viaje viajes : empresa.getViajes()) {
							System.out.println(viajes);
							
						}
						
						System.out.println();
						
					}
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
				}
				
				if (viajesVer.equals("2")) {
					System.out.println("Tiquetes disponibles por persona");
					for	(Pasajero pasajero : Pasajero.getPasajeros()) {
						System.out.println("Tiquetes del pasajero " 
						+ pasajero.getNombre()+ " con id "+pasajero.getId());
						
						for (int i = 0; i < 92; i++) {
							System.out.print("-");
						}


						System.out.println();

						System.out.println("    NUMERO DE RESERVA     NOMBRE        ASIENTO" 
								+ "             FECHA DEL VIAJE      ID VIAJE     ");
						

						for (int i = 0; i < 92; i++) {
							System.out.print("-");
						}

						System.out.println();

						for(Tiquete tiquete : pasajero.getTiquetes()) {
							System.out.println(tiquete);
							
						}
						
						System.out.println();
					}
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
				}
			
				administrador();
			case "4":
				System.out.println("¿Qué desea eliminar del sistema?");
				
				System.out.println();
				
				System.out.println("1. Hospedajes");
				System.out.println("2. Pasajeros");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String viajesEliminar = input();
				System.out.println();
				
			
				if (viajesEliminar.equals("1")) {
					System.out.println("Ingrese el nombre del hospedaje a eliminar");
					String nombre=input();
					System.out.println();
					
					System.out.println("Ingrese la ubicación del hospedaje a eliminar");
					String ubicacion=input();
					System.out.println();
					
					Hospedaje hospedaje=Hospedaje.buscarHospedaje(nombre, ubicacion);
					if (hospedaje==null) {
						System.out.println("No se ha encontrado el hospedaje con nombre "+nombre+ " y ubicación "+ubicacion);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					Hospedaje.eliminarHospedaje(nombre,ubicacion);
					System.out.println("Hospedaje con nombre "+nombre+" y ubicación "+ubicacion+" ha sido eliminado exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					 break;
				}
				if (viajesEliminar.equals("2")) {
					System.out.println("Ingrese el nombre del pasajero a eliminar");
					String nombre=input();
					System.out.println();
					
					System.out.println("Ingrese el id del pasajero a eliminar");
					String id=input();
					System.out.println();
					
					Pasajero pasajero=Pasajero.buscarPasajero(nombre, id);
					if (pasajero==null) {
						System.out.println("No se ha encontrado el pasajero con nombre "+nombre+ " e id "+id);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					Pasajero.eliminarPasajero(nombre,id);
					System.out.println("Pasajero con nombre "+nombre+" e id "+id+" ha sido eliminado exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					break;
				}

				
				
				break;
			case "5":
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
			
			String personal = input();
			System.out.println();
			
			switch (personal) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Pasajero");
				System.out.println("2. Conductor");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String personalAgregar = input();
				System.out.println();
				
				if (personalAgregar.equals("1")) {
					System.out.println("Ingrese el nombre del pasajero");
					String nombrePasajero=input();
					
					System.out.println();
					
					System.out.println("Ingrese el id del pasajero");
					String idPasajero=input();
					
					System.out.println();
					
					System.out.println("Ingrese el teléfono del pasajero");
					String telefonoPasajero=input();
					
					System.out.println();
					
					System.out.println("Ingrese el correo del pasajero");
					String correoPasajero=input();
					
					System.out.println();
					
					Pasajero pasajero=Pasajero.buscarPasajero(idPasajero);
					if (pasajero!=null) {
						System.out.println("El pasajero ya existe");
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}else {
					new Pasajero(nombrePasajero,idPasajero,telefonoPasajero,correoPasajero);
					}
					
					System.out.println("Pasajero creado correctamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					break;
				}
				
				if (personalAgregar.equals("2")) {
					System.out.println("Ingrese el nombre del conductor");
					String nombre=input();
					
					System.out.println();
					
					System.out.println("Ingrese el id del conductor");
					String id=input();
					
					System.out.println();
					
					
					Conductor conductor=Conductor.buscarConductor(nombre,id);
					if (conductor!=null) {
						System.out.println("El conductor ya existe");
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}else {
					new Conductor(nombre,id);
					}
					
					System.out.println("Conductor creado correctamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
				}
				administrador();
			case "2":
				System.out.println("¿Qué desea modificar?");
				
				System.out.println();
				
				System.out.println("1. Pasajero");
				System.out.println("2. Conductor");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String personalmodificar = input();
				System.out.println();
				
				if (personalmodificar.equals("1")) {
					System.out.println("Ingrese el id del pasajero");
					String id=input();
					System.out.println();
					
					Pasajero pasajero=Pasajero.buscarPasajero(id);
					if (pasajero==null) {
						System.out.println("No se encontrado a ningún pasajero con el id "+id);
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					System.out.println("Ingrese el nombre del pasajero");
					String nombre=input();
					System.out.println();
					System.out.println("Ingrese el telefono del pasajero");
					String telefono=input();
					System.out.println();
					System.out.println("Ingrese el correo del pasajero");
					String correo=input();
					System.out.println();
					
					pasajero.setNombre(nombre);
					pasajero.setCorreo(correo);
					pasajero.setTelefono(telefono);
					
					System.out.println("Información modificada correctamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
				}
				
				if (personalmodificar.equals("2")) {
					System.out.println("Ingrese el id del conductor");
					String id=input();
					System.out.println();
					
					Conductor conductor=Conductor.buscarConductor(id);
					if (conductor==null) {
						System.out.println("No se encontrado a ningún conductor con el id "+id);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					System.out.println("Ingrese el nombre del conductor");
					String nombre=input();
					System.out.println();

					
					conductor.setNombre(nombre);

					
					System.out.println("Información modificada correctamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
				}
				administrador();
			case "3":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Pasajeros");
				System.out.println("2. Conductores");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String personalVer = input();
				System.out.println();
				
				if (personalVer.equals("1")) {
					System.out.println("Lista de pasajeros existentes");
					
					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}
					System.out.println();
					
					System.out.println("     NOMBRE          ID         TELEFONO" 
							+ "       CORREO     ");
					

					for (int i = 0; i < 92; i++) {
						System.out.print("-");
					}

					System.out.println();
					
					for	(Pasajero pasajero : Pasajero.getPasajeros()) {
						

							System.out.println(pasajero);}
							
						
						
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						break;
					
				}
				if (personalVer.equals("2")) {
					System.out.println("Lista de condcutores existentes");
					
					for (int i = 0; i < 35; i++) {
						System.out.print("-");
					}
					System.out.println();
					
					System.out.println("     NOMBRE          ID        ");
					

					for (int i = 0; i < 35; i++) {
						System.out.print("-");
					}

					System.out.println();
					
					for	(Conductor conductor:Conductor.getConductores()) {
						

							System.out.println("     "+conductor.getNombre()+"            "+conductor.getId()+"          ");}
							
						
						
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					
				}
				
				administrador();
			case "4":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Pasajero");
				System.out.println("2. Conductor");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String personalEliminar = input();
				System.out.println();
				
				if (personalEliminar.equals("1")) {
					
					System.out.println("Ingrese el id del pasajero a eliminar");
					String id=input();
					System.out.println();
					
					Pasajero pasajero=Pasajero.buscarPasajero(id);
					if (pasajero==null) {
						System.out.println("No se ha encontrado el pasajero con id "+id);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					Pasajero.eliminarPasajero(id);
					System.out.println("Pasajero con id "+id+" ha sido eliminado exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					
					break;
				}
				
				if (personalEliminar.equals("2")) {
					
					System.out.println("Ingrese el id del conductor a eliminar");
					String id=input();
					System.out.println();
					
					Conductor conductor=Conductor.buscarConductor(id);
					if (conductor==null) {
						System.out.println("No se ha encontrado al conductor con id "+id);
						System.out.println();
						
						System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
						String accion=input();
						System.out.println();
						
						if (accion.toLowerCase().equals("si")) {
							administrador();
						}
						
						break;
					}
					Conductor.eliminarConductor(id);
					System.out.println("Conductor con id "+id+" ha sido eliminado exitosamente");
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion=input();
					System.out.println();
					
					if (accion.toLowerCase().equals("si")) {
						administrador();
					}
					break;
				}
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
			
			String vehiculos = input();
			System.out.println();
			
			switch (vehiculos) {
			case "1":
				
				System.out.println("Ingrese la cantidad de filas de asientos preferenciales");
				String preferencial=input();
				int preferencialmod=Integer.parseInt(preferencial);
				
				System.out.println();
				
				System.out.println("Ingrese la cantidad de filas de asientos premium");
				String premium=input();
				int premiummod=Integer.parseInt(premium);
				
				System.out.println();
				
				System.out.println("Ingrese la cantidad de filas de asientos standart");
				String standart=input();
				int standartmod=Integer.parseInt(standart);
				
				System.out.println();
				
				Bus bus1 =new Bus(preferencialmod+premiummod+standartmod,new int[] {preferencialmod,premiummod+preferencialmod});
				
				System.out.println("Bus creado correctamente");
				System.out.println("y su placa es: "+bus1.getPlaca());
				System.out.println();
				
				System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
				String accion=input();
				System.out.println();
				
				if (accion.toLowerCase().equals("si")) {
					administrador();
				}
				break;
				
			case "2":
				System.out.println("Ingrese la placa del bus");
				String placa1=input();
				
				System.out.println();
				
				Bus bus=Bus.buscarBus(placa1);
				if (bus==null) {
					System.out.println("No se ha encontrado al bus con placa "+placa1);
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion1=input();
					System.out.println();
					
					if (accion1.toLowerCase().equals("si")) {
						administrador();
					}
					break;
				}
				else {
				
				System.out.println("Ingrese la cantidad de asientos preferenciales");
				String preferencial1=input();
				int preferencialmod1=Integer.parseInt(preferencial1);
				
				System.out.println();
				
				System.out.println("Ingrese la cantidad de asientos premium");
				String premium1=input();
				int premiummod1=Integer.parseInt(premium1);
				
				System.out.println();
				
				System.out.println("Ingrese la cantidad de asientos standart");
				String standart1=input();
				int standartmod1=Integer.parseInt(standart1);
				
				System.out.println();
				
				bus.crearAsientos(standartmod1+premiummod1+preferencialmod1);
				}
				
				System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
				String accion1=input();
				System.out.println();
				
				if (accion1.toLowerCase().equals("si")) {
					administrador();
				}
				
				break;
			case "3":
				System.out.println("Lista de buses existentes");
				
				for (int i = 0; i < 75; i++) {
					System.out.print("-");
				}
				System.out.println();
				
				
			
				
				System.out.println("     PLACA          PREFERENCIALES        PREMIUM          STANDART       ");
				

				for (int i = 0; i < 75; i++) {
					System.out.print("-");
				}

				System.out.println();
				
				for	(Bus busaux:Bus.getBuses()) {
					

						System.out.println("      "+busaux.getPlaca()+"                 "+
						busaux.getTiposAsiento()[0]+"                  "+(busaux.getTiposAsiento()[1]-busaux.getTiposAsiento()[0])+
						"                 "+(busaux.getAsiento()-busaux.getTiposAsiento()[1]));}
						
					
					
					System.out.println();
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion2=input();
					System.out.println();
					
					if (accion2.toLowerCase().equals("si")) {
						administrador();
					}
			
					break;
			case "4":
				System.out.println("Ingrese la placa del bus a eliminar");
				String placa2=input();
				System.out.println();
				
				Bus bus2=Bus.buscarBus(placa2);
				if (bus2==null) {
					System.out.println("No se ha encontrado el bus con placa "+placa2);
					System.out.println();
					
					System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
					String accion3=input();
					System.out.println();
					
					if (accion3.toLowerCase().equals("si")) {
						administrador();
					}
					break;
				}
				Bus.eliminarBus(placa2);
				System.out.println("Bus con placa "+placa2+" ha sido eliminado exitosamente");
				System.out.println();
				
				System.out.println("Desea realizar alguna acción más cómo administrador (si/no)");
				String accion4=input();
				System.out.println();
				
				if (accion4.toLowerCase().equals("si")) {
					administrador();
				}
				break;
			case "5":
				administrador();
			}
			break;
		case "7":;
			break;
		}
	}

	public static void salirDelSistema() {
		System.out.println("Ten un buen viaje");
		Serializador.limpiarArchivos();
		Serializador.serializar();
		System.exit(0);
	}

	public static void main(String[] args) {
Terminal medellin = new Terminal("MEDELLIN");
	Terminal bogota = new Terminal("BOGOTA");

		Terminal cali = new Terminal("CALI");
		Terminal bucaramanga = new Terminal("BUCARAMANGA");
		Terminal pereira = new Terminal("PEREIRA");
		Terminal santaMarta = new Terminal("SANTA MARTA");
		
		Empresa empresa2 = new Empresa("Telm");
		Bus bus2 = new Bus(13, new int[]{3, 4});
		
		Viaje viaje2 = new Viaje(medellin, cali);
		

		viaje2.setEmpresa(empresa2);
		viaje2.setBus(bus2);
		
		viaje2.setFecha(LocalDate.parse("2024-08-21"));
		viaje2.setHora(LocalTime.of(15, 37));
		
		empresa2.getViajes().add(viaje2);
		
		Empresa empresa1 = new Empresa("Coord");
		
		Viaje viaje1 = new Viaje(medellin, bogota);
		
		Viaje viaje3 = new Viaje(santaMarta, bucaramanga);
		Viaje viaje4 = new Viaje(pereira, medellin);
		Hospedaje hospedaje1 = new Hospedaje("Hostal", 2, 5);
		hospedaje1.setUbicacion("Cali");
		Hospedaje hospedaje2= new Hospedaje("Cielo","medellin");
		Bus bus1 = new Bus( 15, new int[]{3, 11});
		
		Bus bus3 = new Bus( 12, new int[]{5, 6});
		Bus bus4 = new Bus( 13, new int[]{5, 11});
		Pasajero pasajero = new Pasajero("samuel", "123123");
		Tiquete tiquete= new Tiquete(pasajero,viaje1,new Asiento());
		pasajero.agregarTiquete(tiquete);
		
		cali.getHospedajes().add(hospedaje1);
		
		empresa1.getViajes().add(viaje1);
		empresa1.getViajes().add(viaje3);
		
		viaje1.setEmpresa(empresa1);
		viaje1.setBus(bus1);
		viaje3.setEmpresa(empresa1);
		viaje3.setBus(bus3);
		
		viaje1.setFecha(LocalDate.parse("2024-08-05"));
		viaje1.setHora(LocalTime.of(15, 37));
		viaje3.setFecha(LocalDate.parse("2024-08-25"));
		viaje3.setHora(LocalTime.of(15, 37));
		
		
		empresa2.getViajes().add(viaje4);
		
		viaje4.setEmpresa(empresa2);
		viaje4.setBus(bus4);
		
		
		viaje4.setFecha(LocalDate.parse("2024-08-26"));
		viaje4.setHora(LocalTime.of(15, 37));
		
		pasajero.agregarTiquete(new Tiquete(pasajero, viaje2, viaje2.buscarAsiento("9A")));
		
//		Deserializador.deserializar();
		chequearAsientosYHabitaciones();
			
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

			opcion = input();
			
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
				System.out.println("Bienvenido Administrador");
				administrador();
				break;
			case "6":
				salirDelSistema();
				break;
			default:
				System.out.println("Opción no válida");
				System.out.println();
				break;
			}
			
		} while (!opcion.equals("6"));
	}
}