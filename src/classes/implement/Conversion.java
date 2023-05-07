package classes.implement;

import classes.implement.docxcard.WordToCardDriver;
import classes.ui.Menu;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import classes.implement.pptdocx.TextSum;

import java.io.*;

public class Conversion {

    public static String powerpointToWord(String path) throws IOException, InterruptedException {

        Menu.setLoading(true);

        String createdPath, filename = "";

        for (int i = path.length()-1; i >= 0; i--) {
            if (path.charAt(i) == '\\') {
                filename = path.substring(i+1, path.length()-5);
                break;
            }
        }

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
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src\\assets\\text\\pptxtext.txt")));
            out.println(finalText);
            out.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        TextSum textSum = new TextSum(filename);

        createdPath = textSum.getDoc().getWordfilepath();

        return createdPath;
    }

    public static String wordToCard(String path) throws IOException, InterruptedException {

        Menu.setLoading(true);

        String createdPath = "";

        String filename = "";

        for (int i = path.length()-1; i >= 0; i--) {
            if (path.charAt(i) == '\\') {
                filename = path.substring(i+1, path.length()-5);
                break;
            }
        }

        WordToCardDriver wordcard = new WordToCardDriver(path, filename);

        return createdPath;
    }

    public static String powerpointToCard(String path) throws IOException, InterruptedException {

        Menu.setLoading(true);

        String path1 = powerpointToWord(path);
        return wordToCard(path1);
    }

}
