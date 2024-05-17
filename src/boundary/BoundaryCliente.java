package boundary;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import control.GestioneNoleggio;
import exception.OperationException;

public class BoundaryCliente {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {	

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
					continue;

			}

		}
	}

	private static void acquistaBiglietti() {

		boolean inserireCarta = true;
		ArrayList<String> results = null;
		ArrayList<Integer> biglietti = new ArrayList<Integer>();
		GestioneNoleggio gestioneCinema = GestioneNoleggio.getInstance();
		String film, email = null;
		Date data = null;
		Time orario = null;
		int numPosti = 0;
		boolean inputValido = false;

		try {

			System.out.println("Inserisci il film");
			film = scan.nextLine();

			while (!inputValido) {
				try {
					System.out.println("Inserisci la data (aaaa-MM-gg)");
					String dataTemp = scan.nextLine();
					data = Date.valueOf(dataTemp);

					System.out.println("Inserisci l'orario (HH:mm)");
					String orarioTemp = scan.nextLine();
					orario = new Time(new SimpleDateFormat("HH:mm").parse(orarioTemp).getTime());

					inputValido = true;
				} catch (IllegalArgumentException | ParseException iE) {
					System.out.println("Errore nell'acquisizione di data e ora, riprovare..");
					System.out.println();
				}
			}

			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci il numero di posti da acquistare");
					numPosti = Integer.parseInt(scan.nextLine());
					inputValido = true;
				} catch (NumberFormatException nE) {
					System.out.println("Errore, inserire un numero valido");
					System.out.println();
				}
			}

			inputValido = false;
			while (!inputValido) {
				System.out.println("Inserisci la email");

				email = scan.nextLine();

				if (email.contains("@") && email.contains(".")) {
					inputValido = true;
				} else {
					System.out.println("Email non valida..");
				}
			}

			results = gestioneCinema.acquistaBiglietti(film, data, orario, numPosti, email, biglietti);

			System.out.println("Prezzo: " + results.get(0) + " euro");
			System.out.println("Digita 'S' per confermare o qualunque altro carattere per annullare..");
			String conferma = scan.nextLine();

			if (!conferma.equals("S") && !conferma.equals("s")) {
				System.out.println("Operazione annullata..");
				System.out.println();
				return;
			}

			if (!results.get(1).equals("null")) {
				System.out.println();
				System.out.println("Trovata carta di credito per l'utente: " + email);
				System.out.println("Digita 'S' per confermare l'utilizzo della carta ****-****-****-"
						+ results.get(1).substring(results.get(1).length() - 4));

				conferma = scan.nextLine();

				if (conferma.equals("S") || conferma.equals("s"))
					inserireCarta = false;
			}

			if (inserireCarta) {
				inputValido = false;
				while (!inputValido) {
					System.out.println("Inserire il numero di carta:");

					String numeroCarta = scan.nextLine();

					try {
						Long.parseLong(numeroCarta);

						if (numeroCarta.length() == 16) {
							inputValido = true;
						} else {
							System.out.println("Errore inserimento carta, deve essere di 16 cifre..");
						}
					} catch (NumberFormatException e) {
						System.out.println("Errore inserimento carta, deve contenere solo numeri..");
					}
				}
			}

			System.out.println();
			System.out.println("Pagamento in corso..");
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Pagamento effettuato!");

			// se conferma pagamento emetto biglietti altrimenti annullo
			// assumo sempre vero il pagamento
			if (true) {
				gestioneCinema.emettiBiglietti(biglietti);
			}
			// else {
			// gestioneCinema.annullaBiglietti(biglietti);
			// }

			System.out.println("Invio biglietti in corso..");
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Biglietti inviati sulla mail indicata..");

			System.out.println("Acquisto completato!");
			System.out.println();
			System.out.println();

		} catch (OperationException oE) {
			System.out.println(oE.getMessage());
			System.out.println("Riprovare..\n");
		} catch (Exception e) {
			System.out.println("Unexpected exception, riprovare..");
			System.out.println();
		}
	}

}
