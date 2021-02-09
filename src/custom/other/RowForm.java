package custom.other;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class RowForm extends JPanel {
    // Paleta de colores
    private final ColorsPalette DYE = new ColorsPalette();

    // Componentes
    private JTextField txtInput;

    public RowForm() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(25, 200, 20, 200));
        this.setBackground(DYE.getCONTENT());
        this.setLabelTitle("");
        this.setInputField();
    }

    public RowForm(String labelTitle) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(25, 200, 20, 200));
        this.setBackground(DYE.getCONTENT());
        this.setLabelTitle(labelTitle);
        this.setInputField();
    }

    private void setLabelTitle(String labelTitle) {
        JLabel titleRow = new JLabel(labelTitle);
        titleRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleRow.setFont(new Font("Open Sans", Font.BOLD, 14));
        titleRow.setLabelFor(txtInput);
        this.add(titleRow);
    }

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
