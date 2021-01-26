package custom.panels;

import javax.swing.*;
import java.awt.*;

public class MainContent extends ContentWindow {

    public MainContent() {
        super("MAIN");

        JLabel texto = new JLabel("ERROR 404 NOT FOUND ");
        texto.setFont(new Font("Open Sans", Font.BOLD, 36));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(texto);

        /*
         * LISTA DE MEJORAS
         * Asignar RegExp a los JTextfield de formularios
         * Optimizar JTables para personalizar sus modelos y así poder insertar imágenes de acciones y
         * desactivar celdas editables
         * FALTA toda la parte de proyectos (CRUD y botones con listeners)
         * Arreglar el diseño de algunos botones
         * Arreglar tamaño de los formularios
         * Arreglar problemas de JTextArea en ProposalContent
         * Probablemente muchas más
         */
    }
}