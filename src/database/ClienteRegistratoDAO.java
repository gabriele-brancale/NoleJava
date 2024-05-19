package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityClienteRegistrato;
import exception.DAOException;
import exception.DBConnectionException;

public class ClienteRegistratoDAO {

	public static EntityClienteRegistrato ricercaClienteRegistrato(String email, String password) throws DAOException, DBConnectionException {

		try {

			Connection conn = DBManager.getConnection();

			String query = "SELECT * FROM CLIENTEREGISTRATO WHERE EMAIL=? AND PASSWORD=?;";

			try {

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, email);
				stmt.setString(2, password);

				ResultSet result = stmt.executeQuery();

				if(result.next()) {

					return new EntityClienteRegistrato(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getDate(6), result.getString(7));	
				
				}

				return null;

			}catch(SQLException e) {

				throw new DAOException("Errore lettura ClienteRegistrato");

			} finally {

				DBManager.closeConnection();

			}
			
		}catch(SQLException e) {

			throw new DBConnectionException("Errore connessione database");

		}

	}

	public static void inserisciClienteRegistrato(EntityClienteRegistrato clienteRegistrato) throws DAOException, DBConnectionException{

		try {

			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO CLIENTEREGISTRATO VALUES (?, ?, ?, ?, ?, ?, ?);";

			try {

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setInt(1, clienteRegistrato.idClienteRegistrato);
				stmt.setString(2, clienteRegistrato.nome);
				stmt.setString(3, clienteRegistrato.cognome);
				stmt.setString(4, clienteRegistrato.email);
				stmt.setString(5, clienteRegistrato.password);
				stmt.setDate(6, clienteRegistrato.dataDiNascita);
				stmt.setString(7, clienteRegistrato.datiPatentente);

				stmt.executeQuery();

			}catch(SQLException e) {

				throw new DAOException("Errore inserimento ClienteRegistrato");

			} finally {

				DBManager.closeConnection();

			}
			
		}catch(SQLException e) {

			throw new DBConnectionException("Errore connessione database");

		}

	}

}
