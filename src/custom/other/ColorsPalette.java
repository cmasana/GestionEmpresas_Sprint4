package custom.other;

import java.awt.*;

/**
 * Permite obtener los colores de nuestra paleta personalizada
 * @author cmasana
 */
public class ColorsPalette {
    // Colores para contenedores de la app
    private final Color SIDEBAR = new Color(240,165,0);
    private final Color CONTENT = new Color(244,244,244);

    // Colores de fondo para botones (SideBar)
    private final Color NORMAL = new Color(240,165,0);
    private final Color HOVER = new Color(207,117,0);

    // Colores para texto de botones (SideBar)
    private final Color TEXTHOVER = new Color(244,244,244);
    private final Color TEXTNORMAL = new Color(26,28,32);
    private final Color TEXTPRESSED = new Color(207, 117,0);
    private final Color TEXTRELEASED = new Color(244,244,244);

    // Getters
    public Color getSIDEBAR() {
        return SIDEBAR;
    }

    public Color getCONTENT() {
        return CONTENT;
    }

    public Color getHOVER() {
        return HOVER;
    }

    public Color getTEXTNORMAL() {
        return TEXTNORMAL;
    }

    public Color getNORMAL() {
        return NORMAL;
    }

    public Color getTEXTHOVER() {
        return TEXTHOVER;
    }

    public Color getTEXTPRESSED() {
        return TEXTPRESSED;
    }

    public Color getTEXTRELEASED() {
        return TEXTRELEASED;
    }
}
