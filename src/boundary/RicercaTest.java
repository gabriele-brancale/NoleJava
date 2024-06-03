package boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

public class RicercaTest {

    @Test
    public void tuttiInputValidiTipologiaVela() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;

        out = "Imbarcazioni disponibili:\r\n";
        out += " La Bellissima:\r\n";
        out += "  Capienza: 4\r\n";
        out += "  Costo: 30.0\r\n";
        out += "\r\n";

        System.setIn(new ByteArrayInputStream("3\n1\n3\n2024-07-01\n2024-08-01\n\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString().substring(os.toString().indexOf("Imbarcazioni disponibili:"), os.toString().indexOf("Premere [ENTER] per tornare al menu..."));

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }
    
    @Test
    public void tuttiInputValidiTipologiaMotore() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "Imbarcazioni disponibili:\r\n";
        out += " Amanda:\r\n";
        out += "  Capienza: 6\r\n";
        out += "  Costo: 50.0\r\n";
        out += "\r\n";

        System.setIn(new ByteArrayInputStream("3\n2\n3\n2024-07-01\n2024-08-01\n\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString().substring(os.toString().indexOf("Imbarcazioni disponibili:"), os.toString().indexOf("Premere [ENTER] per tornare al menu..."));

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void dataInizioConFormatoNonValido() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Data di inizio noleggio non valida... Riprovare"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n1\n3\n01-07-2024\n2024-08-01\n2024-09-01\n\n5\n".getBytes())); //Da chiedere a Gabriele

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
    public void dataInizioMinoreDellaDataAttuale() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Data di inizio noleggio non valida... Riprovare"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n1\n3\n2024-02-01\n2024-08-01\n2024-09-01\n\n5\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString();
        //Da chiedere a Gabriele

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void dataFineConFormatoNonValido() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Data di fine noleggio non valida... Riprovare"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n1\n3\n2024-08-01\n01-08-2024\n2024-09-01\n\n5\n".getBytes())); //Da chiedere a Gabriele

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
    public void dataFineMinoreDiDataInizio() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Input non valido... Riprovare"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n1\n3\n2024-08-01\n2024-07-01\n2024-08-01\n2024-09-01\n\n5\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString();
        //Da chiedere a Gabriele

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void tipologiaImbarcazioneNonValida() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Tipologia di imbarcazione non valida... Riprovare"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n3\n1\n3\n2024-08-01\n2024-09-01\n\n5\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString();
        //Da chiedere a Gabriele

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void numeroPasseggeriNonValido() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Numero passeggeri non valido... Riprovare"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n1\n-1\n3\n2024-08-01\n2024-09-01\n\n5\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString();
        //Da chiedere a Gabriele

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

}