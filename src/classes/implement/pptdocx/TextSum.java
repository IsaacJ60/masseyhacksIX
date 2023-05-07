package classes.implement.pptdocx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TextSum {

    CreateDocx doc;

    public TextSum(String filename) throws InterruptedException, IOException {

        String Script_Path = "src\\classes\\implement\\pptdocx\\main.py";
        ProcessBuilder Process_Builder = new
                ProcessBuilder("python",Script_Path)
                .inheritIO();

        Process Demo_Process = Process_Builder.start();
        Demo_Process.waitFor();

        BufferedReader Buffered_Reader = new BufferedReader(new InputStreamReader(Demo_Process.getInputStream()));

        doc = new CreateDocx();

        doc.createWord(filename);
    }

    public CreateDocx getDoc() {
        return doc;
    }
}