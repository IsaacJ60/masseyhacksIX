package classes.ui;

import classes.implement.Conversion;
import classes.screens.MenuPanel;
import classes.utility.Button;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Settings {

    private ArrayList<Button> buttons = new ArrayList<>();

    private Image clozeimg = new ImageIcon("src/assets/images/buttons/clozer.png").getImage();
    private Image basicimg = new ImageIcon("src/assets/images/buttons/basic.png").getImage();

    Button clozebutton, basicbutton;

    public Settings() {
        clozebutton = new Button("clozerbutton", 70, 150, 79, 79, Color.WHITE, clozeimg);
        basicbutton = new Button("basicbutton", 170, 150, 79, 79, Color.WHITE, basicimg);
        buttons.add(clozebutton);
        buttons.add(basicbutton);
    }

    public void draw(Graphics g, int mx, int my, boolean clicked, MenuPanel menu) throws IOException, InterruptedException {
        g.setColor(Color.BLACK);
        g.fillRoundRect(50,80,1185, 600, 50, 50);
        Font f = new Font("sans-serif", Font.PLAIN, 30);
        Font bigger = f.deriveFont(50);
        g.setFont(bigger);
        g.setColor(Color.WHITE);
        g.drawString("Settings", 70, 120);

        drawButtons(buttons, g, mx, my, clicked, menu);
    }

    public void drawButtons(ArrayList<Button> buttons, Graphics g, int mx, int my, boolean clicked, MenuPanel menu) throws IOException, InterruptedException {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).draw(g);

            if (buttons.get(i).clicked(mx, my, clicked)) {
                handleClick(buttons.get(i), menu);
            }
        }
    }

    public void handleClick(Button b, MenuPanel menu) throws IOException, InterruptedException {
        if (b.getName().equals("clozerbutton")) {
            Menu.setType("clozer");
            System.out.println("clozer");
            menu.setOpensettings(false);
        } else if (b.getName().equals("basicbutton")) {
            Menu.setType("basic");
            System.out.println("basic");
            menu.setOpensettings(false);
        }
    }
}
