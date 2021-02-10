package custom.panels;

import custom.other.ImageButton;
import custom.other.RowForm;
import custom.tables.CustomTableConfig;
import modules.CrudUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeesContent extends ContentWindow {
    // Paneles
    private JPanel module;
    private JPanel management;

    // Formulario
    private RowForm rowName;
    private RowForm rowDni;
    private RowForm rowNss;
    private RowForm rowEmployeeId;

    // Tabla
    private static JTable userTable;

    // Constructor
    public EmployeesContent() {
        super("GESTIÓN DE EMPLEADOS");
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

        this.putUserListTable();

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

        module.add(management);
    }

    /**
     * Añade un formulario con labels e inputs
     */
    private void putForm() {
        JPanel form = new JPanel();
        form.setBackground(DYE.getCONTENT());
        form.setLayout(new GridLayout(4, 1));
        form.setBorder(new EmptyBorder(20,50,20,100)); // Top, left, bottom, right

        rowName = new RowForm("Nombre");
        form.add(rowName);

        rowDni = new RowForm("DNI");
        form.add(rowDni);

        rowNss = new RowForm("NSS");
        form.add(rowNss);

        rowEmployeeId = new RowForm("Cod. Empleado");
        form.add(rowEmployeeId);

        management.add(form, BorderLayout.CENTER);
    }

    /**
     * Añade los botones de la parte superior que permiten realizar toda la gestión de empleados
     */
    private void putManagementButtons() {

        JPanel mButtonsEmployee = new JPanel();
        mButtonsEmployee.setLayout(new BoxLayout(mButtonsEmployee, BoxLayout.Y_AXIS));
        mButtonsEmployee.setBorder(new EmptyBorder(110,100,10,0)); // Top, left, bottom, right
        mButtonsEmployee.setBackground(DYE.getCONTENT());

        // Botón Crear
        ImageButton btnCreate = new ImageButton("create.png", "CREAR");
        btnCreate.setPreferredSize(new Dimension(150, 40));
        btnCreate.setMaximumSize(new Dimension(150, 40));
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudUser.createUser(userTable, rowName.getTxtInput().getText(), rowDni.getTxtInput().getText(), rowNss.getTxtInput().getText(), rowEmployeeId.getTxtInput().getText());
                cleanInputs();
            }
        });
        mButtonsEmployee.add(btnCreate);

        // Crea un espacio en blanco de separación
        mButtonsEmployee.add(Box.createRigidArea(new Dimension(0, 5)));

        // Botón editar
        ImageButton btnEdit = new ImageButton("edit.png", "MODIFICAR");
        btnEdit.setPreferredSize(new Dimension(150, 40));
        btnEdit.setMaximumSize(new Dimension(150, 40));
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudUser.editUser(userTable, rowName.getTxtInput().getText(), rowDni.getTxtInput().getText(), rowNss.getTxtInput().getText(), rowEmployeeId.getTxtInput().getText());
            }
        });
        mButtonsEmployee.add(btnEdit);

        // Crea un espacio en blanco de separación
        mButtonsEmployee.add(Box.createRigidArea(new Dimension(0, 5)));

        // Botón eliminar
        ImageButton btnDelete = new ImageButton("delete.png", "ELIMINAR");
        btnDelete.setPreferredSize(new Dimension(150, 40));
        btnDelete.setMaximumSize(new Dimension(150, 40));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudUser.deleteUser(userTable);
            }
        });
        mButtonsEmployee.add(btnDelete);

        // Crea un espacio en blanco de separación
        mButtonsEmployee.add(Box.createRigidArea(new Dimension(0, 5)));

        // Botón vaciar lista
        ImageButton btnEmpty = new ImageButton("empty.png", "VACIAR");
        btnEmpty.setPreferredSize(new Dimension(150, 40));
        btnEmpty.setMaximumSize(new Dimension(150, 40));
        btnEmpty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudUser.emptyAll(userTable);
            }
        });
        mButtonsEmployee.add(btnEmpty);

        mButtonsEmployee.setVisible(true);

        management.add(mButtonsEmployee, BorderLayout.WEST);
    }

    /**
     * Añade una tabla y su configuración a la interfaz
     */
    private void putUserListTable() {
        JPanel panelTable = new JPanel(new BorderLayout());
        panelTable.setBackground(DYE.getCONTENT());

        // Tabla
        JScrollPane scrollPane = new JScrollPane();
        panelTable.add(scrollPane, BorderLayout.CENTER);

        userTable = new JTable();

        // Modelo por defecto de la tabla
        userTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Nombre", "DNI", "NSS", "Cod. Empleado"
                }
        ));

        // Diseño básico de la tabla
        CustomTableConfig.initConfig(userTable);

        scrollPane.setViewportView(userTable);


        // Carga los datos de una fila cuando hacemos clic en ella
        userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = userTable.getSelectedRow();

                // Condición que limpia datos cada vez que seleccionamos una fila (sino peta)
                if (selectedRow == -1) {
                    cleanInputs();
                }
                else {
                    rowName.setTxtInput(userTable.getValueAt(userTable.getSelectedRow(), 0).toString());
                    rowDni.setTxtInput(userTable.getValueAt(userTable.getSelectedRow(), 1).toString());
                    rowNss.setTxtInput(userTable.getValueAt(userTable.getSelectedRow(), 2).toString());
                    rowEmployeeId.setTxtInput(userTable.getValueAt(userTable.getSelectedRow(), 3).toString());
                }
            }
        });

        module.add(panelTable, BorderLayout.CENTER);
    }

    /**
     * Método que permite limpiar el texto de los inputs (textfields)
     */
    private void cleanInputs() {
        rowName.setTxtInput("");
        rowDni.setTxtInput("");
        rowNss.setTxtInput("");
        rowEmployeeId.setTxtInput("");
    }
}