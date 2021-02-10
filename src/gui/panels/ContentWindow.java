package gui.panels;

import custom_ui.colors.ColorsPalette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class ContentWindow extends JPanel {
    // Paleta de colores
    protected final ColorsPalette DYE = new ColorsPalette();

    protected String contentTitle;
    protected String contentButton;
    protected JPanel contentModule;

    public ContentWindow(String contentTitle) {
        this.initComponents(contentTitle);
    }

    protected void initComponents(String contentTitle) {
        this.panelSettings();
        this.panelContent(contentTitle);
    }

    protected void panelSettings() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1400,900));
        this.setBorder(new EmptyBorder(10,20,10,10)); // Top, left, bottom, right
        this.setBackground(DYE.getCONTENT());
        this.setVisible(true);
    }

    protected void putContentModule() {
        JPanel module = new JPanel();
        module.setBackground(DYE.getCONTENT());
        this.add(module, BorderLayout.CENTER);
    }

    protected void panelContent(String contentTitle) {
        JLabel title = new JLabel(contentTitle);
        title.setFont(new Font("Open Sans", Font.BOLD, 20));
        title.setForeground(DYE.getTEXTNORMAL());
        title.setHorizontalAlignment(JLabel.CENTER);
        this.add(title, BorderLayout.NORTH);

        this.putContentModule();
    }
}
