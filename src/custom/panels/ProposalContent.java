package custom.panels;

import custom.tables.BasicConfig;
import mainclasses.database.EmployeeDB;
import mainclasses.database.EntityDB;
import mainclasses.entity.Entity;
import modules.CrudProposal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProposalContent extends ContentWindow {
    // Paneles
    private JPanel module;
    private JPanel management;
    private JPanel tablePanel;

    // Textfields (inputs)
    private JTextField txtName;
    private JTextField txtStartDate;

    // TextArea
    private JTextArea txtDescription;

    // ComboBox
    private static JComboBox cbEntity;

    // Tabla
    private JTable proposalTable;

    // Simula una DB con las entidades
    private final static EntityDB entityDB = new EntityDB();

    // Simula una DB con los empleados
    private final static EmployeeDB employeeDB = new EmployeeDB();

    // Constructor
    public ProposalContent() {
        super("GESTIÓN DE PROPUESTAS");
    }

    /**
     * Método que permite personalizar el contenido del apartado de empleados
     */
    @Override
    protected void putContentModule() {
        module = new JPanel();
        module.setLayout(new GridLayout(2,1));
        module.setBackground(DYE.getCONTENT());

        this.putManagementPanel();
        this.putTablesPanel();

        this.add(module, BorderLayout.CENTER);
    }

    /**
     * Añade un panel con una serie de botones y un formulario
     */
    private void putManagementPanel() {
        management = new JPanel();
        management.setLayout(new BorderLayout());

        this.putManagementButtons();
        this.putForm();
        this.putProjectsBtn();

        module.add(management);
    }

    /**
     * Añade un formulario con labels e inputs
     */
    private void putForm() {
        JPanel form = new JPanel();
        form.setBackground(DYE.getCONTENT());
        form.setLayout(new GridBagLayout());
        form.setBorder(new EmptyBorder(0,50,0,0)); // Top, left, bottom, right
        form.setPreferredSize(new Dimension(500, 400));

        // Restricciones GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels
        JLabel lbName = new JLabel("Título");
        gbc.insets = new Insets(10,0,0,50);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(lbName, gbc);

        txtName = new JTextField();
        lbName.setLabelFor(txtName);
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(txtName, gbc);

        JLabel lbDescription = new JLabel("Descripción");
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        form.add(lbDescription, gbc);

        txtDescription = new JTextArea();
        lbDescription.setLabelFor(txtDescription);
        gbc.ipady = 50; // Cambia la altura
        gbc.gridx = 0;
        gbc.gridy = 3;
        form.add(txtDescription, gbc);

        JLabel lbStartDate = new JLabel("Fecha de inicio (dd/mm/yyyy)");
        gbc.ipady = 0; // Reseteamos
        gbc.gridx = 0;
        gbc.gridy = 4;
        form.add(lbStartDate, gbc);

        txtStartDate = new JTextField();
        lbStartDate.setLabelFor(txtStartDate);
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 5;
        form.add(txtStartDate, gbc);

        JLabel lbEntity = new JLabel("Entidad");
        gbc.ipady = 0;
        gbc.gridx = 0;
        gbc.gridy = 6;
        form.add(lbEntity, gbc);

        cbEntity = new JComboBox(entityDB.listEntities());
        lbEntity.setLabelFor(cbEntity);
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 7;
        form.add(cbEntity, gbc);

        management.add(form, BorderLayout.CENTER);
    }


    /**
     * Añade los botones de la parte izquierda que permiten realizar toda la gestión de propuestas
     */
    private void putManagementButtons() {

        JPanel mButtonsProposal = new JPanel();
        mButtonsProposal.setLayout(new GridLayout(10, 1, 10,10));
        mButtonsProposal.setBorder(new EmptyBorder(110,0,0,0)); // Top, left, bottom, right
        mButtonsProposal.setBackground(DYE.getCONTENT());

        // Botón Crear
        JButton btnCreate = new JButton("CREAR");
        btnCreate.setPreferredSize(new Dimension(120, 35));
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudProposal.createProposal(proposalTable, txtName.getText(), txtDescription.getText(), txtStartDate.getText(), (Entity) cbEntity.getSelectedItem());
                cleanInputs();
            }
        });
        mButtonsProposal.add(btnCreate);

        // Botón editar
        JButton btnEdit = new JButton("MODIFICAR");
        btnEdit.setPreferredSize(new Dimension(120, 35));
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudProposal.editProposal(proposalTable, txtName.getText(), txtDescription.getText(), txtStartDate.getText(), cbEntity);
            }
        });
        mButtonsProposal.add(btnEdit);

        // Botón eliminar
        JButton btnDelete = new JButton("ELIMINAR");
        btnDelete.setPreferredSize(new Dimension(120, 35));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudProposal.deleteProposal(proposalTable);
            }
        });
        mButtonsProposal.add(btnDelete);

        // Botón vaciar lista
        JButton btnEmpty = new JButton("VACIAR");
        btnEmpty.setPreferredSize(new Dimension(120, 35));
        btnEmpty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudProposal.emptyAll(proposalTable);
            }
        });
        mButtonsProposal.add(btnEmpty);

        management.add(mButtonsProposal, BorderLayout.WEST);
    }

    /**
     * TO-DO: QUEDA POR HACER apartado con botones para Crear un proyecto a partir de una propuesta y botón para
     * visualizar los proyectos creados
     */
    private void putProjectsBtn() {
        JPanel sButtonsProject = new JPanel();
        sButtonsProject.setLayout(new GridLayout(10, 1, 10,10));
        sButtonsProject.setBorder(new EmptyBorder(110,0,0,0)); // Top, left, bottom, right
        sButtonsProject.setBackground(DYE.getCONTENT());

        // Botón Crear Proyecto
        JButton btnMakeProject = new JButton("CREAR PROYECTO");
        // Desactivado
        btnMakeProject.setEnabled(false);
        btnMakeProject.setPreferredSize(new Dimension(200, 35));
        btnMakeProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TO-DO: FALTA METODO
                cleanInputs();
            }
        });
        sButtonsProject.add(btnMakeProject);

        // Botón Ver Proyecto
        JButton btnShowProject = new JButton("VER PROYECTO");
        // Desactivado
        btnShowProject.setEnabled(false);
        btnShowProject.setPreferredSize(new Dimension(200, 35));
        btnShowProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TO-DO: FALTA METODO
                cleanInputs();
            }
        });
        sButtonsProject.add(btnShowProject);

        management.add(sButtonsProject, BorderLayout.EAST);

    }

    private void putTablesPanel() {
        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        this.putProposalTable();

        module.add(tablePanel);
    }

    /**
     * Añade una tabla y su configuración al panel actual
     */
    private void putProposalTable() {
        JPanel proposalPanelTable = new JPanel(new BorderLayout());
        proposalPanelTable.setBackground(DYE.getCONTENT());

        // Tabla
        JScrollPane scrollPane = new JScrollPane();
        proposalPanelTable.add(scrollPane, BorderLayout.CENTER);

        proposalTable = new JTable();

        // Modelo por defecto de la tabla
        proposalTable.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Título", "Descripción", "Fecha Inicio", "Entidad"
                }
        ));

        // Diseño básico de la tabla
        BasicConfig.initConfig(proposalTable);

        scrollPane.setViewportView(proposalTable);


        // Carga los datos de una fila cuando hacemos clic en ella
        proposalTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = proposalTable.getSelectedRow();

                // Condición que limpia datos cada vez que seleccionamos una fila (sino peta)
                if (selectedRow == -1) {
                    cleanInputs();
                }
                else {
                    txtName.setText(proposalTable.getValueAt(proposalTable.getSelectedRow(), 0).toString());
                    txtDescription.setText(proposalTable.getValueAt(proposalTable.getSelectedRow(), 1).toString());
                    txtStartDate.setText(proposalTable.getValueAt(proposalTable.getSelectedRow(), 2).toString());
                    cbEntity.setSelectedItem(proposalTable.getValueAt(proposalTable.getSelectedRow(), 3).toString());
                }
            }
        });

        tablePanel.add(proposalPanelTable, BorderLayout.CENTER);
    }


    /**
     * Método que permite limpiar el texto de los inputs (textfields y textarea)
     */
    private void cleanInputs() {
        txtName.setText("");
        txtDescription.setText("");
        txtStartDate.setText("");
    }
}
