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
        out += "Premere [ENTER] per tornare al menu...";

        System.setIn(new ByteArrayInputStream("3\n1\n3\n2024-07-01\n2024-08-01\n\n5\n".getBytes()));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString().substring(os.toString().indexOf("Imbarcazioni disponibili:"), os.toString().indexOf("Premere [ENTER] per tornare al menu...")+38);

        assertEquals(out, actual);

        System.setIn(input_bak);
        System.setOut(output_bak);

    }
    
    @Test
    public void tuttiInputValidiTipologiaMotore() {

        BoundaryCliente boundaryCliente = new BoundaryCliente();



    }

}
