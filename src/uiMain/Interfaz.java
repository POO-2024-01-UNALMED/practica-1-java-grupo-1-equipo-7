package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

import baseDatos.Serializador;
import gestorAplicación.*;
import java.time.LocalDate;

public class Interfaz {
	static Scanner sc = new Scanner(System.in);

	static void reservarTiquete() {
		System.out.print("Ingrese el nombre completo del origen: ");
		String origen = sc.nextLine();

		System.out.print("Ingrese el nombre completo del destino: ");
		String destino = sc.nextLine();

		System.out.println("Estos son los viajes disponibles para la ruta " + origen + " --> " + destino + ":");

		ArrayList<Viaje> viajesDisponibles = Viaje.buscarViajes(origen, destino);

		for (Viaje viaje : viajesDisponibles) {
			System.out.println((viajesDisponibles.indexOf(viaje) + 1) + ". " + "Número: " + viaje.getIdViaje());

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
		System.out.print("Número de documento: ");
		String idPasajero = sc.nextLine();
		System.out.print("Teléfono: ");
		String telefono = sc.nextLine();

		Pasajero pasajero = new Pasajero(nombre, idPasajero, telefono);

		Tiquete tiquete = new Tiquete(pasajero, viajeSeleccionado, asientoSeleccionado);

		System.out.println("Confirmación de reserva del tiquete:");
		System.out.println("Origen: " + origen);
		System.out.println("Destino: " + destino);
		System.out.println("Nombre del pasajero: " + nombre);

		sc.nextLine();
	}

	public static void salirDelSistema() {
		System.out.println("Ten un buen viaje");
		Serializador.limpiarArchivos();
		System.exit(0);
	}

	public static void main(String[] args) {
		int opcion;
		Terminal medellin = new Terminal("MEDELLIN");
		Terminal bogota = new Terminal("BOGOTA");
		Terminal cali = new Terminal("CALI");
		Terminal bucaramanga = new Terminal("BUCARAMANGA");
		Terminal pereira = new Terminal("PEREIRA");
		Terminal santaMarta = new Terminal("SANTA MARTA");

		Bus bus = new Bus("1234", 20);

		Viaje viaje1 = new Viaje(medellin, bogota, "0001");
		viaje1.setBus(bus);

		bus.getAsientos().get(7).setReservado(true);
		bus.getAsientos().get(8).setReservado(true);

		do {
			System.out.println("¿Qué operación desea realizar?");
			System.out.println("1. Reservar tiquete");
			System.out.println("5. Salir");
			System.out.print("Ingrese el número de la operación: ");

			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				reservarTiquete();
				break;
			case 5:
				salirDelSistema();
				break;
			}
		} while (opcion != 5);

	}

}
