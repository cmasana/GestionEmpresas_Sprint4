package custom_ui.components.buttons;

import custom_ui.colors.ColorsPalette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Clase SidebarButton: Define las características de los botones de la barra lateral izquierda
 * @author cmasana
 */
public class SidebarButton extends JButton implements MouseListener {
    // Paleta de colores
    private final ColorsPalette DYE = new ColorsPalette();

    // Constructor vacío
    public SidebarButton() {
        this.setBorder(null); // Sin bordes
        this.setContentAreaFilled(false); // Botón transparente
        this.setOpaque(true); // Opacidad
        this.setFont(new Font("Open Sans", Font.BOLD, 14)); // Fuente botón
        this.setBackground(null); // Color de fondo
        this.setForeground(DYE.getTEXTNORMAL()); // Color letra
        this.setPreferredSize(new Dimension(200, 40));
        this.setSize(200, 40);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar a cursor de mano
        this.setFocusable(false); // Quitar bordes alrededor de texto
        addMouseListener(this); // Añadir los eventos de ratón
    }

    // Constructor sobrecargado
    public SidebarButton(String title) {
        this.setBorder(null); // Sin bordes
        this.setContentAreaFilled(false); // Botón transparente
        this.setOpaque(true); // Opacidad
        this.setFont(new Font("Open Sans", Font.BOLD, 14)); // Fuente botón
        this.setBackground(null); // Color de fondo
        this.setForeground(DYE.getTEXTNORMAL()); // Color letra
        this.setPreferredSize(new Dimension(200, 40));
        this.setSize(200, 40);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar a cursor de mano
        this.setFocusable(false); // Quitar bordes alrededor de texto
        this.setText(title);
        addMouseListener(this); // Añadir los eventos de ratón
    }

    // Métodos para los eventos del ratón

    /**
     * Permite modificar los colores cuando hacemos clic en un botón
     * @deprecated
     * @param mouseEvent Evento de ratón
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // No lo utilizo
    }

    /**
     * Permite modificar el color de la fuente cuando hacemos clic en un botón
     * @param mouseEvent Evento de ratón
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        this.setForeground(DYE.getTEXTPRESSED());
    }

    /**
     * Permite modificar el color de la fuente cuando dejamos de hacer clic en un botón
     * @param mouseEvent Evento de ratón
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        this.setForeground(DYE.getTEXTRELEASED());
    }

    /**
     * Permite modificar el color de fondo y el de la fuente cuando pasamos el ratón por encima
     * @param mouseEvent Evento de ratón
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        this.setBackground(DYE.getHOVER());
        this.setForeground(DYE.getTEXTHOVER());
    }

    /**
     * Permite modificar el color de fondo y el de la fuente cuando el cursor abandona el botón
     * @param mouseEvent Evento de ratón
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.setBackground(DYE.getNORMAL());
        this.setForeground(DYE.getTEXTNORMAL());
    }

}
