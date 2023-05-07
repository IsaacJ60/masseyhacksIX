package classes.implement.pptdocx;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;

public class CreateDocx extends JPanel {
    String wordfilepath;
    String cornellfilepath;
    public void createWord(String filename, ArrayList<String> titles, ArrayList<ArrayList<String>> bulletpoints, boolean returnResults) throws IOException {

        if (returnResults) {
            wordfilepath = getDirectoryPath() + "\\" + filename + ".docx";
            cornellfilepath = getDirectoryPath() + "\\" + "cornellnotes" + ".docx";
        } else {
            wordfilepath = "src\\assets\\temp\\" + filename + ".docx";
            cornellfilepath = "src\\assets\\temp\\cornellnotes.docx";
        }

        // 1 - flashcard, 2 - notes
        XWPFDocument document1 = new XWPFDocument();
        FileOutputStream out1 = new FileOutputStream(new File(wordfilepath));

        XWPFParagraph paragraph1 = document1.createParagraph();
        XWPFRun run1 = paragraph1.createRun();

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

        CornellGen cornellnotes = new CornellGen(titles, bulletpoints, text, cornellfilepath);

        if (returnResults) {
            JOptionPane.showMessageDialog(this, "Word Files Successfully Generated");
        }
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