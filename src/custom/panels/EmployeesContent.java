package custom.panels;

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

    // Labels
    private JLabel lbName;
    private JLabel lbDni;
    private JLabel lbNss;
    private JLabel lbEmployeeId;

    // Textfields (inputs)
    private JTextField txtName;
    private JTextField txtDni;
    private JTextField txtNss;
    private JTextField txtEmployeeId;

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
        form.setLayout(new GridLayout(12, 5, 10,10));
        form.setBorder(new EmptyBorder(0,50,0,0)); // Top, left, bottom, right
        form.setPreferredSize(new Dimension(500, 400));

        lbName = new JLabel("Nombre");
        form.add(lbName);
        txtName = new JTextField();
        txtName.setColumns(5);
        lbName.setLabelFor(txtName);
        form.add(txtName);

        lbDni = new JLabel("DNI");
        form.add(lbDni);
        txtDni = new JTextField();
        txtDni.setColumns(5);
        lbDni.setLabelFor(txtDni);
        form.add(txtDni);

        lbNss = new JLabel("NSS");
        form.add(lbNss);
        txtNss = new JTextField();
        txtNss.setColumns(5);
        lbNss.setLabelFor(txtNss);
        form.add(txtNss);

        lbEmployeeId = new JLabel("Cod. Empleado");
        form.add(lbEmployeeId);
        txtEmployeeId = new JTextField();
        txtEmployeeId.setColumns(5);
        lbEmployeeId.setLabelFor(txtEmployeeId);
        form.add(txtEmployeeId);

        management.add(form, BorderLayout.CENTER);
    }

    /**
     * Añade los botones de la parte superior que permiten realizar toda la gestión de empleados
     */
    private void putManagementButtons() {

        JPanel mButtonsEmployee = new JPanel();
        mButtonsEmployee.setLayout(new GridLayout(10, 1, 10,10));
        mButtonsEmployee.setBorder(new EmptyBorder(110,0,0,0)); // Top, left, bottom, right
        mButtonsEmployee.setBackground(DYE.getCONTENT());

        // Botón Crear
        JButton btnCreate = new JButton("CREAR");
        btnCreate.setPreferredSize(new Dimension(120, 35));
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudUser.createUser(userTable, txtName.getText(), txtDni.getText(), txtNss.getText(), txtEmployeeId.getText());
                cleanInputs();
            }
        });
        mButtonsEmployee.add(btnCreate);

        // Botón editar
        JButton btnEdit = new JButton("MODIFICAR");
        btnEdit.setPreferredSize(new Dimension(120, 35));
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudUser.editUser(userTable, txtName.getText(), txtDni.getText(), txtNss.getText(), txtEmployeeId.getText());
            }
        });
        mButtonsEmployee.add(btnEdit);

        // Botón eliminar
        JButton delUser = new JButton("ELIMINAR");
        delUser.setPreferredSize(new Dimension(120, 35));
        delUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudUser.deleteUser(userTable);
            }
        });
        mButtonsEmployee.add(delUser);

        // Botón vaciar lista
        JButton btnEmpty = new JButton("VACIAR");
        btnEmpty.setPreferredSize(new Dimension(120, 35));
        btnEmpty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CrudUser.emptyAll(userTable);
            }
        });
        mButtonsEmployee.add(btnEmpty);

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
                    txtName.setText(userTable.getValueAt(userTable.getSelectedRow(), 0).toString());
                    txtDni.setText(userTable.getValueAt(userTable.getSelectedRow(), 1).toString());
                    txtNss.setText(userTable.getValueAt(userTable.getSelectedRow(), 2).toString());
                    txtEmployeeId.setText(userTable.getValueAt(userTable.getSelectedRow(), 3).toString());
                }
            }
        });

        module.add(panelTable, BorderLayout.CENTER);
    }

    /**
     * Método que permite limpiar el texto de los inputs (textfields)
     */
    private void cleanInputs() {
        txtName.setText("");
        txtDni.setText("");
        txtNss.setText("");
        txtEmployeeId.setText("");
    }
}