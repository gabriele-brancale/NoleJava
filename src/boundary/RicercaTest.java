package boundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthEditorPaneUI;

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
        
        out = "[!] Errore: Data di inizio noleggio non valida... Riprovare\r\n"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n1\n3\n01-07-2024\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString().substring(os.toString().indexOf("Imbarcazioni disponibili:"), os.toString().indexOf("Premere [ENTER] per tornare al menu...")); //Da chiedere a Gabriele

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }
    
    @Test
    public void dataInizioMinoreDellaDataAttuale() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Data di inizio noleggio non valida... Riprovare\r\n"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n1\n3\n2024-02-01\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = "[!] Errore: Data di inizio noleggio non valida... Riprovare";
        //Da chiedere a Gabriele

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void dataFineConFormatoNonValido() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Data di fine noleggio non valida... Riprovare\r\n"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n1\n3\n2024-07-01\n01-08-2024\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = "[!] Errore: Data di fine noleggio non valida... Riprovare";
        //Da chiedere a Gabriele

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void dataFineMinoreDiDataInizio() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Data di fine noleggio non valida... Riprovare\r\n"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n1\n3\n2024-02-04\n2024-02-01\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = "[!] Errore: Data di fine noleggio non valida... Riprovare";
        //Da chiedere a Gabriele

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void tipologiaImbarcazioneNonValida() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Tipologia di imbarcazione non valida... Riprovare\r\n"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n3\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = "[!] Errore: Tipologia di imbarcazione non valida... Riprovare";
        //Da chiedere a Gabriele

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void numeroPasseggeriNonValido() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Numero passeggeri non valido... Riprovare\r\n"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("3\n1\n-1\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = "[!] Errore: Numero passeggeri non valido... Riprovare";
        //Da chiedere a Gabriele

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }
}

public class SceltaTest {

    @Test
    public void tuttiInputValidiScelta() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "Accessori: \r\n";

        out += "\tObbligatori:";
        out += "\t\t1. servizio assicurativo:\r\n";
        out += "\t\t\tDescrizione: servizio assicurativo descrizione\r\n";
        out += "\t\t\tCosto: 50\r\n";
        out += "\t\t2. servizio assicurativo 2:\r\n";
        out += "\t\t\tDescrizione: servizio assicurativo 2 descrizione\r\n";
        out += "\t\t\tCosto: 50\r\n";

        out += "\tOptional:";
        out += "\t\t1. motore tender:\r\n";
        out += "\t\t\tDescrizione: motore tender descrizione\r\n";
        out += "\t\t\tCosto: 30\r\n";
        out += "\t\t2. motore tender 2:\r\n";
        out += "\t\t\tDescrizione: motore tender 2 descrizione\r\n";
        out += "\t\t\tCosto: 30\r\n";

        out += "Inserire gli accessori separando ogni valore con uno spazio: ";

        System.setIn(new ByteArrayInputStream("5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString().substring(os.toString().indexOf("Accessori:"), os.toString().indexOf("valore con uno spazio: "));
        //Da chiedere a Gabriele

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void inputNonIntero() {

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Input non intero... Riprovare.";


        System.setIn(new ByteArrayInputStream("\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = "[!] Errore: Input non intero... Riprovare.";
        //Da chiedere a Gabriele

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }
}
