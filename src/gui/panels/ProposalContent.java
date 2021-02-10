package gui.panels;

import custom_ui.components.buttons.ImageButton;
import custom_ui.components.forms.*;
import custom_ui.tables.*;
import mainclasses.database.EmployeeDB;
import mainclasses.database.EntityDB;
import mainclasses.entity.Entity;
import modules.CrudProposal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProposalContent extends ContentWindow {
    // Paneles
    private JPanel module;
    private JPanel management;
    private JPanel tablePanel;

    // Formulario
    private RowForm rowTitle;
    private RowForm rowDescription;
    private RowForm rowStartDate;

    // ComboBox
    private static JComboBox<Entity> cbEntity;

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
        management.setBorder(new EmptyBorder(0,50,0,0));
        management.setBackground(DYE.getCONTENT());

        this.putManagementButtons();
        this.putForm();

        module.add(management);
    }

    /**
     * Añade un formulario con labels e inputs
     */
    private void putForm() {
        JPanel form = new JPanel();
        form.setBackground(DYE.getCONTENT());
        form.setLayout(new GridLayout(4,1));
        form.setBorder(new EmptyBorder(20,50,20,100)); // Top, left, bottom, right

        rowTitle = new RowForm("Título");
        form.add(rowTitle);

        rowDescription = new RowForm("Descripción");
        form.add(rowDescription);

        rowStartDate = new RowForm("Fecha de inicio (dd/mm/yyyy)");
        form.add(rowStartDate);

        form.add(putCombobox());

        management.add(form, BorderLayout.CENTER);
    }

    /**
     * Permite mostrar un panel con un Label y un Combobox de Entidades
     * @return devuelve un JPanel con los componentes asignados
     */
    private JPanel putCombobox() {
        JPanel comboPanel = new JPanel();
        comboPanel.setBorder(new EmptyBorder(20,75,20,297)); // Top, left, bottom, right
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.Y_AXIS));
        comboPanel.setBackground(DYE.getCONTENT());

        // Combobox
        JLabel lbEntity = new JLabel("Entidad");
        lbEntity.setFont(new Font("Open Sans", Font.BOLD, 14));
        lbEntity.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboPanel.add(lbEntity);

        cbEntity = new JComboBox<Entity>(entityDB.listEntities());
        cbEntity.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboPanel.add(cbEntity);

        return comboPanel;
    }


    /**
     * Añade los botones de la parte izquierda que permiten realizar toda la gestión de propuestas
     */
    private void putManagementButtons() {
        JPanel mButtonsProposal = new JPanel();
        mButtonsProposal.setLayout(new BoxLayout(mButtonsProposal, BoxLayout.Y_AXIS));
        mButtonsProposal.setBorder(new EmptyBorder(110,50,0,0)); // Top, left, bottom, right
        mButtonsProposal.setBackground(DYE.getCONTENT());

        // Botón Crear
        ImageButton btnCreate = new ImageButton("img/create.png", "CREAR");
        btnCreate.setPreferredSize(new Dimension(150, 40));
        btnCreate.setMaximumSize(new Dimension(150, 40));
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudProposal.createProposal(proposalTable, rowTitle.getTxtInput().getText(), rowDescription.getTxtInput().getText(), rowStartDate.getTxtInput().getText(), (Entity) cbEntity.getSelectedItem());
                cleanInputs();
            }
        });
        mButtonsProposal.add(btnCreate);

        // Crea un espacio en blanco de separación
        mButtonsProposal.add(Box.createRigidArea(new Dimension(0, 5)));

        // Botón editar
        ImageButton btnEdit = new ImageButton("img/edit.png", "MODIFICAR");
        btnEdit.setPreferredSize(new Dimension(150, 40));
        btnEdit.setMaximumSize(new Dimension(150, 40));
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudProposal.editProposal(proposalTable, rowTitle.getTxtInput().getText(), rowDescription.getTxtInput().getText(), rowStartDate.getTxtInput().getText(), cbEntity);
            }
        });
        mButtonsProposal.add(btnEdit);

        // Crea un espacio en blanco de separación
        mButtonsProposal.add(Box.createRigidArea(new Dimension(0, 5)));

        // Botón eliminar
        ImageButton btnDelete = new ImageButton("img/delete.png", "ELIMINAR");
        btnDelete.setPreferredSize(new Dimension(150, 40));
        btnDelete.setMaximumSize(new Dimension(150, 40));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudProposal.deleteProposal(proposalTable);
            }
        });
        mButtonsProposal.add(btnDelete);

        // Crea un espacio en blanco de separación
        mButtonsProposal.add(Box.createRigidArea(new Dimension(0, 5)));

        // Botón vaciar lista
        ImageButton btnEmpty = new ImageButton("img/empty.png", "VACIAR");
        btnEmpty.setPreferredSize(new Dimension(150, 40));
        btnEmpty.setMaximumSize(new Dimension(150, 40));
        btnEmpty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudProposal.emptyAll(proposalTable);
            }
        });
        mButtonsProposal.add(btnEmpty);

        management.add(mButtonsProposal, BorderLayout.WEST);
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
        String[] titulos = {"Título", "Descripción", "Fecha Inicio", "Entidad"};

        JPanel proposalPanelTable = new JPanel(new BorderLayout());
        proposalPanelTable.setBackground(DYE.getCONTENT());

        // Tabla
        JScrollPane scrollPane = new JScrollPane();
        proposalPanelTable.add(scrollPane, BorderLayout.CENTER);

        proposalTable = new JTable();

        // Modelo por defecto de la tabla
        proposalTable.setModel(new CustomTableModel(
                new Object [][] {

                },
                titulos
        ));

        // Diseño básico de la tabla
        CustomTableConfig.initConfig(proposalTable);

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
                    rowTitle.setTxtInput(proposalTable.getValueAt(proposalTable.getSelectedRow(), 0).toString());
                    rowDescription.setTxtInput(proposalTable.getValueAt(proposalTable.getSelectedRow(), 1).toString());
                    rowStartDate.setTxtInput(proposalTable.getValueAt(proposalTable.getSelectedRow(), 2).toString());
                    cbEntity.setSelectedItem(proposalTable.getValueAt(proposalTable.getSelectedRow(), 3));
                }
            }
        });

        tablePanel.add(proposalPanelTable, BorderLayout.CENTER);
    }


    /**
     * Método que permite limpiar el texto de los inputs (textfields)
     */
    private void cleanInputs() {
        rowTitle.setTxtInput("");
        rowDescription.setTxtInput("");
        rowStartDate.setTxtInput("");
    }
}
