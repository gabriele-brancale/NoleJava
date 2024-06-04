package boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;

import org.junit.Test;

public class NoleggioTest {

    @Test
    public void imbarcazioneValidaAccessoTrueCofermaS(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        System.setIn(new ByteArrayInputStream("1\nmariorossi@gmail.com\npippo2002\n4\n1\n3\n2025-06-01\n2025-07-01\n1\n1 3 4\ns\ns\n1234567812345678\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		try{

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nolejava", "root", "root");

			try {

				String query = "SELECT ID_NOLEGGIO, DATA_INIZIO, DATA_FINE, ID_CLIENTE, TARGA, ID_ACCESSORIO_OBBLIGATORIO FROM NOLEGGIO WHERE DATA_INIZIO=? AND DATA_FINE=? AND ID_CLIENTE=? AND TARGA=? AND ID_ACCESSORIO_OBBLIGATORIO=?;";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, Date.valueOf("2025-06-01"));
                stmt.setDate(2, Date.valueOf("2025-07-01"));
                stmt.setInt(3, 1);
                stmt.setString(4, "NA123");
                stmt.setInt(5, 1);

				ResultSet result = stmt.executeQuery();
				
				assertTrue(result.next());

                query = "SELECT ID_ACCESSORIO_OPTIONAL FROM NOLEGGIO_ACCESSORIO_OPTIONAL WHERE ID_NOLEGGIO=?";

                stmt = conn.prepareStatement(query);

                stmt.setInt(1, result.getInt(1));

                result = stmt.executeQuery();

                assertTrue(result.next());
                assertEquals(1, result.getInt(1));

                assertTrue(result.next());
                assertEquals(2, result.getInt(1));

                assertFalse(result.next());

			}catch(SQLException e) {

				assertTrue(false);

			}finally {

				conn.close();

			}

		}catch(SQLException e){

			assertTrue(false);

		}

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void imbarcazioneValidaAccessoFalseConfermaS(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-07-01\n2025-08-01\n1\n1 3 4\n1\nmariorossi@gmail.com\npippo2002\ns\ns\n1234567812345678\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		try{

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nolejava", "root", "root");

			try {

				String query = "SELECT ID_NOLEGGIO, DATA_INIZIO, DATA_FINE, ID_CLIENTE, TARGA, ID_ACCESSORIO_OBBLIGATORIO FROM NOLEGGIO WHERE DATA_INIZIO=? AND DATA_FINE=? AND ID_CLIENTE=? AND TARGA=? AND ID_ACCESSORIO_OBBLIGATORIO=?;";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, Date.valueOf("2025-07-01"));
                stmt.setDate(2, Date.valueOf("2025-08-01"));
                stmt.setInt(3, 1);
                stmt.setString(4, "NA123");
                stmt.setInt(5, 1);

				ResultSet result = stmt.executeQuery();
				
				assertTrue(result.next());

                query = "SELECT ID_ACCESSORIO_OPTIONAL FROM NOLEGGIO_ACCESSORIO_OPTIONAL WHERE ID_NOLEGGIO=?";

                stmt = conn.prepareStatement(query);

                stmt.setInt(1, result.getInt(1));

                result = stmt.executeQuery();

                assertTrue(result.next());
                assertEquals(1, result.getInt(1));

                assertTrue(result.next());
                assertEquals(2, result.getInt(1));

                assertFalse(result.next());

			}catch(SQLException e) {

				assertTrue(false);

			}finally {

				conn.close();

			}

		}catch(SQLException e){

			assertTrue(false);

		}

        System.setIn(input_bak);
        System.setOut(output_bak);

    }
    
    @Test
    public void imbarcazioneValidaAccessoTrueConfermaN(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        System.setIn(new ByteArrayInputStream("1\nmariorossi@gmail.com\npippo2002\n4\n1\n3\n2025-08-01\n2025-09-01\n1\n1 3 4\ns\nn\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		try{

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nolejava", "root", "root");

			try {

				String query = "SELECT DATA_INIZIO, DATA_FINE, ID_CLIENTE, TARGA, ID_ACCESSORIO_OBBLIGATORIO FROM NOLEGGIO WHERE DATA_INIZIO=? AND DATA_FINE=? AND ID_CLIENTE=? AND TARGA=? AND ID_ACCESSORIO_OBBLIGATORIO=?;";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, Date.valueOf("2025-08-01"));
                stmt.setDate(2, Date.valueOf("2025-09-01"));
                stmt.setInt(3, 1);
                stmt.setString(4, "NA123");
                stmt.setInt(5, 1);

				ResultSet result = stmt.executeQuery();

                assertFalse(result.next());

			}catch(SQLException e) {

				assertTrue(false);

			}finally {

				conn.close();

			}

		}catch(SQLException e){

			assertTrue(false);

		}

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void imbarcazioneValidaAccessoFalseConfermaN(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-10-01\n2025-11-01\n1\n1 3 4\n1\nmariorossi@gmail.com\npippo2002\ns\nn\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		try{

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nolejava", "root", "root");

			try {

				String query = "SELECT DATA_INIZIO, DATA_FINE, ID_CLIENTE, TARGA, ID_ACCESSORIO_OBBLIGATORIO FROM NOLEGGIO WHERE DATA_INIZIO=? AND DATA_FINE=? AND ID_CLIENTE=? AND TARGA=? AND ID_ACCESSORIO_OBBLIGATORIO=?;";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, Date.valueOf("2025-10-01"));
                stmt.setDate(2, Date.valueOf("2025-11-01"));
                stmt.setInt(3, 1);
                stmt.setString(4, "NA123");
                stmt.setInt(5, 1);

				ResultSet result = stmt.executeQuery();

                assertFalse(result.next());

			}catch(SQLException e) {

				assertTrue(false);

			}finally {

				conn.close();

			}

		}catch(SQLException e){

			assertTrue(false);

		}

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void confermaNonValida(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out = "[!] Errore: Input non valido, inserire o 's' o 'n'... Riprovare.";

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-11-01\n2025-12-01\n1\n1 3 4\n1\nmariorossi@gmail.com\npippo2002\ns\na\nn\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		String actual = os.toString();

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void sceltaImbarcazioneMinoreDi1(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out = "[!] Errore: Input non valido, inserire il numero corrispondente all'imbarcazione deisderata... Riprovare";

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-12-01\n2026-01-01\n0\n1\n1 3 4\n1\nmariorossi@gmail.com\npippo2002\ns\nn\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		String actual = os.toString();

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void sceltaImbarcazioneMaggioreDiN(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out = "[!] Errore: Input non valido, inserire il numero corrispondente all'imbarcazione deisderata... Riprovare";

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-01-01\n2026-02-01\n6\n1\n1 3 4\n1\nmariorossi@gmail.com\npippo2002\ns\nn\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		String actual = os.toString();

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void sceltaNonNumero(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out = "[!] Errore: Input non valido, inserire il numero corrispondente all'imbarcazione deisderata... Riprovare";

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-02-01\n2026-03-01\na\n1\n1 3 4\n1\nmariorossi@gmail.com\npippo2002\ns\nn\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		String actual = os.toString();

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

}
