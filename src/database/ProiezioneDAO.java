package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import entity.EntityProiezione;
import exception.DAOException;
import exception.DBConnectionException;

public class ProiezioneDAO {

	public static EntityProiezione readProiezione(String film, Date data, Time orario) throws DAOException, DBConnectionException {

		EntityProiezione eP = null;



		try {

			Connection conn = DBManager.getConnection();

			try {
				String query = "SELECT * FROM PROIEZIONE WHERE DATA=? AND ORARIO=? AND IDFILM = (" + 
						"   SELECT IDFILM FROM FILM WHERE TITOLO = ?);";


				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, data);
				stmt.setTime(2, orario);
				stmt.setString(3, film);

				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					eP = new EntityProiezione(result.getInt(1), data, orario, result.getInt(4), result.getInt(5));	
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura proiezione");
			}finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore di connessione DB");
		}

		return eP;
	}

}