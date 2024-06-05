package control;

import java.sql.Date;

import database.ClienteRegistratoDAO;
import entity.EntityClienteRegistrato;
import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class GestioneClienti {

    private static GestioneClienti gC = null;
    private EntityClienteRegistrato clienteRegistrato;

    protected GestioneClienti(){}

    public static GestioneClienti getInstance(){ 

		if (gC == null){

			gC = new GestioneClienti();
		
		}

		return gC;

	}

    public EntityClienteRegistrato getClienteRegistrato(){

        return clienteRegistrato;

    }

    public boolean login(String email, String password) throws OperationException{

        try{

            clienteRegistrato = ClienteRegistratoDAO.ricercaClienteRegistrato(email, password);

            if(clienteRegistrato == null){

                return false;

            }

            return true;

        }catch(DBConnectionException e){

			throw new OperationException("\u001B[31m" + "[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("\u001B[31m" + "[!] Errore: Impossibile trovare i dati necessari");

		}

    }

    public boolean registrazione(String nome, String cognome, String email, String password, Date dataDiNascita, String numeroPatente) throws OperationException{

        try{

            if(ClienteRegistratoDAO.verificaClienteRegistrato(email)){

                return false;

            }

            clienteRegistrato = ClienteRegistratoDAO.inserisciClienteRegistrato(nome, cognome, email, password, dataDiNascita, numeroPatente);

            if(clienteRegistrato == null){

                return false;

            }

            return true;

        }catch(DBConnectionException e){

			throw new OperationException("\u001B[31m" + "[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("\u001B[31m" + "[!] Errore: Impossibile trovare i dati necessari");

		}

    }
    
    public boolean verificaPatente(){

        return (clienteRegistrato.getNumeroPatante() != null);
        
    }
}
