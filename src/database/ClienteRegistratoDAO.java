package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityClienteRegistrato;
import exception.DAOException;
import exception.DBConnectionException;

public class ClienteRegistratoDAO {

	public static EntityClienteRegistrato readClienteRegistrato(String email) throws DAOException, DBConnectionException {
		EntityClienteRegistrato eC = null;

		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT * FROM CLIENTEREGISTRATO WHERE EMAIL=?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, email);

				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					eC = new EntityClienteRegistrato(result.getInt(1), result.getString(2), result.getString(3), email, result.getString(5));	
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura ClienteRegistrato");
			} finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}



		return eC;

	}

}
