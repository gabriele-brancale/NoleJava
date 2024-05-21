package control;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import database.AccessorioDAO;
import database.ClienteRegistratoDAO;
import database.ImbarcazioneDAO;
import entity.EntityAccessorio;
import entity.EntityClienteRegistrato;
import entity.EntityImbarcazione;
import entity.EntityNoleggio;
import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class GestioneNoleggio {

	private static GestioneNoleggio gN = null;

	protected GestioneNoleggio(){

		

	}

	public static GestioneNoleggio getInstance(){ 

		if (gN == null){

			gN = new GestioneNoleggio();
		
		}

		gN.resetta();

		return gN;

	}

	public void resetta(){

		imbarcazione = null;
		accessorio_obbligatorio = null;
		accessori_optional = null;

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

	public void noleggia(String cartaDiredito, EntityImbarcazione imbarcazione, EntityAccessorio accessorioObbligatori, ArrayList<EntityAccessorio> accessoriOptional, boolean skipper){



	}

	public void impegnaImbarcazione(EntityImbarcazione imbarcazione) throws Exception{

		try{

			ImbarcazioneDAO.impegnaImbarcazione(imbarcazione);

		}catch(Exception e){

			throw e;

		}

	}

}