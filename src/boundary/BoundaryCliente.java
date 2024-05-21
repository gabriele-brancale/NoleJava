package boundary;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import control.GestioneNoleggio;
import entity.EntityAccessorio;
import entity.EntityImbarcazione;
import entity.EntityNoleggio;
import exception.OperationException;

public class BoundaryCliente {

	static Scanner scan = new Scanner(System.in);

	public void main(String[] args) {

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

						//login();
						break;
						
					case "2":

						//registrazione();
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

	private void noleggiaImbarcazione() throws OperationException{

		GestioneNoleggio gN = GestioneNoleggio.getInstance();
		ArrayList<EntityImbarcazione> risultati;
		EntityImbarcazione imbarcazioneScelta;
		ArrayList<EntityAccessorio> listaAccessori;

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

					scelta = scan.nextInt();

					if(scelta <= 0 || scelta >= risultati.size()){

						System.out.println("[!] Errore: Input non valido... Riprovare.");
						continue;

					}

					break;
					
				}

				imbarcazioneScelta = risultati.get(scelta);

				listaAccessori = gN.getAccessori();

				System.out.println("Accessori: ");
				System.out.println("\t Obbligatori:");

				for (int i = 0; i < listaAccessori.size(); i++) {

					if(!listaAccessori.get(i).obbligatiorio){

						System.out.println("\t Optional:");

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

				// da continure

			}else{

				System.out.println("[#] Info: Nessun risultato trovato");

			}

		} catch (OperationException e) {

			throw e;

		}

	}

	private ArrayList<EntityImbarcazione> ricercaImbarcazioni() throws OperationException{

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

			tipologia = scan.nextLine();

			if(tipologia == "1" || tipologia == "2"){

				break;

			}else{

				System.out.println("[!] Errore: Input non valido... Riprovare.");

			}

		}

		while(true){

			System.out.print("Inserie il numero dei passeggeri: ");

			numeroPassegeri = scan.nextInt();

			if(numeroPassegeri <= 0){

				System.out.println("[!] Errore: Input non valido... Riprovare.");
				continue;

			}

			break;

		}

		while(true){

			try{

				System.out.print("Inserisci la data di inzio del nolggio [aaaa/mm/gg]: ");

				dataInizio = Date.valueOf(scan.nextLine());

			}catch(IllegalArgumentException e){

				System.out.println("[!] Errore: Input non valido... Riprovare.");
				continue;

			}

			break;

		}

		while(true){

			try{

				System.out.print("Inserisci la data di fine del nolggio [aaaa-mm-gg]: ");

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

	private float calcolaCosto(EntityNoleggio noleggio){

		float prezzoAccessori = noleggio.accessorio_obbligatorio.prezzo;

		for (EntityAccessorio accessorio : noleggio.accessori_optional) {

			prezzoAccessori += accessorio.prezzo;
			
		}

		long giorniPrenotati = ChronoUnit.DAYS.between(LocalDate.parse(noleggio.dataFine.toString()),LocalDate.parse(noleggio.dataInizio.toString()));

		float costo = ((noleggio.imbarcazione.costo /*+ costo skipper*/) * giorniPrenotati) + prezzoAccessori;

		return costo;

	}

}