package classes.implement.pptdocx;

import java.io.*;
import java.util.Scanner;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class CreateDocx {
    public void createWord() throws IOException {
        // 1 - flashcard, 2 - notes
        XWPFDocument document1 = new XWPFDocument();
        XWPFDocument document2 = new XWPFDocument();
        FileOutputStream out1 = new FileOutputStream(new File("src/assets/text/input.docx"));
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
    }
}