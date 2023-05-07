package classes.implement.pptdocx;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;

public class CreateDocx extends JPanel {
    String cornellfilepath;
    public void createWord(String filename, ArrayList<String> titles, ArrayList<ArrayList<String>> bulletpoints, boolean returnResults) throws IOException {

        if (returnResults) {
            cornellfilepath = getDirectoryPath("Select Save Directory for Cornell") + "\\" + filename + "_cornellnotes.docx";
        } else {
            cornellfilepath = "src\\assets\\temp\\" + filename + "_cornellnotes.docx";
        }

        if (!cornellfilepath.contains("null")) {
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

            CornellGen cornellnotes = new CornellGen(titles, bulletpoints, text, cornellfilepath);

            if (returnResults) {
                JOptionPane.showMessageDialog(this, "Word Files Successfully Generated");
            }
        }
    }

    public String getCornellFilePath() {
        return cornellfilepath;
    }

    public String getDirectoryPath(String s) {

        JFileChooser chooser;
        String choosertitle;

        int result;

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(s);
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