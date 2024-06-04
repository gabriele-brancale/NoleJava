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
        
        out = "[!] Errore:"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-01-01\n2025-02-01\n1\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\nn\n5\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString();//Da chiedere a Gabriele

        assertTrue(!actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void numeroMinoreDi1(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore:"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-01-01\n2025-02-01\n1\n1 -1\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\nn\n5\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString();//Da chiedere a Gabriele

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void numeroMaggioreDiN(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore:"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-01-01\n2025-02-01\n1\n1 6\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\nn\n5\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString();//Da chiedere a Gabriele

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

    @Test
    public void nessunAccessorioObbligatorio(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore:"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-01-01\n2025-02-01\n1\n3 4\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\nn\n5\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString();//Da chiedere a Gabriele

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    } 

    @Test
    public void piuAccessoriObbligatori(){

        InputStream input_bak = System.in;
        PrintStream output_bak = System.out;

        String out;
        
        out = "[!] Errore:"; //Da chiedere a Gabriele

        System.setIn(new ByteArrayInputStream("4\n1\n3\n2025-01-01\n2025-02-01\n1\n1 2\n1 4\n1\nrobertomengoni@gmail.com\npippo2004\nn\n5\n".getBytes())); //Da chiedere a Gabriele

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(os);

        System.setOut(output);

        BoundaryCliente.main(new String[]{});

        String actual = os.toString();//Da chiedere a Gabriele

        assertTrue(actual.contains(out));

        System.setIn(input_bak);
        System.setOut(output_bak);

    }

}
