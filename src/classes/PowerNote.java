package classes;


import javax.swing.*;
import java.awt.*;

import classes.screens.MenuPanel;
import classes.utility.FontLoader;

public class PowerNote extends JFrame { // frame
    CardLayout card;

    // all screens
    private MenuPanel menuPanel;
    private String currPanel;

    // game dimensions
    private static final int WIDTH = 1280, HEIGHT = 720;

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public PowerNote() {
        super("PowerNote");

        // load all fonts
        FontLoader.loadFonts();

        // TODO: get last panel from close
        currPanel = "MENU";

        // adding panels to card layout
        card = new CardLayout();
        setLayout(card);

        menuPanel = new MenuPanel(this);
        add("MENU", menuPanel);

        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // set X to exit
        setVisible(true); // make panel visible
    }

    public void setCurrPanel(String panel) {
        currPanel = panel;
    }

    public String getCurrPanel() {
        return currPanel;
    }

    public void start() {
        // display current panel
        card.show(getContentPane(), currPanel);
    }

    public static void main(String[] args) {
        new PowerNote();
    }
}

