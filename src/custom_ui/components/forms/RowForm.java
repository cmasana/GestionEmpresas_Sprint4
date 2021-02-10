package custom_ui.components.forms;

import custom_ui.colors.ColorsPalette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Permite añadir una fila a un formulario con un formato personalizado
 */
public class RowForm extends JPanel {
    // Paleta de colores
    private final ColorsPalette DYE = new ColorsPalette();

    // Componentes
    private JTextField txtInput;

    public RowForm(String labelTitle) {
        this.initComponent();
        this.setLabelTitle(labelTitle);
        this.setInputField();
    }

    /**
     * Configuración predefinida para el componente
     */
    private void initComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(25, 75, 20, 300));
        this.setBackground(DYE.getCONTENT());
    }

    /**
     * Añade un JLabel con un título personalizado
     * @param labelTitle String con el título del JLabel
     */
    private void setLabelTitle(String labelTitle) {
        JLabel titleRow = new JLabel(labelTitle);
        titleRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleRow.setFont(new Font("Open Sans", Font.BOLD, 14));
        titleRow.setLabelFor(txtInput);
        this.add(titleRow);
    }

    /**
     * Añade un input para que el usuario introduzca datos
     */
    private void setInputField(){
        txtInput = new JTextField(10);
        txtInput.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtInput.setOpaque(false);
        txtInput.setBorder(new MatteBorder(0, 0, 1, 0, DYE.getTEXTNORMAL()));
        this.add(txtInput);
    }


    // Getters & Setters

    public JTextField getTxtInput() {
        return txtInput;
    }

    public void setTxtInput(String txtInput) {
        this.txtInput.setText(txtInput);
    }
}
