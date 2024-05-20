package boundary;

import java.util.Scanner;

import control.GestioneNoleggio;

public class BoundaryCliente {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		GestioneNoleggio gN = GestioneNoleggio.getInstance();

		boolean exit = false;

		String in;
		
		System.out.println("Bevenuto in NoleJava");

		while(!exit) {

			System.out.println("1. LogIn");
			System.out.println("2. Registrazione");
			System.out.println("3. Ricerca Imbarcazioni");
			System.out.println("4. Esci");

			in = scan.nextLine();

			switch (in) {

				case "1":

					//login();
					break;
					
				case "2":

					//registrazione();
					break;

				case "3": 

					//ricercaImbarcazioni();
					break;

				case "4":

					exit = true;
					System.out.println("Arrivederci!");
					break;

				default: 

					System.out.println("Opzione non valida");

			}

		}
	}
}
