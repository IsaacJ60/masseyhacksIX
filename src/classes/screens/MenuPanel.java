package classes.screens;

import classes.PowerNote;
import classes.ui.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel implements KeyListener, ActionListener, MouseListener {
    Timer timer;
    PowerNote powerNote;
    private boolean[] keys;
    private int WIDTH, HEIGHT;
    private int tarX, tarY, alpha = 255;

    //variables
    private boolean clicked;

    //menu
    private Menu menu;

    public MenuPanel(PowerNote a) {
        powerNote = a;
        keys = new boolean[KeyEvent.KEY_LAST+1];
        setPreferredSize(new Dimension(powerNote.getWIDTH(), powerNote.getHEIGHT()));
        setFocusable(true);
        requestFocus();
        // adding listener for events
        addMouseListener(this);
        addKeyListener(this);

        WIDTH = powerNote.getWIDTH();
        HEIGHT = powerNote.getHEIGHT();

        menu = new Menu();

        timer = new Timer(25, this); // manages frames
        timer.start();
    }

    // MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {;}
    @Override
    public void mouseExited(MouseEvent e) {;}
    @Override
    public void mousePressed(MouseEvent e) {;}
    @Override
    public void mouseReleased(MouseEvent e) {
        clicked = true;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keys[key] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keys[key] = false;
    }

    // ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {

        if (powerNote.getCurrPanel().equals("MENU")) {
            Point mouse = MouseInfo.getPointerInfo().getLocation(); // loc of mouse on screen
            Point offset = new Point(0,0);
            try {
                offset = getLocationOnScreen(); // loc of panel
            } catch (IllegalComponentStateException ex) {
                System.out.println(ex + " hmm");
            }
            tarX = mouse.x - offset.x;
            tarY = mouse.y - offset.y;
        }

        requestFocus();
        powerNote.start();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH, HEIGHT);
        menu.draw(g, tarX, tarY, clicked);
    }
}
