package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import entity.EntityImbarcazione;
import exception.DAOException;
import exception.DBConnectionException;

import java.util.ArrayList;

public class ImbarcazioneDAO {

    public static ArrayList<EntityImbarcazione> ricercaImbarcazioni(String tipologia, int numeroPassegeri, Date dataInizio, Date dataFine) throws DAOException, DBConnectionException{

        ArrayList<EntityImbarcazione> risultato = new ArrayList<EntityImbarcazione>();

		try {

			Connection conn = DBManager.getConnection();

			try {

				String query = "SELECT * FROM IMBARCAZIONE JOIN NOLEGGIO ON IMBARCAZIONE.TARGA=NOLEGGIO.TARGA WHERE TIPOLOGIA=? AND CAPIENZA>=? AND" 
                                + "((DATAINIZIO>? AND DATAINIZIO>?) OR (DATAFINE<? AND DATAFINE<?));";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, tipologia);

				stmt.setInt(2, numeroPassegeri);

				stmt.setDate(3, dataInizio);
                stmt.setDate(4, dataFine);
                stmt.setDate(5, dataInizio);
                stmt.setDate(6, dataFine);

				ResultSet result = stmt.executeQuery();

                while(result.next()){

                    risultato.add(new EntityImbarcazione(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getFloat(7)));

                }

			}catch(SQLException e) {

				throw new DAOException("Errore lettura imbarcazione");

			}finally {

				DBManager.closeConnection();

			}
			
		}catch(SQLException e) {
            
			throw new DBConnectionException("Errore di connessione DB");

		}

		return risultato; 

    }

	public static void impegnaImbarcazione(EntityImbarcazione imbarcazione) throws DAOException, DBConnectionException{

		try {

			Connection conn = DBManager.getConnection();

			try {

				String query = "UPDATE IMBARAZIONE SET STATO=? WHERE TARGA=?";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, "dismessa");
				stmt.setString(2, imbarcazione.targa);

				stmt.executeQuery();

			}catch(SQLException e) {

				throw new DAOException("Errore lettura imbarcazione");

			}finally {

				DBManager.closeConnection();

			}
			
		}catch(SQLException e) {
            
			throw new DBConnectionException("Errore di connessione DB");

		}

	}

	/*
	public static boolean verificaImbarcazione(EntityImbarcazione imbarcazione) throws DAOException, DBConnectionException{

		try {
			
			Connection conn = DBManager.getConnection();

			try{

				String query = "SELECT * FROM IMBARCAZIONE WHERE ID=?";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setInt(1, imbarcazione.id);

				ResultSet result = stmt.executeQuery();

				if(result.next()){

					return true;

				}

				return false;

			}catch(SQLException e){

				throw new DAOException("Errore lettura imbarcazione");

			}
			
		}catch(SQLException e) {
			
			throw new DBConnectionException("Errore di connessione DB");
			
		}

	}
	*/

}
