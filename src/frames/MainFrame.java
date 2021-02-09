package frames;

import custom.other.SidebarButton;
import custom.other.ColorsPalette;
import custom.panels.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Permite crear la ventana principal de nuestra aplicación con todas sus configuraciones
 * @author cmasana
 */
public class MainFrame extends JFrame {
    // Paleta de colores
    private final ColorsPalette DYE = new ColorsPalette();

    // Paneles UI
    private final JPanel sideBar = new JPanel();
    private final JPanel content = new JPanel();
    private final JPanel emptySpace = new JPanel();
    private final EmployeesContent eContent = new EmployeesContent();
    private final ProposalContent pContent = new ProposalContent();

    /**
     * Crea un objeto de la clase MainWindow
     */
    public MainFrame() {
        this.initComponents();
    }

    /**
     * Permite inicializar todos los componentes de nuestra ventana
     */
    private void initComponents() {
        // Cargamos configuración de la ventana
        this.windowSettings();

        // Añadimos barra lateral
        this.putSidebar();

        // Añadimos panel de contenido
        this.putContent();

        // Añadimos un espacio vacío (SOLO DISEÑO)
        this.putEmptySpace();
    }

    /**
     * Añade la configuración de la ventana
     */
    private void windowSettings() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Cerrar aplicación al hacer clic en X
        this.setTitle("Gestión de Empresas"); // Añadir título a ventana
        this.setSize(1600,900); // Establecer un tamaño por defecto
        this.setResizable(true); // No se puede redimensionar, también quita botón maximizar
        this.setLayout(new BorderLayout()); // Establecemos layout
    }

    /**
     * Añade la barra lateral, su configuración y sus respectivos componentes
     */
    private void putSidebar() {
        // Configuración barra lateral
        sideBar.setPreferredSize(new Dimension(200,900)); // Establecer un tamaño por defecto
        sideBar.setBackground(DYE.getSIDEBAR());

        // Cargamos componentes de la barra lateral
        this.sbLogo();
        this.sbBrand();
        this.sbButtons();

        // Añadimos barra lateral a ventana actual
        this.add(sideBar, BorderLayout.WEST);
    }

    /**
     * Permite añadir el logo de la barra lateral
     */
    private void sbLogo() {
        JLabel imageLogo = new JLabel(new ImageIcon("src/Images/logo.png"));
        imageLogo.setBorder(new EmptyBorder(50,0,20,0)); // Top, left, bottom, right
        sideBar.add(imageLogo);
    }

    /**
     * Permite añadir un título a la barra lateral
     */
    private void sbBrand() {
        JLabel appTitle = new JLabel("PROIECTUS");
        appTitle.setBorder(new EmptyBorder(0,0,150,0)); // Top, left, bottom, right
        appTitle.setFont(new Font("Open Sans", Font.BOLD, 26)); // Fuente del texto
        appTitle.setForeground(DYE.getTEXTNORMAL()); // Color de texto
        sideBar.add(appTitle);
    }

    /**
     * Permite añadir el bloque de contenido a la ventana principal
     */
    private void putContent() {
        content.setPreferredSize(new Dimension(1400,900));
        content.setBackground(DYE.getCONTENT());

        // Añadimos paneles que mostrarán el contenido, dependiendo del botón al que hagamos clic
        content.add(eContent);
        content.add(pContent);

        // Añadimos visibilidad por defecto a los paneles (hijo)
        eContent.setVisible(false);
        pContent.setVisible(false);

        // Añadimos panel (padre)
        this.add(content, BorderLayout.CENTER);
    }

    /**
     * Permite añadir una serie de botones a la barra lateral con sus respectivas acciones al hacer clic
     */
    private void sbButtons() {
        SidebarButton employeeBtn = new SidebarButton("Empleados");
        employeeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                eContent.setVisible(true);
                pContent.setVisible(false);
            }
        });
        sideBar.add(employeeBtn);

        SidebarButton proposalBtn = new SidebarButton("Propuestas");
        proposalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                eContent.setVisible(false);
                pContent.setVisible(true);
            }
        });
        sideBar.add(proposalBtn);
    }

    /**
     * Permite añadir un espacio vacío a la ventana (con fines meramente estéticos)
     */
    private void putEmptySpace() {
        emptySpace.setPreferredSize(new Dimension(1600, 1));
        emptySpace.setBackground(DYE.getTEXTNORMAL());
        this.add(emptySpace, BorderLayout.SOUTH);
    }
}
