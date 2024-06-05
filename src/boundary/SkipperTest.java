package boundary;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class SkipperTest {
    
    @Test
    public void patenteTruePostiLiberiPresentiSceltaS(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2027-11-01\n2027-12-01\n1\n1 4\n1\nmariorossi@gmail.com\npippo2002\ns\ns\n1234567812345678\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		try{

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nolejava", "root", "root");

			try {

				String query = "SELECT SKIPPER FROM NOLEGGIO WHERE DATA_INIZIO=? AND DATA_FINE=?;";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, Date.valueOf("2027-11-01"));
				stmt.setDate(2, Date.valueOf("2027-12-01"));

				ResultSet result = stmt.executeQuery();
				
				if(result.next()){

					boolean c = result.getBoolean(1);

					assertTrue(c);

				}else{

                    assertTrue(false);

                }

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
	public void patenteTruePostiLiberiPresentiSceltaN(){

		InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        System.setIn(new ByteArrayInputStream("4\n2\n3\n2024-08-01\n2024-09-01\n1\n1 4\n1\nmariorossi@gmail.com\npippo2002\nn\ns\n1234567812345678\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		try{

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nolejava", "root", "root");

			try {

				String query = "SELECT SKIPPER FROM NOLEGGIO WHERE DATA_INIZIO=? AND DATA_FINE=?;";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, Date.valueOf("2024-08-01"));
				stmt.setDate(2, Date.valueOf("2024-09-01"));

				ResultSet result = stmt.executeQuery();
				
				if(result.next()){

					boolean c = result.getBoolean(1);

					assertFalse(c);

				}else{

                    assertTrue(false);

                }

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
    public void patenteTruePostiLiberiMancanti(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        System.setIn(new ByteArrayInputStream("4\n1\n4\n2024-11-01\n2024-12-01\n1\n1 4\n1\nmariorossi@gmail.com\npippo2002\ns\n1234567812345678\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		try{

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nolejava", "root", "root");

			try {

				String query = "SELECT SKIPPER FROM NOLEGGIO WHERE DATA_INIZIO=? AND DATA_FINE=?;";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, Date.valueOf("2024-11-01"));
				stmt.setDate(2, Date.valueOf("2024-12-01"));

				ResultSet result = stmt.executeQuery();
				
				if(result.next()){

					boolean c = result.getBoolean(1);

					assertFalse(c);

				}else{

                    assertTrue(false);

                }

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
	public void patenteTruePostiLiberiPresentiSceltaNonValida(){

		InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

		String out = "[!] Errore: Input non valido... Riprovare";

        System.setIn(new ByteArrayInputStream("4\n2\n3\n2024-10-01\n2024-11-01\n1\n1 4\n1\nmariorossi@gmail.com\npippo2002\na\ns\nn\n5\n".getBytes()));

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
	public void patenteFalsePostiLiberiPresenti(){

		InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2024-09-01\n2024-10-01\n1\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\ns\n1234567812345678\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

		try{

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nolejava", "root", "root");

			try {

				String query = "SELECT SKIPPER FROM NOLEGGIO WHERE DATA_INIZIO=? AND DATA_FINE=?;";

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, Date.valueOf("2024-09-01"));
				stmt.setDate(2, Date.valueOf("2024-10-01"));

				ResultSet result = stmt.executeQuery();
				
				if(result.next()){

					boolean c = result.getBoolean(1);

					assertTrue(c);

				}else{

                    assertTrue(false);

                }

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
	public void patenteFalsePostiLiberiMancanti(){

		InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

		String out = "[#] Attenzione: Siccome non si dispone di patente, non ci sono posti sufficienti anche per lo skipper";

        System.setIn(new ByteArrayInputStream("4\n1\n4\n2027-01-01\n2027-02-01\n1\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\n5\n".getBytes()));

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
