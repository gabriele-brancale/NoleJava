package boundary;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import control.GestioneClienti;
import control.GestioneNoleggio;

import entity.EntityAccessorio;
import entity.EntityImbarcazione;

import exception.OperationException;

public class BoundaryCliente {

	static Scanner scan = new Scanner(System.in);

	static Date dataInizio;
	static Date dataFine;

	static boolean accesso = false;

	public static void main(String[] args) {

		boolean exit = false;

		String in;
		
		System.out.println("Bevenuto in NoleJava");

		while(!exit) {

			System.out.println("1. LogIn");
			System.out.println("2. Registrazione");
			System.out.println("3. Ricerca Imbarcazioni");
			System.out.println("4. Noleggio Imbarcazioni");
			System.out.println("5. Esci");

			in = scan.nextLine();

			try{

				switch (in) {

					case "1":

						login();
						break;
						
					case "2":

						registrazione();
						break;

					case "3": 

						ArrayList<EntityImbarcazione> risultato = ricercaImbarcazioni();

						System.out.println("Imbarcazioni disponibili:");

						for (int i = 0; i < risultato.size(); i++) {

							System.out.println("\t" + risultato.get(i).nome + ":");
							System.out.println("\t\tCapienza: " + risultato.get(i).capienza);
							System.out.println("\t\tCosto: " + risultato.get(i).costo);
								
						}

						break;
					
					case "4":

						noleggiaImbarcazione();
						break;

					case "5":

						exit = true;
						System.out.println("Arrivederci!");
						break;

					default: 

						System.out.println("Opzione non valida");

				}

			}catch(OperationException e){

				System.out.println("[!] Errore: Operazione fallita");

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

			System.out.println("Imbarcazioni disponibili:");

			for (int i = 0; i < risultati.size(); i++) {

				System.out.println("\t" + (i+1) + ". " + risultati.get(i).nome + ":");
				System.out.println("\t\tCapienza: " + risultati.get(i).capienza);
				System.out.println("\t\tCosto: " + risultati.get(i).costo);
				
			}

			if(risultati.size() > 0){

				int scelta;

				while(true){

					System.out.print("Inserire l'imbaracazione che si desidera: ");

					scelta = scan.nextInt();
					scan.nextLine();

					if(scelta <= 0 || scelta >= risultati.size()){

						System.out.println("[!] Errore: Input non valido... Riprovare.");
						continue;

					}

					break;
					
				}

				imbarcazioneScelta = risultati.get(scelta);

				listaAccessori = gN.getAccessori();

				System.out.println("Accessori: ");
				System.out.println("\tObbligatori:");

				for (int i = 0; i < listaAccessori.size(); i++) {

					if(!listaAccessori.get(i).obbligatiorio){

						System.out.println("\tOptional:");

						for(; i < listaAccessori.size(); i++){

							System.out.println("\t" + (i+1) + ". " + listaAccessori.get(i).nome + ":");
							System.out.println("\t\tCapienza: " + listaAccessori.get(i).descrizione);
							System.out.println("\t\tCosto: " + listaAccessori.get(i).prezzo);

						}

						break;

					}

					System.out.println("\t" + (i+1) + ". " + listaAccessori.get(i).nome + ":");
					System.out.println("\t\tCapienza: " + listaAccessori.get(i).descrizione);
					System.out.println("\t\tCosto: " + listaAccessori.get(i).prezzo);
					
				}

				while(true){
				
					System.out.print("Inserire gli accessori separando ogni valore con uno spazio: ");

					ArrayList<String> accessoriScelti = new ArrayList<String>(Arrays.asList(scan.nextLine().split(" ")));

					boolean obbligatiorioScelto = false;

					try{

						for (int i = 0; i < accessoriScelti.size(); i++) {
							
							int accessorio = Integer.parseInt(accessoriScelti.get(i));

							if(accessorio <= 0 || accessorio > listaAccessori.size()){

								throw new Exception("Input non valido");

							}else{

								if(listaAccessori.get(accessorio).obbligatiorio){

									if(obbligatiorioScelto){

										throw new Exception("Piu' di un obbligatorio selezionato");
										// errore piu' di un obbligatorio

									}else{

										obbligatiorioScelto = true;

									}

								}

							}

						}

						for (int i = 0; i < listaAccessori.size(); i++) {

							if(!accessoriScelti.contains(Integer.toString(i))){

								listaAccessori.remove(i);

							}
							
						}

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

						System.out.println("Inserire l'opzione: ");

						scelta = scan.nextInt();		// ******************** gestire il caso in cui non viene inserito un numero ovunque ci sia uno scan.nextInt()
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

						System.out.println("Si desidera uno skipper? (avra' un costo aggiuntivo di 50euro al giorno) [s/n]: ");

						String opt = scan.nextLine();

						opt = opt.toLowerCase();

						if(opt.equals("n")){

							skipper = false;

						}else if(opt.equals("s")){

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

						gN.conferma();

						String numeroCarta;

						while(true){

							System.out.print("Inserire il numero della carta di credito: ");

							numeroCarta = scan.nextLine();

							if(numeroCarta.matches("^\\d{16}$")){

								break;

							}

						}

						if(gN.effettuaPagamento(numeroCarta)){

							

						}

					}else if(opt.equals("n")){

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
		Date dataInizio;
		Date dataFine;

		while(true){

			System.out.println("Scegliere la tipologia:");
			System.out.println("\t1. A vela");
			System.out.println("\t2. A motore");

			System.out.print("Inserire l'opzione: ");

			tipologia = scan.nextLine();

			if(tipologia.equals("1") || tipologia.equals("2")){

				break;

			}else{

				System.out.println("[!] Errore: Input non valido... Riprovare.");

			}

		}

		while(true){

			System.out.print("Inserie il numero dei passeggeri: ");

			numeroPassegeri = scan.nextInt();
			scan.nextLine();

			if(numeroPassegeri <= 0){

				System.out.println("[!] Errore: Input non valido... Riprovare.");
				continue;

			}

			break;

		}

		while(true){

			System.out.print("Inserire la data di inzio del nolggio [aaaa-mm-gg]: ");

			try{
				
				String data = scan.nextLine();

				dataInizio = Date.valueOf(data);

			}catch(IllegalArgumentException e){

				System.out.println("[!] Errore: Input non valido... Riprovare.");
				continue;

			}

			break;

		}

		while(true){

			try{

				System.out.print("Inserire la data di fine del nolggio [aaaa-mm-gg]: ");

				dataFine = Date.valueOf(scan.nextLine());

			}catch(IllegalArgumentException e){

				System.out.println("[!] Errore: Input non valido... Riprovare.");
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

		while(true){

			while(true){

				System.out.print("Inserire l'indirizzo email: ");

				email = scan.nextLine();

				// controllare anche la lunghezza

				if(email.contains("@") && email.contains(".")){		// altrimenti utilizzare l'espressione regolare  /^[a-zA-Z0-9. _%+-]+@[a-zA-Z0-9. -]+\\. [a-zA-Z]{2,}$/

					break;

				}else{
				
					System.out.println("[!] Errore: Input non valido... Riprovare");

				}
			
			}

			while(true){

				System.out.print("Inserire la password: ");

				password = scan.nextLine();

				// controllare la lunghezza

				if(true /* lunghezza giusta */){

					break;

				}

			}

			try {

				accesso = gC.login(email, password);

				if(accesso == true){

					System.out.println("[#] Info: Login effettuato");
		
					break;

				}

				System.out.println("[#] Info: Login fallito... Email o password non corretti, riprovare");

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

		while(true){

			while(true){

				System.out.print("Inserire il nome: ");

				nome = scan.nextLine();

				if(nome.matches("[a-zA-Z]+") /* && controllare lunghezza */){

					break;

				}else{

					System.out.println("[!] Errore: Input non valido... Riprovare");

				}

			}

			while(true){

				System.out.print("Inserire il cognome: ");

				cognome = scan.nextLine();

				if(nome.matches("[a-zA-Z]+") /* && controllare lunghezza */){

					break;

				}else{

					System.out.println("[!] Errore: Input non valido... Riprovare");

				}

			}

			while(true){

				System.out.print("Inserire l'indirizzo email: ");

				email = scan.nextLine();

				// controllare anche la lunghezza

				if(email.contains("@") && email.contains(".")){		// altrimenti utilizzare l'espressione regolare  /^[a-zA-Z0-9. _%+-]+@[a-zA-Z0-9. -]+\\. [a-zA-Z]{2,}$/

					break;

				}else{
				
					System.out.println("[!] Errore: Input non valido... Riprovare");

				}
			
			}

			while(true){

				System.out.print("Inserire la password: ");

				password = scan.nextLine();

				// controllare la lunghezza

				if(true /* lunghezza giusta */){

					break;

				}

			}

			while(true){

				try{

					System.out.print("Inserire la data di nascita [aaaa/mm/gg]: ");

					dataDiNascita = Date.valueOf(scan.nextLine());

				}catch(IllegalArgumentException e){

					System.out.println("[!] Errore: Input non valido... Riprovare.");
					continue;

				}

				break;

			}

			while(true){

				System.out.println("Si possiede una patente? [s/n]: ");

				String scelta = scan.nextLine();

				scelta = scelta.toLowerCase();

				if(scelta.equals("s")){

					while(true){

						System.out.print("Inserire il numero di patente: ");
		
						numeroPatente = scan.nextLine().toUpperCase();
		
						if(numeroPatente.length() <= 10 && numeroPatente.length() >= 7 && numeroPatente.substring(0, 2).matches("[a-zA-Z]+") && numeroPatente.substring(2, numeroPatente.length()).matches( "\\d+")){
		
							break;
		
						}else{
		
							System.out.println("[!] Errore: Input non valido... Riprovare.");
		
						}
		
					}

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

					System.out.println("[!] Errore: Esiste gia' un account con questa email... Riprovare");

				}

			}catch(OperationException e){

				throw e;

			}

		}

	}

}