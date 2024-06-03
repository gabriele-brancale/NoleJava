package boundary;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class NoleggioTest {

    /* @Test
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

    } */
    
}
