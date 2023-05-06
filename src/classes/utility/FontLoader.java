package classes.utility;

import classes.PowerNote;
import classes.screens.MenuPanel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    public static Font fontTitle;

    public static void loadFonts() {

        // normal-sized font
        String fName3 = "src\\assets\\fonts\\arcadeFont.ttf";
        InputStream is3 = MenuPanel.class.getResourceAsStream(fName3);
        try {
            assert is3 != null;
            fontTitle = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(30f);
        } catch(IOException ex){
            System.out.println(ex + "text font");
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }

    }
}
