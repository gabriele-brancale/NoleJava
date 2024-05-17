package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityBiglietto;
import exception.DAOException;
import exception.DBConnectionException;

public class BigliettoDAO {

	public static int readNumBigliettiVenduti(int idProiezione) throws DAOException, DBConnectionException {
		int numBiglietti = 0;

		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT COUNT(*) FROM BIGLIETTO WHERE IDPROIEZIONE=? ;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setInt(1, idProiezione);

				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					numBiglietti = result.getInt(1);
				}
			}catch(SQLException e) {
				throw new DAOException("Errore biglietto readNumBigliettiVenduti");
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

		return numBiglietti;
	}

	public static int getUltimoPostoVenduto(int idProiezione) throws DAOException, DBConnectionException {
		int numPosto = 0;


		try {

			Connection conn = DBManager.getConnection();

			String query = "SELECT MAX(NUMPOSTO) FROM BIGLIETTO WHERE IDPROIEZIONE=? ;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setInt(1, idProiezione);

				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					numPosto = result.getInt(1);
				}
			}catch(SQLException e) {
				throw new DAOException("Errore biglietto getUltimoPostoVenduto");
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		return numPosto;
	}

	public static void createBiglietto(EntityBiglietto eB) throws DAOException, DBConnectionException {
		
		try {
			
			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO BIGLIETTO VALUES (null,?,?,?,?);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setFloat(1, eB.getCosto());
				stmt.setInt(2, eB.getNumPosto());
				stmt.setInt(3, eB.getIdCliente());
				stmt.setInt(4, eB.getIdProiezione());

				stmt.executeUpdate();

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura biglietto");
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

	}


}
