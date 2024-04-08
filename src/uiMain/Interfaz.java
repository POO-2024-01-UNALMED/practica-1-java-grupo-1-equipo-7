package uiMain;

import java.util.ArrayList;
import java.util.Scanner;
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

		System.out.println("Ingrese el número del viaje: ");
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
					System.out.print(asiento.getNumeroAsiento() + " ");
				}
			}
			if (caracter == 'B') {
				System.out.println();
			}
			System.out.println();
		}

		System.out.println("Ingrese el número del asiento que desea: ");
		String numeroAsiento = sc.nextLine();

		sc.nextLine();
	}

	public static void main(String[] args) {
		int opcion;
		Terminal terminal1 = new Terminal("MEDELLIN");
		Terminal terminal2 = new Terminal("BOGOTA");

		Bus bus = new Bus("1234", 20);

		Viaje viaje1 = new Viaje(terminal1, terminal2, "0001");
		viaje1.setBus(bus);

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
				break;
			}
		} while (opcion != 5);

	}

}
