package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.EntityAccessorio;
import exception.DAOException;
import exception.DBConnectionException;

public class AccessorioDAO {
    
    public static ArrayList<EntityAccessorio> getAccessori() throws DAOException, DBConnectionException{

        ArrayList<EntityAccessorio> risultato = new ArrayList<EntityAccessorio>();

        try {

			Connection conn = DBManager.getConnection();

			try {

				String query = "SELECT * FROM ACCESSORIO_OBBLIGATORIO";

				PreparedStatement stmt = conn.prepareStatement(query);

				ResultSet result = stmt.executeQuery();

                while(result.next()){

                    risultato.add(new EntityAccessorio(result.getInt(1), result.getString(2), result.getString(3), result.getFloat(4), true));

                }

				query = "SELECT * FROM ACCESSORIO_OPTIONAL";

				stmt = conn.prepareStatement(query);

				result = stmt.executeQuery();

                while(result.next()){

                    risultato.add(new EntityAccessorio(result.getInt(1), result.getString(2), result.getString(3), result.getFloat(4), false));

                }

			}catch(SQLException e) {

				throw new DAOException("Errore lettura proiezione");

			}finally {

				DBManager.closeConnection();

			}
			
		}catch(SQLException e) {
            
			throw new DBConnectionException("Errore di connessione DB");

		}

		return risultato;

    }

}
