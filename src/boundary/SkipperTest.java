package boundary;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import database.DBManager;
import entity.EntityAccessorio;
import exception.DAOException;

public class SkipperTest {
    
    @Test
    public void patenteTrueSceltaS(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        System.setIn(new ByteArrayInputStream("3\n1\n3\n2024-07-01\n2024-08-01\n\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nolejava", "root", "root");

        try {

			String query = "SELECT SKIPPER FROM NOLEGGIO WHERE ;";

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

        String actual = os.toString().substring(os.toString().indexOf("Imbarcazioni disponibili:"), os.toString().indexOf("Premere [ENTER] per tornare al menu..."));

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

}
