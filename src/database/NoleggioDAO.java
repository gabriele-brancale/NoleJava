package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityAccessorio;
import entity.EntityNoleggio;
import exception.DAOException;
import exception.DBConnectionException;

public class NoleggioDAO {
    
    public static void inserisciNoleggio(EntityNoleggio noleggio) throws DAOException, DBConnectionException{

		try {

			Connection conn = DBManager.getConnection();

			try {

				String query = "INSERT INTO NOLEGGIO VALUES (NULL, ?, ?, ?, ?, ?, ?);";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, noleggio.getDataInizio());
                stmt.setDate(2, noleggio.getDataFine());
                stmt.setInt(3, noleggio.getIdCliente());
                stmt.setString(4, noleggio.getImbarcazione().getTarga());
				stmt.setInt(5, noleggio.getAccessorioObbligatorio().getId());
				stmt.setBoolean(6, noleggio.getSkipper());

				stmt.executeUpdate();

				stmt = conn.prepareStatement("SELECT LAST_INSERT_ID()");

				ResultSet result = stmt.executeQuery();
				result.next();

				int idNoleggio = result.getInt(1);

				query = "INSERT INTO NOLEGGIO_ACCESSORIO_OPTIONAL VALUES (?, ?);";

				stmt = conn.prepareStatement(query);

				for (EntityAccessorio accessorio_optional : noleggio.getAccessoriOptional()) {

					stmt.setInt(1, idNoleggio);
					stmt.setInt(2, accessorio_optional.getId());

					stmt.executeUpdate();
					
				}

			}catch(SQLException e) {

				throw new DAOException("Errore lettura proiezione");

			}finally {

				DBManager.closeConnection();

			}
			
		}catch(SQLException e) {
            
			throw new DBConnectionException("Errore di connessione DB");

		}

    }

}
