package mainclasses.io;

import javax.swing.*;

/**
 * Grup Individual Sprint 3 2020 - Carlos Masana -
 *
 * Biblioteca de Entradas / Salidas de la aplicación
 */
public class InputOutput {

    /**
     * Muestra por pantalla un cuadro de alerta con el mensaje indicado
     * @param message mensaje que se muestra por pantalla
     */
    public static void printError(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Vacía los textfields indicados
     * @param field1 primer textfield
     * @param field2 segundo textfield
     * @param field3 tercer textfield
     * @param field4 cuarto textfield
     */
    public static void cleanInputs(JTextField field1, JTextField field2, JTextField field3, JTextField field4) {
        field1.setText("");
        field2.setText("");
        field3.setText("");
        field4.setText("");
    }
}
