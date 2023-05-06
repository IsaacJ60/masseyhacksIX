package classes.implement;

public class Conversion {

    public Conversion() {

    }

    public String powerpointToWord(String path) {
        String createdPath = "";


        return createdPath;
    }

    public String wordToCard(String path) {
        String createdPath = "";


        return createdPath;
    }

    public String powerpointToCard(String path) {
        String path1 = powerpointToWord(path);
        return wordToCard(path1);
    }

}
