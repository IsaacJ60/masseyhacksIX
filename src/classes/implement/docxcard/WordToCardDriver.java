package classes.implement.docxcard;

import classes.ui.Menu;
import classes.utility.ReadDocx;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class WordToCardDriver extends JPanel {

    public WordToCardDriver(String path, String filename) throws InterruptedException, IOException {

        String text = ReadDocx.readDocx(path);

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src\\assets\\text\\input.txt")));
            out.println(text);
            out.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        String Script_Path0 = "src\\classes\\implement\\docxcard\\summarizer.py";
        ProcessBuilder Process_Builder0 = new
                ProcessBuilder("python",Script_Path0)
                .inheritIO();

        Process Demo_Process0 = Process_Builder0.start();
        Demo_Process0.waitFor();

        BufferedReader Buffered_Reader0 = new BufferedReader(new InputStreamReader(Demo_Process0.getInputStream()));


        String Script_Path1 = "src\\classes\\implement\\docxcard\\clozer.py";
        ProcessBuilder Process_Builder1 = new
                ProcessBuilder("python",Script_Path1)
                .inheritIO();

        Process Demo_Process1 = Process_Builder1.start();
        Demo_Process1.waitFor();

        BufferedReader Buffered_Reader1 = new BufferedReader(new InputStreamReader(Demo_Process1.getInputStream()));

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src\\assets\\text\\docxcard\\deckfilename.txt")));
            out.println(filename);
            out.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        String Script_Path2 = "src\\classes\\implement\\docxcard\\cardGeneratorCloze.py";
        ProcessBuilder Process_Builder2 = new
                ProcessBuilder("python",Script_Path2)
                .inheritIO();

        Process Demo_Process2 = Process_Builder2.start();
        Demo_Process2.waitFor();

        BufferedReader Buffered_Reader2 = new BufferedReader(new InputStreamReader(Demo_Process2.getInputStream()));

        String flashcardpath = getDirectoryPath() + "\\" + filename + ".apkg";

        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream("src\\assets\\temp\\temp.apkg");
            os = new FileOutputStream(flashcardpath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }

        JOptionPane.showMessageDialog(this, "Flashcard Files Successfully Generated");

    }

    public String getDirectoryPath() {

        JFileChooser chooser;
        String choosertitle;

        int result;

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose a location for your document");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }
}
