package classes.implement.pptdocx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class CornellGen {
    public CornellGen(ArrayList<String> keyWords, ArrayList<ArrayList<String>> notes, String summary, String path) throws IOException {
        XWPFDocument document = new XWPFDocument();
        // XWPFDocument document2 = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File(path));
        //FileOutputStream out2 = new FileOutputStream(new File("notes2.docx"));

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        XWPFParagraph mainIdeaPara = document.createParagraph();
        XWPFRun mainIdearun = mainIdeaPara.createRun();

        XWPFParagraph notesPara = document.createParagraph();
        XWPFRun notesRun = notesPara.createRun();

        XWPFTable table = document.createTable();

        //  XWPFParagraph []paraArr = new XWPFParagraph[keyWords.length];
        // XWPFRun [] notesArr = new XWPFRun[keyWords.length];

        XWPFTableRow[] rowList = new XWPFTableRow [keyWords.size()];
        XWPFTableRow titleRow1 = table.getRow(0);
        titleRow1.getCell(0).setText("Main Ideas");
        titleRow1.getCell(0).getParagraphs().get(0).getRuns().get(0).setBold(true);
        titleRow1.addNewTableCell().setText("Notes");
        titleRow1.getCell(1).getParagraphs().get(0).getRuns().get(0).setBold(true);

        for (int i=0;i<keyWords.size();i++){
            rowList[i] = table.createRow();
            rowList[i].getCell(0).setText(keyWords.get(i));
            // XWPFRun runn = rowList[i].getCell(1).getParagraphs().get(0).createRun();
            for (int j=0;j<notes.get(i).size();j++){
                tableText(rowList[i].getCell(1).getParagraphs(), "- "+notes.get(i).get(j));
            }
            //System.out.println(runn.getText(notes[i].length-1));
            //runn.setText("",2);
        }
        run.setText(summary);
        document.write(out);
        out.close();
    }
    private static void tableText(List<XWPFParagraph> paragraph, String text) {
        XWPFRun run = paragraph.get(0).createRun();
        run.setText(text);
        run.addBreak();
        run.setText("");
    }
}