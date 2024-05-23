package control;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import org.omg.CORBA.TIMEOUT;

import database.AccessorioDAO;
import database.ClienteRegistratoDAO;
import database.ImbarcazioneDAO;
import database.NoleggioDAO;
import entity.EntityAccessorio;
import entity.EntityClienteRegistrato;
import entity.EntityImbarcazione;
import entity.EntityNoleggio;
import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class GestioneNoleggio {

	private static GestioneNoleggio gN = null;
	private EntityNoleggio noleggio = null;
	private boolean esitoPagamento;

	protected GestioneNoleggio(){}

	public static GestioneNoleggio getInstance(){ 

		if (gN == null){

			gN = new GestioneNoleggio();
		
		}

		return gN;

	}

	public ArrayList<EntityImbarcazione> ricercaImbarcazioni(Date dataInizio, Date dataFine, String tipologia, int numeroPasseggeri) throws OperationException{

		try{

			ArrayList<EntityImbarcazione> risultato = ImbarcazioneDAO.ricercaImbarcazioni(tipologia, numeroPasseggeri, dataInizio, dataFine);

			return risultato;

		}catch(DBConnectionException e){

			throw new OperationException("[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("[!] Errore: Impossibile trovare i dati necessari");

		}

	}

	public ArrayList<EntityAccessorio> getAccessori() throws OperationException{

		try{

			ArrayList<EntityAccessorio> listaAccessori = AccessorioDAO.trovaAccessori();

			return listaAccessori;

		}catch(DBConnectionException e){

			throw new OperationException("[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("[!] Errore: Impossibile trovare i dati necessari");

		}

	}

	public float checkout(Date dataInizio, Date dataFine, EntityImbarcazione imbarcazione, ArrayList<EntityAccessorio> listaAccessori, boolean skipper){

		GestioneClienti gC = GestioneClienti.getInstance();

		noleggio = new EntityNoleggio(dataInizio, dataFine, gC.getClienteRegistrato().idClienteRegistrato, imbarcazione, listaAccessori.get(0), new ArrayList<EntityAccessorio>(listaAccessori.subList(1, listaAccessori.size())), skipper);

		return calcolaCosto(noleggio);

	}

	public void conferma() throws OperationException{

		try {

			impegnaImbarcazione(noleggio.imbarcazione);

			//avviare il timer;

		} catch (OperationException e) {
			
			throw e;

		}

	}

	public boolean effettuaPagamento(String numeroCarta) throws OperationException{

		System.out.println("Pagamento in corso...");

		try {

			Thread.sleep(3000);

		} catch (InterruptedException e) {
			
			esitoPagamento = false;

		}

		System.out.println("Pagamento effettuato...");

        esitoPagamento = true;

		try {

			registraNoleggio();

		} catch (OperationException e) {

			System.out.print("Qualcosa e' andato storto durante la registrazione del noleggio, rimborso in corso...");

			try {

				Thread.sleep(3000);

			} catch (InterruptedException e1) {}

			System.out.print("Rimborso effettuato");
			
			throw e;

		}

		return true;

    }

	private void registraNoleggio() throws OperationException{

		try{

			NoleggioDAO.inserisciNoleggio(noleggio);

		}catch(DBConnectionException e){

			throw new OperationException("[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("[!] Errore: Impossibile trovare i dati necessari");

		}

	}

	public void impegnaImbarcazione(EntityImbarcazione imbarcazione) throws OperationException{

		try{

			ImbarcazioneDAO.impegnaImbarcazione(imbarcazione);

		}catch(DBConnectionException e){

			throw new OperationException("[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("[!] Errore: Impossibile trovare i dati necessari");

		}

	}

	private float calcolaCosto(EntityNoleggio noleggio){

		float prezzoAccessori = noleggio.accessorioObbligatorio.prezzo;

		for (EntityAccessorio accessorio : noleggio.accessoriOptional) {

			prezzoAccessori += accessorio.prezzo;
			
		}

		long giorniPrenotati = ChronoUnit.DAYS.between(LocalDate.parse(noleggio.dataFine.toString()),LocalDate.parse(noleggio.dataInizio.toString()));

		float costo = ((noleggio.imbarcazione.costo + (noleggio.skipper ? 50 : 0)) * giorniPrenotati) + prezzoAccessori;

		return costo;

	}

}