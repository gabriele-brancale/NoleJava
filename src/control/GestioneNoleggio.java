package control;


import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import database.AccessorioDAO;
import database.ImbarcazioneDAO;
import database.NoleggioDAO;
import entity.EntityAccessorio;
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

			ArrayList<EntityAccessorio> listaAccessori = AccessorioDAO.getAccessori();

			return listaAccessori;

		}catch(DBConnectionException e){

			throw new OperationException("[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("[!] Errore: Impossibile trovare i dati necessari");

		}

	}

	public float checkout(Date dataInizio, Date dataFine, EntityImbarcazione imbarcazione, ArrayList<EntityAccessorio> listaAccessori, boolean skipper){

		GestioneClienti gC = GestioneClienti.getInstance();

		EntityAccessorio obbligatorio = listaAccessori.remove(0);

		noleggio = new EntityNoleggio(dataInizio, dataFine, gC.getClienteRegistrato().getIdClienteRegistrato(), imbarcazione, obbligatorio, listaAccessori, skipper);

		return calcolaCosto(noleggio);

	}

	/*public void conferma() throws OperationException{

		try {

			impegnaImbarcazione(noleggio.getImbarcazione());

			//avviare il timer;

		} catch (OperationException e) {
			
			throw e;

		}

	}*/

	public void annullaNoleggio(){

		noleggio = null;

	}

	public boolean effettuaPagamento(String numeroCarta) throws OperationException{

		try {

			Thread.sleep(3000);

		} catch (InterruptedException e) {
			
			esitoPagamento = false;

		}

        esitoPagamento = true;

		if(esitoPagamento){

			try {

				try{

					NoleggioDAO.inserisciNoleggio(noleggio);
		
				}catch(DBConnectionException e){
		
					throw new OperationException("[!] Errore: Riscontrato un problema interno");
		
				}catch(DAOException e){
		
					throw new OperationException("[!] Errore: Impossibile trovare i dati necessari");
		
				}

			} catch (OperationException e) {

				System.out.println("[#] Info: Qualcosa e' andato storto durante la registrazione del noleggio, rimborso in corso...");

				try {

					Thread.sleep(3000);

				} catch (InterruptedException e1) {}

				System.out.println("[#] Info: Rimborso effettuato");
				
				throw e;

			}

		}

		//disimpegnaImbarcazione(noleggio.getImbarcazione());

		return esitoPagamento;

    }

	/* private void registraNoleggio() throws OperationException{

		try{

			NoleggioDAO.inserisciNoleggio(noleggio);

		}catch(DBConnectionException e){

			throw new OperationException("[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("[!] Errore: Impossibile trovare i dati necessari");

		}

	} */

	/*public void impegnaImbarcazione(EntityImbarcazione imbarcazione) throws OperationException{

		try{

			ImbarcazioneDAO.impegnaImbarcazione(imbarcazione);

		}catch(DBConnectionException e){

			throw new OperationException("[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("[!] Errore: Impossibile trovare i dati necessari");

		}

	}*/

	/*private void disimpegnaImbarcazione(EntityImbarcazione imbarcazione) throws OperationException{

		try{

			ImbarcazioneDAO.impegnaImbarcazione(imbarcazione);

		}catch(DBConnectionException e){

			throw new OperationException("[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("[!] Errore: Impossibile trovare i dati necessari");

		}

	}*/

	private float calcolaCosto(EntityNoleggio noleggio){

		float prezzoAccessori = noleggio.getAccessorioObbligatorio().getPrezzo();

		for (EntityAccessorio accessorio : noleggio.getAccessoriOptional()) {

			prezzoAccessori += accessorio.getPrezzo();
			
		}

		long giorniPrenotati = ChronoUnit.DAYS.between(LocalDate.parse(noleggio.getDataInizio().toString()),LocalDate.parse(noleggio.getDataFine().toString()));

		float costo = ((noleggio.getImbarcazione().getCosto() + (noleggio.getSkipper() ? 50 : 0)) * giorniPrenotati) + prezzoAccessori;

		return costo;

	}

}