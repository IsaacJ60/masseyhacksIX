package classes.ui;

import classes.implement.Conversion;
import classes.utility.Button;
import classes.utility.FontLoader;
import org.apache.logging.log4j.core.appender.db.jdbc.FactoryMethodConnectionSource;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {

    private ArrayList<Button> buttons = new ArrayList<>();
    private Button powerpoint, word, powerpointtocard;
    private Button modeswitcher;
    private Image bg_dark, bg_light, currBg, modeimg;
    private Image df, pd, pf;

    public Menu() {

        // image from https://www.flaticon.com/authors/icon-hubs
        modeimg = new ImageIcon("src/assets/images/dark-mode.png").getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);

        df = new ImageIcon("src/assets/images/buttons/df.png").getImage();
        pd = new ImageIcon("src/assets/images/buttons/pd.png").getImage();
        pf = new ImageIcon("src/assets/images/buttons/pf.png").getImage();

        bg_dark = new ImageIcon("src/assets/images/darkmode.png").getImage();
        bg_light = new ImageIcon("src/assets/images/lightmode.png").getImage();
        currBg = bg_dark;

        modeswitcher = new Button("modeswitcher", 20, 20, 50, 50, Color.GRAY, modeimg);

        powerpoint = new Button("powerpoint", 200, 550, 200, 80, Color.RED, pd);
        word = new Button("word", 550, 550, 200, 80, Color.BLUE, df);
        powerpointtocard = new Button("powerpointtocard", 900, 550, 200, 80, Color.GREEN, pf);

        buttons.add(powerpoint);
        buttons.add(word);
        buttons.add(powerpointtocard);
        buttons.add(modeswitcher);
    }

    public void draw(Graphics g, int mx, int my, boolean clicked) throws IOException, InterruptedException {

        g.drawImage(currBg, 0, 0, null);

        //drawing buttons
        for (Button b : buttons) {
            b.draw(g);

            if (b.clicked(mx, my, clicked)) {
                handleClick(b);
            }
        }
    }

    public void handleClick(Button b) throws IOException, InterruptedException {
        if (b.getName().equals("powerpoint")) {
            String path = fileChooser();
            if (path != null) {
                Conversion.powerpointToWord(path, true);
            }
        } else if (b.getName().equals("word")) {
            String path = fileChooser();
            if (path != null) {
                Conversion.wordToCard(path);
            }
        } else if (b.getName().equals("powerpointtocard")) {
            String path = fileChooser();
            if (path != null) {
                Conversion.powerpointToCard(path);
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
