package control;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import database.ClienteRegistratoDAO;
import database.ImbarcazioneDAO;
import entity.EntityAccessorio;
import entity.EntityClienteRegistrato;
import entity.EntityImbarcazione;

public class GestioneNoleggio {

	private static GestioneNoleggio gN = null;

	EntityImbarcazione imbarcazione;
	EntityAccessorio accessorio_obbligatorio;
	ArrayList<EntityAccessorio> accessori_optional;

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

	public ArrayList<EntityImbarcazione> ricercaImbarcazioni(Date dataInizio, Date dataFine, String tipologia, int numeroPasseggeri) throws Exception{

		try{

			ArrayList<EntityImbarcazione> risultato = ImbarcazioneDAO.ricercaImbarcazioni(tipologia, numeroPasseggeri, dataInizio, dataFine);

			return risultato;

		}catch(Exception e){

			throw e;

		}
	}

}