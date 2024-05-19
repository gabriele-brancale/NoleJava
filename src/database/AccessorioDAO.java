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
    
    public static ArrayList<EntityAccessorio> trovaAccessori() throws DAOException, DBConnectionException{

        ArrayList<EntityAccessorio> risultato = new ArrayList<EntityAccessorio>();

        try {

			Connection conn = DBManager.getConnection();

			try {

				String query = "SELECT * FROM ACCESSORIO";              // da vedere in base al diagramma delle classi

				PreparedStatement stmt = conn.prepareStatement(query);

				//stmt.setString(1, tipologia);

				//stmt.setInt(2, numeroPassegeri);

				//stmt.setDate(3, dataInizio);
                //stmt.setDate(4, dataFine);
                //stmt.setDate(5, dataInizio);
                //stmt.setDate(6, dataFine);

				ResultSet result = stmt.executeQuery();

                while(result.next()){

                    //risultato.add(new EntityImbarcazione(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getInt(6)));

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
