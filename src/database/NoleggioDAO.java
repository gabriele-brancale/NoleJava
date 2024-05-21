package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

				String query = "INSERT INTO NOLEGGIO VALUES (?, ?, ?, ?, ?, ?);";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, noleggio.dataInizio);
                stmt.setDate(2, noleggio.dataFine);
                stmt.setInt(3, noleggio.idCliente);
                stmt.setInt(4, noleggio.imbarcazione.id);
				stmt.setInt(5, noleggio.accessorio_obbligatorio.id);
				stmt.setBoolean(6, noleggio.skipper);

				stmt.executeQuery();

				query = "INSERT INTO NOLEGGIO-ACCESSORIO_OPTIONAL VALUES (?, ?);";

				stmt = conn.prepareStatement(query);

				for (EntityAccessorio accessorio_optional : noleggio.accessori_optional) {

					stmt.setInt(1, noleggio.idCliente);
					stmt.setInt(2, accessorio_optional.id);

					stmt.executeQuery();
					
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
