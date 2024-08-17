package uiMain;

import java.text.Normalizer;
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
import java.util.function.ToLongBiFunction;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorAplicación.gestion.Empresa;
import gestorAplicación.gestion.Habitacion;
import gestorAplicación.gestion.Hospedaje;
import gestorAplicación.gestion.Terminal;
import gestorAplicación.gestion.Tiquete;
import gestorAplicación.gestion.Viaje;
import gestorAplicación.personas.Pasajero;
import gestorAplicación.personas.Persona;
import gestorAplicación.transporte.Asiento;
import gestorAplicación.transporte.Bus;
import gestorAplicación.transporte.TipoAsiento;

public class Interfaz {
	static Scanner sc = new Scanner(System.in);
	
	public static String input() {
		String input = sc.nextLine();
		
		return Normalizer.normalize(input, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
	}
	
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
					+ "         HORA DE SALIDA     ID       PLACA BUS");

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
					+ "         HORA DE SALIDA     ID       PLACA BUS");

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
					+ "         HORA DE SALIDA     ID       PLACA BUS");
	
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
					+ "         HORA DE SALIDA     ID       PLACA BUS");
	
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
					+ "         HORA DE SALIDA     ID       PLACA BUS");
	
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
					+ "         HORA DE SALIDA     ID       PLACA BUS");
	
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
								
								if (viaje.buscarAsiento(numeroAsiento).isReservado() 
										|| viaje.buscarAsiento(numeroAsiento) == null) {
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
								
								final String numeroAsiento2 = numeroAsiento;
								
								viaje.reservarAsiento(numeroAsiento2);
								
								System.out.println("¿Por cuánto tiempo desea reservarlo?" 
								+ " (minutos/horas/dias)");
			
								String tiempo = input();
			
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
			
									tiempo = input();
									
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
							
							if (viaje.buscarAsiento(numeroAsiento).isReservado() 
									|| viaje.buscarAsiento(numeroAsiento) == null) {
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
							
							final String numeroAsiento2 = numeroAsiento;
							
							viaje.reservarAsiento(numeroAsiento2);
			
							System.out.println("¿Por cuánto tiempo desea reservarlo?" 
							+ " (minutos/horas/dias)");
			
							String tiempo = input();
			
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
			
								tiempo = input();
								
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
				+ "         HORA DE SALIDA     ID       PLACA BUS");
	
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
				
				System.out.println();
				
				if (viaje.buscarAsiento(numeroAsiento).isReservado() 
						|| viaje.buscarAsiento(numeroAsiento) == null) {
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

				viaje.reservarAsiento(numeroAsiento);
		
				System.out.println("Ingrese sus datos:");
				
				System.out.println();
		
				System.out.print("Nombre: ");
				
				String nombre = input();
				
				System.out.print("Número de identificación (6 dígitos): ");
				
				String idPasajero = input();
				
				while (idPasajero.length() != 6) {
					System.out.print("Número de identificación (6 dígitos): ");
					idPasajero = input();
				}
				
				System.out.println();
				
				Pasajero pasajero = new Pasajero(nombre, idPasajero);
				
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
				System.out.println("Asiento: " + asiento);
				System.out.println("Empresa: " + viaje.getEmpresa().getNombre());
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
				
				for (int i = 0; i < 85; i++) {
					System.out.print("-");
				}

				System.out.println();

				System.out.println("    NUMERO DE RESERVA     NOMBRE        ASIENTO" 
				+ "     FECHA DEL VIAJE      ID VIAJE     ");

				for (int i = 0; i < 85; i++) {
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
							+ "         HORA DE SALIDA     ID       PLACA BUS");
		
							for (int i = 0; i < 92; i++) {
								System.out.print("-");
							}
							
							System.out.println();
							
							System.out.println(tiquete.getViaje());
							
							System.out.println();
							
							System.out.println("Detalles del tiquete");
							
							for (int i = 0; i < 85; i++) {
								System.out.print("-");
							}
		
							System.out.println();
		
							System.out.println("    NUMERO DE RESERVA     NOMBRE        ASIENTO" 
							+ "     FECHA DEL VIAJE      ID VIAJE     ");
							
							for (int i = 0; i < 85; i++) {
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
										System.out.println("No hay más sillas disponibles");
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
										
										System.out.println();
										
										if (viaje.buscarAsiento(numeroAsiento).isReservado() 
												|| viaje.buscarAsiento(numeroAsiento) == null) {
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
								} else {	
									System.out.print("Ingrese el origen: ");
									
									String origen = input();
									
									System.out.println();
	
									System.out.print("Ingrese el destino: ");
									
									String destino = input();
									
									System.out.println();
	
									System.out.println("Estos son los viajes disponibles " + 
														"para la ruta " + origen.toUpperCase() 
														+ " --> " + destino.toUpperCase() + ":");
									
									for (Viaje viaje : Empresa.buscarViajes(origen.toUpperCase(), 
											destino.toUpperCase())) {
									
										for (int i = 0; i < 92; i++) {
											System.out.print("-");
										}
	
										System.out.println();
	
										System.out.println("    FECHA          " 
										+ "ORIGEN          DESTINO" 
										+ "         HORA DE SALIDA     ID       PLACA BUS");
	
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
	
									Viaje viaje = Empresa.buscarViaje(id);
	
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
									
									if (viaje.buscarAsiento(numeroAsiento).isReservado() 
											|| viaje.buscarAsiento(numeroAsiento) == null) {
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
								
								System.out.println("Nombre del pasajero: " 
													+ tiquete.getPasajero().getNombre());
								System.out.println("Asiento: " + tiquete.getAsiento());
								System.out.println("Empresa: " 
													+ tiquete.getViaje().getEmpresa().getNombre());
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
			
			for (Tiquete tiquete : pasajero.buscarTiquetes("validos")) {
				System.out.println(tiquete.getViaje());
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
		
					System.out.println("    NOMBRE     CALIFICACION     HABITACIONES DISPONIBLES");
		
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
						
						if (hospedaje.buscarHabitacion(numeroHabitacion).isReservada() 
								|| hospedaje.buscarHabitacion(numeroHabitacion) == null) {
							while (true) {
								System.out.println();
								System.out.println("La habitación no está disponible");
								System.out.println();
								System.out.print("Ingrese otro número de habitación: ");
								
								numeroHabitacion = input();
								
								if (!hospedaje.buscarHabitacion(numeroHabitacion).isReservada() 
										&& hospedaje.buscarHabitacion(numeroHabitacion) != null) {
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
						
						if (tiempo.toLowerCase().contains("hora") 
								|| tiempo.toLowerCase().contains("horas")) {
							fechaReserva = fechaViaje.
									plusHours(Integer.valueOf(cantidad));
						} else if (tiempo.toLowerCase().contains("dia") 
										|| tiempo.toLowerCase().contains("dias")) {
							fechaReserva = fechaViaje.
									plusDays(Integer.valueOf(cantidad));
						} 
						
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
					}
				}
			}
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
			
			switch (empresas) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Empresa");
				System.out.println("2. Conductores a una empresa");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String empresasAgregar = input();
				break;
			case "2":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Empresa");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String empresasVer = input();
				break;
			case "3":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Empresa");
				System.out.println("2. Conductores de una empresa");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String empresasEliminar = input();
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
			
			String hospedajes = input();
			
			
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
				break;
			case "2":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Hospedajes");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String hospedajesVer = input();
				break;
			case "3":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Hospedaje");
				System.out.println("2. Habitaciones de un hospedaje");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String hospedajesEliminar = input();
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
			
			String terminales = input();
			
			
			switch(terminales) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Terminal");
				System.out.println("2. Empresa a una terminal");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String terminalesAgregar = input();
				break;
			case "2":
				System.out.println("¿Qué desea anclar?");
				
				System.out.println();
				
				System.out.println("1. Revisor a una terminal");
				System.out.println("2. Aseador a una terminal");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String terminalesAnclar = input();
				break;
			case "3":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Terminales");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String terminalesVer = input();
				break;
			case "4":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Terminal");
				System.out.println("2. Empresa de una terminal");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String terminalesEliminar = input();
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
			
			String viaje = input();
			
			switch (viaje) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Viaje");
				System.out.println("2. Tiquete");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String viajesAgregar = input();
				break;
			case "2":
				System.out.println("¿Qué desea añadir?");
				
				System.out.println();
				
				System.out.println("1. Hospedajes");
				System.out.println("2. Pasajeros");
				System.out.println("3. Revisores");
				System.out.println("4. Volver");
				
				System.out.println();
				
				String viajesAñadir = input();
				break;
			case "3":
				System.out.println("¿Qué desea modificar?");
				
				System.out.println();
				
				System.out.println("1. Viaje");
				System.out.println("2. Volver");
				
				System.out.println();
				
				String viajesModificar = input();
				break;
			case "4":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Viajes");
				System.out.println("2. Tiquetes");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String viajesVer = input();
			
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
				
				String viajesEliminar = input();
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
			
			String personal = input();
			
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
				
				String personalAgregar = input();
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
				
				String personalmodificar = input();
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
				
				String personalVer = input();
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
				
				String personalEliminar = input();
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
			
			switch (vehiculos) {
			case "1":
				System.out.println("¿Qué desea agregar?");
				
				System.out.println();
				
				System.out.println("1. Bus");
				System.out.println("2. MiniVan");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String vehiculosAgregar = input();
				break;
			case "2":
				System.out.println("¿Qué desea modificar?");
				
				System.out.println();
				
				System.out.println("1. Bus");
				System.out.println("2. MiniVan");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String vehiculosModificar = input();
				break;
			case "3":
				System.out.println("¿Qué desea ver?");
				
				System.out.println();
				
				System.out.println("1. Bus");
				System.out.println("2. MiniVan");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String vehiculosVer = input();
				break;
			case "4":
				System.out.println("¿Qué desea eliminar?");
				
				System.out.println();
				
				System.out.println("1. Bus");
				System.out.println("2. MiniVan");
				System.out.println("3. Volver");
				
				System.out.println();
				
				String vehiculosEliminar = input();
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
		Hospedaje hospedaje1 = new Hospedaje("Hostal", 2, 5);
		Bus bus1 = new Bus("0001", 15, new int[]{3, 11});
		Bus bus2 = new Bus("0002", 13, new int[]{3, 4});
		Bus bus3 = new Bus("0003", 12, new int[]{5, 6});
		Bus bus4 = new Bus("0004", 13, new int[]{5, 11});
		Pasajero pasajero = new Pasajero("samuel", "123");
		
		cali.getHospedajes().add(hospedaje1);
		
		empresas.add(empresa1);
		empresas.add(empresa2);
		
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
		
		pasajero.agregarTiquete(new Tiquete(pasajero, viaje2, viaje2.buscarAsiento("9A")));
		
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