package modules;

import mainclasses.database.EmployeeDB;
import mainclasses.database.ProjectDB;
import mainclasses.database.ProposalDB;
import mainclasses.proposal.Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Clase CrudProject: Implementa los métodos necesarios para realizar la gestión de proyectos
 */
public class CrudProject {
    // Array de tipo ProposalDB
    private final static ProposalDB proposalList = new ProposalDB();

    // Array de tipo EmpleadoDB
    private final static EmployeeDB employeeList = new EmployeeDB();

    // Array de tipo PropuestaDB
    private final static ProjectDB projectList = new ProjectDB();

    public static void createProject(JTable proposalTable, String title) {
        JLabel lbProposal;
        JLabel lbEmployee;
        JTextField txtTitle;

        JPanel mainPanel;

        // Almacena un entero, necesario para Diálogo de confirmación
        int resultado;

        // Fila seleccionada
        int selectedRow = proposalTable.getSelectedRow();

        mainPanel = new JPanel(new GridLayout(0,1));
        mainPanel.setBorder(new EmptyBorder(50,50,50,50));

        lbProposal = new JLabel("Título Propuesta");
        txtTitle = new JTextField(50);
        txtTitle.setEditable(false);
        txtTitle.setText(title);


        // TO-DO: No me ha dado tiempo, queda sin hacer
        lbEmployee = new JLabel("Jefe Proyecto");
        JTable employeeTable = new JTable();


        // Añadimos componentes al panel principal
        mainPanel.add(lbProposal);
        mainPanel.add(txtTitle);
        mainPanel.add(lbEmployee);

        // Mostramos diálogo de confirmación
        resultado = JOptionPane.showConfirmDialog(null, mainPanel, "Selecciona un jefe de proyecto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // No se puede utilizar un listener si utilizamos showxxxxDialog
        if (resultado == 0) {
            // Si el resultado es 0, significa que el usuario ha hecho clic en OK. Guardamos el objeto de tipo Project
            Project project = new Project(proposalList.getProposalFromDB(selectedRow).getName(), proposalList.getProposalFromDB(selectedRow).getDescription(), employeeList.getEmployeeFromDB(employeeTable.getSelectedRow()));

            projectList.addProject(project);
        }
    }
}
