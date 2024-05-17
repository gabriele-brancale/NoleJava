package control;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import database.BigliettoDAO;
import database.ClienteRegistratoDAO;
import database.ProiezioneDAO;
import database.SalaDAO;
import entity.EntityBiglietto;
import entity.EntityClienteRegistrato;
import entity.EntityProiezione;
import entity.EntitySala;
import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class GestioneNoleggio {

	private static GestioneNoleggio gC = null;
	private float prezzoBiglietto = (float) 7.5;
	private ArrayList<EntityBiglietto> bigliettiInAttesa;

	protected GestioneNoleggio(){
		bigliettiInAttesa = new ArrayList<EntityBiglietto>();
	}

	public static GestioneNoleggio getInstance() 
	{ 
		if (gC == null) 
			gC = new GestioneNoleggio(); 

		return gC; 
	}


	public ArrayList<String> acquistaBiglietti(String film, Date data, Time orario, int numPosti, String email, ArrayList<Integer> idBiglietti) throws OperationException {

		EntityProiezione eP = null;
		EntityClienteRegistrato eC = null;
		int numBigliettiVenduti;
		float prezzoSingolo = prezzoBiglietto;
		float prezzoTot = 0;
		int idClienteAcquisto = 0;


		ArrayList<String> returnList = new ArrayList<String>();
		returnList.add("0");//prezzo
		returnList.add("null");//cartaDiCredito


		try {
			//controllo esistenza proiezione
			eP = ProiezioneDAO.readProiezione(film, data, orario);

			if(eP == null) {
				throw new OperationException("Proiezione non trovata");
			}
			
			
			int capienza = eP.capienzaSala();
			
			//controllo sufficienti posti liberi
			numBigliettiVenduti = BigliettoDAO.readNumBigliettiVenduti(eP.getIdProiezione());
			
						
			if(capienza - numBigliettiVenduti < numPosti) {
				throw new OperationException("Posti non sufficienti");
			}

			//controllo clienteRegistrato
			eC = ClienteRegistratoDAO.readClienteRegistrato(email);

			if (eC != null) {
				prezzoTot = applicaSconto(prezzoSingolo, numPosti);
				returnList.set(1,eC.getCartaDiCredito());
				idClienteAcquisto = eC.getIdClienteRegistrato();
			}
			else {
				prezzoTot = calcolaPrezzoIntero(prezzoSingolo, numPosti);
			}
			returnList.set(0,String.valueOf(prezzoTot));


			//genero i biglietti temporanei, che vengono restituiti alla boundary
			//biglietti temporanei da cancellare se il cliente non conferma l'acquisto
			int numUltimoPosto = BigliettoDAO.getUltimoPostoVenduto(eP.getIdProiezione());

			for(int i = 0; i < numPosti; i++) {
				idBiglietti.add(bigliettiInAttesa.size());
				bigliettiInAttesa.add(new EntityBiglietto(bigliettiInAttesa.size(), prezzoSingolo, numUltimoPosto+(i+1), eP.getIdProiezione(), idClienteAcquisto));
			}

		}catch(DBConnectionException dbEx) {
			//caso di problemi di connessione al DB
			
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa � andato storto..");
		}
		
		//restituisco i biglietti temporanei alla boundary
		//la boundary dovr� chiedere conferma all'utente e in caso positivo invocare il metodo "emettiBiglietti"
		//altrimenti dovr� invocare "annullaBiglietti"
		return returnList;
	}



	//Questo è stato reso pubblico per semplicità
	public void emettiBiglietti(ArrayList<Integer> biglietti) throws OperationException {

		for(int i = 0; i < biglietti.size() ; i++) {
			try {
				int indexTemp = biglietti.get(i);
				bigliettiInAttesa.get(indexTemp).saveBiglietto();
			}catch(DBConnectionException dbEx) {
				//caso di problemi di connessione al DB
				throw new OperationException("\nRiscontrato problema interno applicazione!\n");
			}catch(DAOException e) {
				//� necessario rimuovere eventuali biglietti gi� creati..
				throw new OperationException("Errore emissione biglietti");
			}
		}
		for(int i = biglietti.size()-1; i >= 0  ; i--) {
			bigliettiInAttesa.remove((int)biglietti.get(i));
		}
	}
	
	private float applicaSconto(float prezzoSingolo, int numPosti)  {
		prezzoSingolo = this.prezzoBiglietto - (this.prezzoBiglietto*10/100);
		return (prezzoSingolo * numPosti);
	}
	
	private float calcolaPrezzoIntero(float prezzoSingolo, int numPosti)  {
		return (prezzoSingolo * numPosti);
	}
	

	
	
//	public void annullaBiglietti(ArrayList<Integer> biglietti) {
//		
//	}

}
