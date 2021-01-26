package modules;

import custom.tables.BasicConfig;
import mainclasses.database.ProposalDB;
import mainclasses.entity.Entity;
import mainclasses.io.InputOutput;
import mainclasses.proposal.Proposal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * Clase CrudProposal: Implementa todos los métodos necesarios para la gestión de propuestas
 * @author cmasana
 */
public class CrudProposal {
    // Array de tipo ProposalDB
    private final static ProposalDB proposalList = new ProposalDB();


    /**
     * Permite crear propuestas
     * @param proposalTable tabla donde se visualizan las propuestas
     * @param name nombre de la propuesta
     * @param description descripción de la propuesta
     * @param startDate fecha de la propuesta
     * @param entity entidad de la propuesta
     */
    public static void createProposal(JTable proposalTable, String name, String description, String startDate, Entity entity) {

        // Si hay algún campo vacío
        if (name.isEmpty() || description.isEmpty() || startDate.isEmpty() || entity == null) {
            InputOutput.printError("Error: Por favor, rellene todos los campos");
        }
        else {
            // Creamos objeto de la clase Proposal
            Proposal prop = new Proposal(name, description, startDate, entity);

            // Lo añadimos al arraylist de tipo Proposal
            proposalList.addProposal(prop);

            // Actualizamos los datos en la tabla
            showData(proposalTable);
        }
    }


    /**
     * Permite eliminar propuestas
     * @param proposalTable tabla donde se visualizan las propuestas
     */
    public static void deleteProposal(JTable proposalTable) {

        // Guardamos el número de fila (coincide con la posición en el ArrayList)
        int row = proposalTable.getSelectedRow();

        // Si el resultado es mayor o igual a 0, eliminamos la propuesta
        if (row >= 0) {
            proposalList.removeProposal(row);

            // Actualizamos datos de la tabla
            showData(proposalTable);
        }
        // En caso contrario, mostramos un error por pantalla
        else {
            InputOutput.printError("Error: Selecciona una fila");
        }
    }

    /**
     * Permite vaciar toda la lista de propuestas
     * @param proposalTable tabla donde se visualizan las propuestas creadas
     */
    public static void emptyAll(JTable proposalTable) {

        // Almacenamos el nº total de filas que hay en la tabla
        int totalrows = proposalTable.getRowCount();

        if (totalrows != 0) {
            // Recorremos el arrayList y eliminamos 1 a 1 los registros
            for (int i = (totalrows - 1); i >= 0; i--) {
                proposalList.removeProposal(i);
            }

            // Actualizamos los datos de la tabla
            showData(proposalTable);
        }
        else {
            InputOutput.printError("Error: No hay ningún empleado creado");
        }
    }

    /**
     * Permite modificar propuestas
     * @param proposalTable tabla donde se visualizan las propuestas
     * @param name nombre de la propuesta
     * @param description descripción de la propuesta
     * @param startDate fecha de la propuesta
     * @param cbEntity comboBox con la lista de entidades
     */
    public static void editProposal(JTable proposalTable, String name, String description, String startDate , JComboBox cbEntity) {

        // Almacenamos el nº total de filas que hay en la tabla
        int totalRows = proposalTable.getRowCount();

        // Almacena el nº de fila seleccionado (coincide con el índice en el ArrayList)
        int selectedRow = proposalTable.getSelectedRow();

        // Almacena el item seleccionado del comboBox
        Entity itemSelected = (Entity) cbEntity.getSelectedItem();


        // Si no hay ninguna fila creada
        if (totalRows == 0) {
            InputOutput.printError("Error: No hay ningún empleado creado");
        }
        else {
            // Si no hay ninguna fila seleccionada
            if (selectedRow < 0) {
                InputOutput.printError("Error: No has seleccionado ninguna fila");
            }
            else {
                // Si los campos están vacíos
                if (name.isEmpty() || description.isEmpty() || startDate.isEmpty()) {
                    InputOutput.printError("Error: Por favor, rellene todos los campos");
                } else {
                    proposalList.getProposalFromDB(selectedRow).setName(name);
                    proposalList.getProposalFromDB(selectedRow).setDescription(description);
                    proposalList.getProposalFromDB(selectedRow).setStartDate(startDate);
                    proposalList.getProposalFromDB(selectedRow).setEntity(itemSelected);

                    // Actualizamos datos de la tabla
                    showData(proposalTable);
                }
            }
        }
    }

    /**
     * Muestra los datos actualizados en la tabla de propuestas
     * @param proposalTable tabla dónde se visualizan las propuestas creadas
     */
    public static void showData(JTable proposalTable) {

        // Creamos array de tipo string e inicializamos con el tamaño del ArrayList
        String[][] tabla = new String[proposalList.sizeProposalDB()][5];

        // Recorre la lista de Propuestas
        for(int i = 0; i < proposalList.sizeProposalDB(); i++) {

            // Datos de cada Empleado
            tabla[i][0] = proposalList.getProposalFromDB(i).getName();
            tabla[i][1] = proposalList.getProposalFromDB(i).getDescription();
            tabla[i][2] = proposalList.getProposalFromDB(i).getStartDate();
            tabla[i][3] = proposalList.getProposalFromDB(i).getEntity().toString();
        }

        // Añade los datos al modelo
        proposalTable.setModel(new DefaultTableModel(
                tabla,
                new String [] {
                        "Título", "Descripción", "Fecha Inicio", "Entidad"
                }
        ));

        // Diseño de la tabla
        BasicConfig.initConfig(proposalTable);
    }
}
