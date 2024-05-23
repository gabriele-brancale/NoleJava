package control;

import entity.EntityClienteRegistrato;

import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

import database.ClienteRegistratoDAO;

import java.sql.Date;

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

			throw new OperationException("[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("[!] Errore: Impossibile trovare i dati necessari");

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

			throw new OperationException("[!] Errore: Riscontrato un problema interno");

		}catch(DAOException e){

			throw new OperationException("[!] Errore: Impossibile trovare i dati necessari");

		}

    }
    
    public boolean verificaPatente(){

        return (clienteRegistrato.numeroPatente != null);
        
    }
}
