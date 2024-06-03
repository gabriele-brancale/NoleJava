package boundary;


import java.sql.Date;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import control.GestioneClienti;
import control.GestioneNoleggio;

import entity.EntityAccessorio;
import entity.EntityImbarcazione;

import exception.OperationException;

public class BoundaryCliente {

	static Scanner scan = new Scanner(System.in);

	private static Date dataInizio;
	private static Date dataFine;

	private static boolean accesso = false;
	
	public static void main(String[] args) {

		boolean exit = false;

		String in;

		System.out.println("\033[H\033[2J");

		System.out.print("  _   _       _          _                  \r\n" +
						   " | \\ | | ___ | | ___    | | __ ___   ____ _ \r\n" +
						   " |  \\| |/ _ \\| |/ _ \\_  | |/ _` \\ \\ / / _` |\r\n" +
						   " | |\\  | (_) | |  __/ |_| | (_| |\\ V / (_| |\r\n" +
						   " |_| \\_|\\___/|_|\\___|\\___/ \\__,_| \\_/ \\__,_|\r\n" +
						   "                                By Project-X\r\n");

		System.out.println();

		while(!exit) {

			System.out.println(/* "\u001B[35m" */"\u001B[32m" + "  MENU:" + "\u001B[0m");
			System.out.println("   1. Login");
			System.out.println("   2. Registrazione");
			System.out.println("   3. Ricerca Imbarcazioni");
			System.out.println("   4. Noleggio Imbarcazioni");
			System.out.println("   5. Esci");

			System.out.println();

			System.out.print("Scegli un'opzione: " + "\u001B[34m");

			in = scan.nextLine();

			System.out.print("\u001B[0m");

			try{

				switch (in) {

					case "1":

						if(accesso == false){

							System.out.print("\033[9;0H\033[0J");
							login();
							System.out.print("\033[9;0H\033[0J");
						
						}else{

							System.out.print("\u001B[33m" + "[#] Attenzione: Login già effettuato");
							System.out.print("\033[F\033[K" + "\033[9;0H");

						}
						break;
						
					case "2":

						System.out.print("\033[9;0H\033[0J");
						registrazione();
						System.out.print("\033[9;0H\033[0J");
						break;

					case "3": 

						System.out.print("\033[9;0H\033[0J");

						ArrayList<EntityImbarcazione> risultato = ricercaImbarcazioni();

						System.out.print("\033[13;0H\033[0J");

						System.out.println("Imbarcazioni disponibili:");

						for (int i = 0; i < risultato.size(); i++) {

							System.out.println(" " + risultato.get(i).getNome() + ":");
							System.out.println("  Capienza: " + risultato.get(i).getCapienza());
							System.out.println("  Costo: " + risultato.get(i).getCosto());
								
						}

						System.out.println();

						System.out.print("Premere [ENTER] per tornare al menu...");

						try
						{
							System.in.read();
							scan.nextLine();
						}  
						catch(Exception e)
						{}

						System.out.print("\033[9;0H\033[0J");

						break;
					
					case "4":

						noleggiaImbarcazione();
						break;

					case "5":

						exit = true;
						System.out.print("\033[9;0H\033[0J");
						System.out.print("\u001B[32m");
						System.out.println(" ╔═══════════════════╗");
						System.out.println(" ║    Arrivederci    ║");
						System.out.println(" ╚═══════════════════╝");
						System.out.println("\u001B[0m");
						break;

					default: 

						System.out.print("\033[K");
						System.out.print("\u001B[31m" + "[!] Errore: Opzione non valida" + "\u001B[0m");
						System.out.print("\033[F\033[K" + "\033[9;0H");

				}

			}catch(OperationException e){

				System.out.print("\033[9;0H\033[0J\033[17;0H");
				System.out.print("\u001B[31m" + "[!] Errore: Operazione fallita" + "\u001B[0m");
				System.out.print("\033[9;0H");

			}

		}
	}

	private static void noleggiaImbarcazione() throws OperationException{

		GestioneNoleggio gN = GestioneNoleggio.getInstance();
		ArrayList<EntityImbarcazione> risultati;
		EntityImbarcazione imbarcazioneScelta;
		ArrayList<EntityAccessorio> listaAccessori;
		boolean skipper = true;

		try {

			risultati = ricercaImbarcazioni();

			if(risultati.size() > 0){

				System.out.println("Imbarcazioni disponibili:");

				for (int i = 0; i < risultati.size(); i++) {

					System.out.println("\t" + (i+1) + ". " + risultati.get(i).getNome() + ":");
					System.out.println("\t\tCapienza: " + risultati.get(i).getCapienza());
					System.out.println("\t\tCosto: " + risultati.get(i).getCosto());
					
				}

				int scelta;

				while(true){

					System.out.print("Inserire l'imbaracazione che si desidera: ");

					try {

						scelta = scan.nextInt();

					} catch (InputMismatchException e) {

						System.out.println("[!] Errore: Input non valido... Riprovare.");
						continue;

					}
					scan.nextLine();

					if(scelta <= 0 || scelta > risultati.size()){

						System.out.println("[!] Errore: Input non valido... Riprovare.");
						continue;

					}

					break;
					
				}

				imbarcazioneScelta = risultati.get(scelta-1);

				listaAccessori = gN.getAccessori();

				System.out.println("Accessori: ");
				System.out.println("\tObbligatori:");

				for (int i = 0; i < listaAccessori.size(); i++) {

					if(!listaAccessori.get(i).getObbligatorio()){

						System.out.println("\tOptional:");

						for(; i < listaAccessori.size(); i++){

							System.out.println("\t\t" + (i+1) + ". " + listaAccessori.get(i).getNome() + ":");
							System.out.println("\t\t\tCapienza: " + listaAccessori.get(i).getDescrizione());
							System.out.println("\t\t\tCosto: " + listaAccessori.get(i).getPrezzo());

						}

						break;

					}

					System.out.println("\t\t" + (i+1) + ". " + listaAccessori.get(i).getNome() + ":");
					System.out.println("\t\t\tCapienza: " + listaAccessori.get(i).getDescrizione());
					System.out.println("\t\t\tCosto: " + listaAccessori.get(i).getPrezzo());
					
				}

				while(true){
				
					System.out.print("Inserire gli accessori separando ogni valore con uno spazio: ");

					ArrayList<String> accessoriScelti = new ArrayList<String>(Arrays.asList(scan.nextLine().split(" ")));

					boolean obbligatiorioScelto = false;

					try{

						for (int i = 0; i < accessoriScelti.size(); i++) {
							
							int accessorio = Integer.parseInt(accessoriScelti.get(i));

							if(accessorio <= 1 || accessorio > listaAccessori.size()){

								throw new Exception("Input non valido");

							}else{

								if(listaAccessori.get(accessorio-1).getObbligatorio()){

									if(obbligatiorioScelto){

										throw new Exception("Piu' di un obbligatorio selezionato");
										// errore piu' di un obbligatorio

									}else{

										obbligatiorioScelto = true;

									}

								}

							}

						}

						if(!obbligatiorioScelto){

							throw new Exception("Piu' di un obbligatorio selezionato");

						}

						for (int i = 0; i < listaAccessori.size(); i++) {

							if(!accessoriScelti.contains(Integer.toString(i+1))){

								listaAccessori.set(i, null);

							}
							
						}

						listaAccessori.removeIf(e -> e == null);

						break;
					
					}catch(Exception e){

						System.out.println("[!] Errore: Input non valido");

					}

				}

				if(accesso == false){

					System.out.println("Non hai effettuato l'accesso, per continuare e' necessario un account: ");
					System.out.println("\t 1. Login");
					System.out.println("\t 2. Registrazione");

					while(true){

						System.out.print("Inserire l'opzione: ");

						try {

							scelta = scan.nextInt();
						
						} catch (InputMismatchException e) {

							System.out.println("[!] Errore: Input non valido... Riprovare.");
							continue;

						}
						scan.nextLine();

						if(scelta == 1){

							login();
							break;
	
						}else if(scelta == 2){
	
							registrazione();
							break;
	
						}else{

							System.out.println("[!] Errore: Input non valido");

						}

					}

				}

				GestioneClienti gC = GestioneClienti.getInstance();

				if(gC.verificaPatente()){

					while(true){

						System.out.print("Si desidera uno skipper? (avra' un costo aggiuntivo di 50euro al giorno) [s/n]: ");

						String opt = scan.nextLine();

						opt = opt.toLowerCase();

						if(opt.equals("n")){

							skipper = false;

						}else if(!opt.equals("s")){

							System.out.println("[!] Errore: Input non valido... Riprovare.");

							continue;

						}

						break;
					
					}

				}

				float costo = gN.checkout(dataInizio, dataFine, imbarcazioneScelta, listaAccessori, skipper);

				System.out.println("[#] Info: Il costo totale del noleggio e': " + costo);

				while(true){

					System.out.print("Confermare l'operazione di noleggio [s/n]: ");

					String opt = scan.nextLine();

					opt = opt.toLowerCase();

					if(opt.equals("s")){

						//gN.conferma();

						String numeroCarta;

						while(true){

							System.out.print("Inserire il numero della carta di credito: ");

							numeroCarta = scan.nextLine();

							if(numeroCarta.matches("^\\d{16}$")){

								break;

							}

						}

						System.out.println("[#] Info: Pagamento in corso...");

						if(gN.effettuaPagamento(numeroCarta)){

							System.out.println("[#] Info: Pagamento effettuato...");

						}else{

							System.out.println("[!] Errore: Pagamento non riuscito");

						}

					}else if(opt.equals("n")){

						gN.annullaNoleggio();

						System.out.println("[#] Info: Operazione Annullata");

					}else{

						System.out.println("[!] Errore: Input non valido... Riprovare");

						continue;

					}

					return;

				}

			}else{

				System.out.println("[#] Info: Nessun risultato trovato");

			}

		} catch (OperationException e) {

			throw e;

		}

	}

	private static ArrayList<EntityImbarcazione> ricercaImbarcazioni() throws OperationException{

		GestioneNoleggio gN = GestioneNoleggio.getInstance();
		ArrayList<EntityImbarcazione> risultato;
		String tipologia = null;
		int numeroPassegeri;

		System.out.print("\u001B[32m");
		System.out.println(" ╔═══════════════╗");
		System.out.println(" ║    RICERCA    ║");
		System.out.println(" ╚═══════════════╝");
		System.out.print("\u001B[0m");
		System.out.println();

		while(true){

			System.out.println("\u001B[32m" + "Scegliere la tipologia:" +  "\u001B[0m");
			System.out.println(" 1. A vela");
			System.out.println(" 2. A motore");
			System.out.println();

			System.out.print("Inserire l'opzione: " + "\u001B[34m");

			tipologia = scan.nextLine();

			System.out.print("\u001B[0m");

			if(tipologia.equals("1")){

				tipologia = "vela";

			}else if(tipologia.equals("2")){

				tipologia = "motore";

			}else{

				System.out.print("\033[K");
				System.out.print("\u001B[31m" + "[!] Errore: Input non valido... Riprovare" + "\u001B[0m");
				System.out.print("\033[F\033[K" + "\033[13;0H");
				
				continue;

			}

			System.out.print("\033[13;0H\033[0J");
			System.out.println("Inserire la tipologia:" + "\u001B[34m" + " " + tipologia + "\u001B[0m");
			

			break;

		}

		while(true){

			System.out.print("Inserie il numero dei passeggeri: " + "\u001B[34m");

			try {

				numeroPassegeri = scan.nextInt();

				System.out.print("\u001B[0m");

				if(numeroPassegeri <= 0){

					throw new InputMismatchException();
	
				}
				
			} catch (InputMismatchException e) {

				System.out.print("\033[K");
				System.out.print("\u001B[31m" + "[!] Errore: Input non valido... Riprovare" + "\u001B[0m");
				System.out.print("\033[F\033[K" + "\033[14;0H");
				continue;

			}finally {

				scan.nextLine();

			}

			break;

		}

		while(true){

			while(true){

				System.out.print("Inserire la data di inzio del noleggio [aaaa-mm-gg]: " + "\u001B[34m");

				try{
					
					String data = scan.nextLine();

					dataInizio = Date.valueOf(data);

					System.out.print("\u001B[0m");

					if(Date.valueOf(LocalDate.now()).after(dataInizio)){

						throw new IllegalArgumentException();

					}

				}catch(IllegalArgumentException e){

					System.out.print("\033[K");
					System.out.print("\u001B[31m" + "[!] Errore: Input non valido... Riprovare" + "\u001B[0m");
					System.out.print("\033[F\033[K" + "\033[15;0H");
					continue;

				}

				break;

			}

			while(true){

				try{

					System.out.print("Inserire la data di fine del nolggio [aaaa-mm-gg]: " + "\u001B[34m");

					dataFine = Date.valueOf(scan.nextLine());

					System.out.print("\u001B[0m");

					if(Date.valueOf(LocalDate.now()).after(dataFine)){

						throw new IllegalArgumentException();

					}

				}catch(IllegalArgumentException e){

					System.out.print("\033[K");
					System.out.print("\u001B[31m" + "[!] Errore: Input non valido... Riprovare" + "\u001B[0m");
					System.out.print("\033[F\033[K" + "\033[16;0H");
					continue;

				}

				break;

			}

			if(dataInizio.after(dataFine)){

				System.out.print("\033[F\033[K");
				System.out.println("\033[F\033[K");
				System.out.print("\u001B[31m" + "[!] Errore: Input non valido... Riprovare" + "\u001B[0m");
				System.out.print("\033[15;0H");

				continue;

			}

			break;
		
		}

		try{

			risultato = gN.ricercaImbarcazioni(dataInizio, dataFine, tipologia, numeroPassegeri);

			return risultato;
		
		}catch(OperationException e){

			throw e;

		}

	}

	private static void login() throws OperationException{

		GestioneClienti gC = GestioneClienti.getInstance();
		String email;
		String password;

		System.out.print("\u001B[32m");
		System.out.println(" ╔═════════════╗");
		System.out.println(" ║    LOGIN    ║");
		System.out.println(" ╚═════════════╝");
		System.out.print("\u001B[0m");
		System.out.println();

		while(true){

			while(true){

				System.out.print("Inserire l'indirizzo email: " + "\u001B[34m");

				email = scan.nextLine();

				System.out.print("\u001B[0m");

				// controllare anche la lunghezza

				if(email.contains("@") && email.contains(".")){		// altrimenti utilizzare l'espressione regolare  /^[a-zA-Z0-9. _%+-]+@[a-zA-Z0-9. -]+\\. [a-zA-Z]{2,}$/

					break;

				}else{
				
					System.out.print("\033[K");
					System.out.print("\u001B[31m" + "[!] Errore: Input non valido... Riprovare" + "\u001B[0m");
					System.out.print("\033[F\033[K" + "\033[13;0H");
					

				}
			
			}

			System.out.print("\033[K");

			while(true){

				System.out.print("Inserire la password: " + "\u001B[34m");

				password = scan.nextLine();

				System.out.print("\u001B[0m");

				// controllare la lunghezza

				if(true /* lunghezza giusta */){

					break;

				}

			}

			try {

				accesso = gC.login(email, password);

				if(accesso == true){

					//System.out.println("[#] Info: Login effettuato");
					System.out.print("\033[7;0H");
					System.out.print("\u001B[32m" + " Login effettuato" + "\u001B[0m");
		
					break;

				}

				System.out.println("\033[13;0H\033[0J");
				System.out.print("\u001B[33m" + "[#] Attenzione: Login fallito... Email o password non corretti, riprovare" + "\u001B[0m");
				System.out.print("\033[13;0H");

			} catch (OperationException e) {
				
				throw e;

			}
		
		}

	}

	private static void registrazione() throws OperationException{

		GestioneClienti gC = GestioneClienti.getInstance();
		String nome;
		String cognome;
		String email;
		String password;
		Date dataDiNascita;
		String numeroPatente;
		Thread caricamento = null;

		System.out.print("\u001B[32m");
		System.out.println(" ╔═════════════════════╗");
		System.out.println(" ║    REGISTRAZIONE    ║");
		System.out.println(" ╚═════════════════════╝");
		System.out.print("\u001B[0m");
		System.out.println();

		while(true){

			while(true){

				System.out.print("Inserire il nome: " + "\u001B[34m");

				nome = scan.nextLine();

				System.out.print("\u001B[0m");

				if(nome.matches("[a-zA-Z]+") /* && controllare lunghezza */){

					break;

				}else{

					System.out.print("\033[K");
					System.out.print("\u001B[31m" + "[!] Errore: Input non valido... Riprovare" + "\u001B[0m");
					System.out.print("\033[F\033[K" + "\033[13;0H");

				}

			}
			
			System.out.print("\033[K");

			while(true){

				System.out.print("Inserire il cognome: " + "\u001B[34m");

				cognome = scan.nextLine();

				System.out.print("\u001B[0m");

				if(cognome.matches("[a-zA-Z]+") /* && controllare lunghezza */){

					break;

				}else{

					System.out.print("\033[K");
					System.out.print("\u001B[31m" + "[!] Errore: Input non valido... Riprovare" + "\u001B[0m");
					System.out.print("\033[F\033[K" + "\033[14;0H");

				}

			}

			System.out.print("\033[K");

			while(true){

				System.out.print("Inserire l'indirizzo email: " + "\u001B[34m");

				email = scan.nextLine();

				System.out.print("\u001B[0m");

				// controllare anche la lunghezza

				if(email.contains("@") && email.contains(".")){		// altrimenti utilizzare l'espressione regolare  /^[a-zA-Z0-9. _%+-]+@[a-zA-Z0-9. -]+\\. [a-zA-Z]{2,}$/

					break;

				}else{
				
					System.out.print("\033[K");
					System.out.print("\u001B[31m" + "[!] Errore: Input non valido... Riprovare" + "\u001B[0m");
					System.out.print("\033[F\033[K" + "\033[15;0H");

				}
			
			}

			System.out.print("\033[K");

			while(true){

				System.out.print("Inserire la password: " + "\u001B[34m");

				password = scan.nextLine();

				System.out.print("\u001B[0m");

				// controllare la lunghezza

				if(true /* lunghezza giusta */){

					break;

				}

			}

			System.out.print("\033[K");

			while(true){

				try{

					System.out.print("Inserire la data di nascita [aaaa-mm-gg]: " + "\u001B[34m");

					dataDiNascita = Date.valueOf(scan.nextLine());

					System.out.print("\u001B[0m");

					break;

				}catch(IllegalArgumentException e){

					System.out.print("\033[K");
					System.out.print("\u001B[31m" + "[!] Errore: Input non valido... Riprovare" + "\u001B[0m");
					System.out.print("\033[F\033[K" + "\033[17;0H");

				}

			}

			System.out.print("\033[K");

			while(true){

				System.out.print("Si possiede una patente? [s/n]: " + "\u001B[34m");

				String scelta = scan.nextLine();

				System.out.print("\u001B[0m");

				scelta = scelta.toLowerCase();

				if(scelta.equals("s")){

					while(true){

						System.out.print("Inserire il numero di patente: " + "\u001B[34m");
		
						numeroPatente = scan.nextLine().toUpperCase();

						System.out.print("\u001B[0m");
		
						if(numeroPatente.length() >= 7 && numeroPatente.length() <= 10 && numeroPatente.substring(0, 2).matches("[A-Z]+") && numeroPatente.substring(2, numeroPatente.length()).matches("\\d+")){
		
							//System.out.print("\033[K");
							//System.out.print("\u001B[33m" + "[#] Registrazione in corso..." + "\u001B[0m");

							caricamento = new Thread(() -> {

								while(true){

									System.out.print("\033[2K\033[0G");
									System.out.print("\u001B[33m" + "[#] Registrazione in corso." + "\u001B[0m");

									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										break;
									}

									System.out.print("\033[2K\033[0G");
									System.out.print("\u001B[33m" + "[#] Registrazione in corso.." + "\u001B[0m");

									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										break;
									}

									System.out.print("\033[2K\033[0G");
									System.out.print("\u001B[33m" + "[#] Registrazione in corso..." + "\u001B[0m");

									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										break;
									}

								}

							});
							caricamento.start();

							break;
		
						}else{
		
							System.out.print("\033[K");
							System.out.print("\u001B[31m" + "[!] Errore: Input non valido... Riprovare" + "\u001B[0m");
							System.out.print("\033[F\033[K" + "\033[19;0H");
		
						}
		
					}
					break;

				}else if(scelta.equals("n")){

					numeroPatente = null;

					break;

				}else{

					System.out.println("[!] Errore: Input non valido... Riprovare");

				}

			}

			try {

				if(gC.registrazione(nome, cognome, email, password, dataDiNascita, numeroPatente)){

					accesso = true;

					break;

				}else{

					caricamento.interrupt();

					System.out.println("[#] Attenzione: Esiste gia' un account con questa email... Riprovare");

				}

			}catch(OperationException e){

				caricamento.interrupt();

				throw e;

			}

		}

	}

}