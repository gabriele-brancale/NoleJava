package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntitySala;
import exception.DAOException;
import exception.DBConnectionException;	

public class SalaDAO {

	public static EntitySala readSala(int idSala) throws DAOException, DBConnectionException {
		EntitySala eS = null;

		try {

			Connection conn = DBManager.getConnection();

			String query = "SELECT * FROM SALA WHERE IDSALA=?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setInt(1, idSala);

				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					eS = new EntitySala(result.getInt(1), result.getInt(2));	
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura sala");
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore di connessione DB");
		}

		return eS;
	}

	public static int capienza(int idSala) throws DAOException, DBConnectionException {
		int capienza = 0;

		try {

			Connection conn = DBManager.getConnection();

			String query = "SELECT CAPIENZA FROM SALA WHERE IDSALA=?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setInt(1, idSala);

				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					capienza = result.getInt(1);	
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura capienza sala");
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore di connessione DB");
		}

		return capienza;
	}

}
