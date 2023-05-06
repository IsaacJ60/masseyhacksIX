package classes.utility;

import classes.screens.MenuPanel;

import java.awt.*;

public class Button {

    private String name;

    private int x, y, width, height;

    private Rectangle rect;

    private Color color;

    public Button(String name, int x, int y, int width, int height, Color col) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = col;
        this.rect = new Rectangle(x, y, width, height);
    }

    public String getName() {
        return name;
    }

    public boolean clicked(int mx, int my, boolean clicked) {
        if (rect.contains(new Point(mx, my))) {
            if (clicked) {
                MenuPanel.setClicked(false);
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
