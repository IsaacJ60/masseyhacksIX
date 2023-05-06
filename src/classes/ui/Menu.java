package classes.ui;

import classes.utility.Button;

import java.awt.*;
import java.util.ArrayList;

public class Menu {

    ArrayList<Button> buttons = new ArrayList<>();
    Button powerpoint;
    Button word;

    public Menu() {
        powerpoint = new Button("powerpoint", 50, 50, 100, 30, Color.RED);
        word = new Button("word", 50, 100, 100, 30, Color.BLUE);

        buttons.add(powerpoint);
        buttons.add(word);
    }

    public void draw(Graphics g, int mx, int my, boolean clicked) {
        for (Button b : buttons) {
            b.draw(g);

            if (b.clicked(mx, my, clicked)) {
                handleClick(b);
            }
        }
    }

    public void handleClick(Button b) {
        if (b.getName().equals("powerpoint")) {
            //do stuff for ppt
        } else if (b.getName().equals("word")) {
            //do stuff for word
        }
    }
}
