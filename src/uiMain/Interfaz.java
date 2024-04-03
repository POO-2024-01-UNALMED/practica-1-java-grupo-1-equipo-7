package uiMain;

import java.util.Scanner;
import gestorAplicación.*;

public class Interfaz {
	static Scanner sc = new Scanner(System.in);

	static void reservarTiquete() {
		System.out.print("Ingrese el nombre completo del origen: ");
		String origen = sc.nextLine();

		System.out.print("Ingrese el nombre completo del destino: ");
		String destino = sc.nextLine();
		
		for(Viaje viaje : Viaje.buscarViajes(origen, destino)) {
			
		}
		
		
		
	}

	public static void main(String[] args) {
		int opcion;
		
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
		} while(opcion != 5);
		

	}

}
