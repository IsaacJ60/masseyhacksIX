package classes.implement;

import classes.implement.docxcard.WordToCardDriver;
import classes.ui.Menu;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import classes.implement.pptdocx.TextSum;

import java.io.*;
import java.util.ArrayList;

public class Conversion {

    public static String powerpointToWord(String path, boolean returnResults) throws IOException, InterruptedException {

        if (path.endsWith(".pptx")) {
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

            // stuff for cornell notes
            ArrayList<String> titles = new ArrayList<>();
            ArrayList<ArrayList<String>> bulletpoints = new ArrayList<>();

            int slideNum = 0;

            for (XSLFSlide slide : ppt.getSlides()) {
                slideNum++;
                bulletpoints.add(new ArrayList<>());
                bulletpoints.add(new ArrayList<>());
                for (XSLFShape shape : slide) {
                    if (shape instanceof XSLFTextShape) {

                        // getting text
                        XSLFTextShape text = (XSLFTextShape) shape;

                        String[] usableText = text.getText().split("\n");

                        // finding font
                        text.setWordWrap(false);

                        int font = (int)((text.getTextHeight()/usableText.length) / fontToPixel);
                        if (font > 40 && usableText.length == 1) {
                            titles.add(usableText[0]);
                        } else {
                            for (int i = 0; i < usableText.length; i++) {
                                if (!usableText[i].equals("\n") || !usableText[i].equals(" ") || !usableText[i].equals("")) {
                                    bulletpoints.get(slideNum).add(usableText[i]);
                                }
                            }
                        }

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

            TextSum textSum = new TextSum(filename, titles, bulletpoints, returnResults);

            createdPath = textSum.getDoc().getWordfilepath();

            return createdPath;
        }
        return null;
    }

    public static String wordToCard(String path) throws IOException, InterruptedException {

        if (path.endsWith(".docx")) {
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
        return null;
    }

    public static String powerpointToCard(String path) throws IOException, InterruptedException {
        if (path.endsWith(".pptx")) {
            String path1 = powerpointToWord(path, false);
            return wordToCard(path1);
        }
        return null;
    }

}
