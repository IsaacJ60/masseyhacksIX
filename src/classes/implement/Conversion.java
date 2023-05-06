package classes.implement;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import classes.implement.pptdocx.TextSum;
import org.w3c.dom.Text;

import java.io.*;

public class Conversion {

    public Conversion() {

    }

    public String powerpointToWord(String path) throws IOException, InterruptedException {
        String createdPath = "";

        StringBuilder finalText = new StringBuilder();

        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(path));

        double fontToPixel = 1.34821777633;

        for (XSLFSlide slide : ppt.getSlides()) {
            for (XSLFShape shape : slide) {
                if (shape instanceof XSLFTextShape) {

                    // getting text
                    XSLFTextShape text = (XSLFTextShape) shape;

                    String[] usableText = text.getText().split("\n");

                    for (int i = 0; i < usableText.length; i++) {
                        finalText.append(usableText[i]).append("\n");
                    }
                }
            }
        }

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("E:\\compsci\\mhix\\src\\assets\\text\\pptxtext.txt")));
            out.println(finalText);
            out.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        TextSum textSum = new TextSum();

        return createdPath;
    }

    public String wordToCard(String path) {
        String createdPath = "";


        return createdPath;
    }

    public String powerpointToCard(String path) throws IOException, InterruptedException {
        String path1 = powerpointToWord(path);
        return wordToCard(path1);
    }

}
