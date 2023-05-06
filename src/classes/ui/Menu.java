package classes.ui;

import classes.implement.Conversion;
import classes.utility.Button;
import classes.utility.FontLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Menu {

    Conversion conversion;
    private ArrayList<Button> buttons = new ArrayList<>();
    private Button powerpoint, word;
    private Button modeswitcher;
    private Image bg_dark, bg_light, currBg;

    public Menu() {
        conversion = new Conversion();

        bg_dark = new ImageIcon("src/assets/images/darkmode.jpg").getImage().getScaledInstance(1280, 720, Image.SCALE_FAST);
        bg_light = new ImageIcon("src/assets/images/lightmode.jpg").getImage().getScaledInstance(1280, 720, Image.SCALE_FAST);
        currBg = bg_dark;

        modeswitcher = new Button("modeswitcher", 50, 600, 50, 50, Color.GRAY);

        powerpoint = new Button("powerpoint", 50, 160, 200, 80, Color.RED);
        word = new Button("word", 50, 300, 200, 80, Color.BLUE);

        buttons.add(powerpoint);
        buttons.add(word);
        buttons.add(modeswitcher);
    }

    public void draw(Graphics g, int mx, int my, boolean clicked) {

        g.drawImage(currBg, 0, 0, null);

        //drawing buttons
        for (Button b : buttons) {
            b.draw(g);

            if (b.clicked(mx, my, clicked)) {
                handleClick(b);
            }
        }

        // drawing other things
        g.setFont(FontLoader.fontTitle);
        g.setColor(Color.WHITE);
        g.drawString("PowerNote", 50, 100);
    }

    public void handleClick(Button b) {
        if (b.getName().equals("powerpoint")) {
            String path = fileChooser();
            if (path != null) {
                conversion.powerpointToWord(path);
            }
        } else if (b.getName().equals("word")) {
            String path = fileChooser();
            if (path != null) {
                conversion.wordToCard(path);
            }
        } else if (b.getName().equals("modeswitcher")) {
            if (currBg == bg_dark) {
                currBg = bg_light;
            } else {
                currBg = bg_dark;
            }
        }
    }

    public String fileChooser() {


        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
}
