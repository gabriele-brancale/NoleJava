package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.EntityImbarcazione;
import exception.DAOException;
import exception.DBConnectionException;

public class ImbarcazioneDAO {

    public static ArrayList<EntityImbarcazione> ricercaImbarcazioni(String tipologia, int numeroPassegeri, Date dataInizio, Date dataFine) throws DAOException, DBConnectionException{

        ArrayList<EntityImbarcazione> risultati = new ArrayList<EntityImbarcazione>();

		try {

			Connection conn = DBManager.getConnection();

			try {

				String query = "SELECT * FROM IMBARCAZIONE i LEFT JOIN NOLEGGIO n ON i.TARGA = N.TARGA"
							+ " AND n.DATA_INIZIO<? AND n.DATA_FINE>? WHERE n.targa IS NULL AND TIPOLOGIA=? AND CAPIENZA>=? AND STATO='IN USO';";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, dataFine);
				stmt.setDate(2, dataInizio);

				stmt.setString(3, tipologia);
                stmt.setInt(4, numeroPassegeri);

				ResultSet result = stmt.executeQuery();

                while(result.next()){

                    risultati.add(new EntityImbarcazione(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getFloat(6)));

                }

			}catch(SQLException e) {

				throw new DAOException("Errore lettura imbarcazione");

			}finally {

				DBManager.closeConnection();

			}
			
		}catch(SQLException e) {
            
			throw new DBConnectionException("Errore di connessione DB");

		}

		return risultati; 

    }

	public static void impegnaImbarcazione(EntityImbarcazione imbarcazione) throws DAOException, DBConnectionException{

		try {

			Connection conn = DBManager.getConnection();

			try {

				String query = "UPDATE IMBARCAZIONE SET STATO=? WHERE TARGA=?";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, "DISMESSA");
				stmt.setString(2, imbarcazione.getTarga());

				stmt.executeUpdate();

			}catch(SQLException e) {

				throw new DAOException("Errore lettura imbarcazione");

			}finally {

				DBManager.closeConnection();

			}
			
		}catch(SQLException e) {
            
			throw new DBConnectionException("Errore di connessione DB");

		}

	}

	public static void disimpegnaImbarcazione(EntityImbarcazione imbarcazione) throws DAOException, DBConnectionException{

		try {

			Connection conn = DBManager.getConnection();

			try {

				String query = "UPDATE IMBARCAZIONE SET STATO=? WHERE TARGA=?";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, "IN USO");
				stmt.setString(2, imbarcazione.getTarga());

				stmt.executeUpdate();

			}catch(SQLException e) {

				throw new DAOException("Errore lettura imbarcazione");

			}finally {

				DBManager.closeConnection();

			}
			
		}catch(SQLException e) {
            
			throw new DBConnectionException("Errore di connessione DB");

		}

	}

}
