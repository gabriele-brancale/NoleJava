package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.EntityNoleggio;

import exception.DAOException;
import exception.DBConnectionException;

public class NoleggioDAO {
    
    public static void inserisciNoleggio(EntityNoleggio noleggio) throws DAOException, DBConnectionException{

		try {

			Connection conn = DBManager.getConnection();

			try {

				String query = "INSERT INTO NOLEGGIO VALUES (?, ?, ?, ?);";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, noleggio.dataInizio);
                stmt.setDate(2, noleggio.dataFine);
                stmt.setInt(3, noleggio.idCliente);
                stmt.setInt(4, noleggio.idImbarcazione);

				stmt.executeQuery();

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
