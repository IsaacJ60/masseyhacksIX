package classes.implement.pptdocx;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

import classes.ui.Menu;
import classes.utility.ReadDocx;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;

public class CreateDocx extends JPanel {
    String wordfilepath;
    public void createWord(String filename) throws IOException {

        wordfilepath = getDirectoryPath() + "\\" + filename + ".docx";

        System.out.println(wordfilepath);

        // 1 - flashcard, 2 - notes
        XWPFDocument document1 = new XWPFDocument();
        XWPFDocument document2 = new XWPFDocument();
        FileOutputStream out1 = new FileOutputStream(new File(wordfilepath));
        FileOutputStream out2 = new FileOutputStream(new File("src/assets/text/notes2.docx"));

        XWPFParagraph paragraph1 = document1.createParagraph();
        XWPFRun run1 = paragraph1.createRun();

        XWPFParagraph paragraph2 = document2.createParagraph();
        XWPFRun run2 = paragraph2.createRun();
        String text = "";
        try {
            Scanner f = new Scanner(new BufferedReader(new FileReader("src\\assets\\text\\pptxsummarized.txt")));
            while (f.hasNextLine()){
                text += f.nextLine().strip()+"\n";
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        String[] lines = text.split("\n");
        for(int i=0;i<lines.length;i++){
            run1.addBreak();
            run1.setText(lines[i]);
        }
        document1.write(out1);
        out1.close();

        Menu.setLoading(false);
    }

    public String getWordfilepath() {
        return wordfilepath;
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