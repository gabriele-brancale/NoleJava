package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import entity.EntityClienteRegistrato;
import exception.DAOException;
import exception.DBConnectionException;

public class ClienteRegistratoDAO {

	public static EntityClienteRegistrato ricercaClienteRegistrato(String email, String password) throws DAOException, DBConnectionException {

		try {

			Connection conn = DBManager.getConnection();

			String query = "SELECT * FROM CLIENTE_REGISTRATO WHERE EMAIL=? AND PASSWORD=?;";

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

	public static EntityClienteRegistrato inserisciClienteRegistrato(String nome, String cognome, String email, String password, Date dataDiNascita, String numeroPatente) throws DAOException, DBConnectionException{

		try {

			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO CLIENTE_REGISTRATO VALUES (?, ?, ?, ?, ?, ?);";

			try {

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, nome);
				stmt.setString(2, cognome);
				stmt.setString(3, email);
				stmt.setString(4, password);
				stmt.setDate(5, dataDiNascita);
				stmt.setString(6, numeroPatente);

				stmt.executeQuery();

				stmt = conn.prepareStatement("LAST_INSERT_ID()");

				ResultSet result = stmt.executeQuery();

				result.next();

				return new EntityClienteRegistrato(result.getInt(0), nome, cognome, email, password, dataDiNascita, numeroPatente);

			}catch(SQLException e) {

				throw new DAOException("Errore inserimento ClienteRegistrato");

			} finally {

				DBManager.closeConnection();

			}
			
		}catch(SQLException e) {

			throw new DBConnectionException("Errore connessione database");

		}

	}

	public static boolean verificaClienteRegistrato(String email) throws DAOException, DBConnectionException{

		try {

			Connection conn = DBManager.getConnection();

			String query = "SELECT * FROM CLIENTE_REGISTRATO WHERE EMAIL=?;";

			try {

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, email);
				
				ResultSet result = stmt.executeQuery();

				if(result.next()){

					return true;

				}

				return false;

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
