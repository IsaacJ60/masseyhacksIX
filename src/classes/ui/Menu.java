package classes.ui;

import classes.utility.Button;
import classes.utility.FontLoader;

import java.awt.*;
import java.util.ArrayList;

public class Menu {

    ArrayList<Button> buttons = new ArrayList<>();
    Button powerpoint;
    Button word;

    public Menu() {
        powerpoint = new Button("powerpoint", 50, 150, 200, 80, Color.RED);
        word = new Button("word", 50, 300, 200, 80, Color.BLUE);

        buttons.add(powerpoint);
        buttons.add(word);
    }

    public void draw(Graphics g, int mx, int my, boolean clicked) {

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
        g.drawString("PowerNote", 50, 50);
    }

    public void handleClick(Button b) {
        if (b.getName().equals("powerpoint")) {
            //do stuff for ppt
        } else if (b.getName().equals("word")) {
            //do stuff for word
        }
    }
}
