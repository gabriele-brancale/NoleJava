package boundary;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class SceltaAccessoriTest {
    
    @Test
    public void inputValido(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore:"; 

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-01-01\n2025-02-01\n1\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\nn\n5\n".getBytes()));   

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString();

        assertTrue(!actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void numeroMinoreDi1(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Input non valido, è possibile scegliere solo gli accessori elencati... Riprovare";   

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-01-01\n2025-02-01\n1\n1 -1\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\nn\n5\n".getBytes()));   

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
    public void numeroMaggioreDiN(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Input non valido, è possibile scegliere solo gli accessori elencati... Riprovare";   

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-01-01\n2025-02-01\n1\n1 6\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\nn\n5\n".getBytes()));   

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
    public void nessunAccessorioObbligatorio(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Input non valido, selezionare almeno un accessorio obbligatorio... Riprovare";   

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-01-01\n2025-02-01\n1\n3 4\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\nn\n5\n".getBytes()));   

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
    public void piuAccessoriObbligatori(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Input non valido, è possibile scegliere un solo accessorio obbligatorio... Riprovare";   

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-01-01\n2025-02-01\n1\n1 2\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\nn\n5\n".getBytes()));   

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
    public void listaConAlmenoUnAccessorioNonNumero(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore: Input non valido, inserire i numeri corrispondenti agli accessori desiderati... Riprovare";   

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-03-01\n2025-04-01\n1\n1 3 a !\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\nn\n5\n".getBytes()));   

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
