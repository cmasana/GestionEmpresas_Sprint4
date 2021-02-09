package modules;

import custom.tables.CustomTableConfig;
import custom.tables.CustomTableModel;
import mainclasses.database.EmployeeDB;
import mainclasses.io.InputOutput;
import mainclasses.user.Employee;
import javax.swing.*;

/**
 * Clase CrudUser: Implementa todos los métodos para la gestión de empleados
 */
public class CrudUser {
    // Array de tipo EmpleadoDB
    private final static EmployeeDB employeeList = new EmployeeDB();

    // Array normal, solo para utilizar con JComboBox
    private final static Employee[] arrayEmp = new Employee[employeeList.sizeEmployeeDB()];

    /**
     * Permite crear un usuario y visualizarlo en tiempo real en su correspondiente tabla
     * @param userTable tabla dónde visualizamos los empleados creados
     * @param name nombre del empleado
     * @param dni dni del empleado
     * @param nss nss del empleado
     * @param employeeId codigo del empleado
     */
    public static void createUser(JTable userTable, String name, String dni, String nss, String employeeId) {

        // Si hay algún campo vacío
        if (name.isEmpty() || dni.isEmpty() || nss.isEmpty() || employeeId.isEmpty()) {
            InputOutput.printError("Error: Por favor, rellene todos los campos");
        }
        else {
            // Creamos objeto de la clase empleado
            Employee emp = new Employee(name, dni, nss, employeeId);

            // Lo añadimos al arraylist de tipo Empleado
            employeeList.addEmployee(emp);

            // Actualizamos los datos en la tabla
            showData(userTable);
        }
    }

    /**
     * Permite eliminar un usuario
     * @param userTable tabla dónde se visualizan los empleados
     */
    public static void deleteUser(JTable userTable) {

        // Guardamos el número de fila (coincide con la posición en el ArrayList)
        int row = userTable.getSelectedRow();

        // Si el resultado es mayor o igual a 0, eliminamos el empleado
        if (row >= 0) {
            employeeList.removeEmployee(row);

            // Actualizamos datos de la tabla
            showData(userTable);
        }
        // En caso contrario, mostramos un error por pantalla
        else {
            InputOutput.printError("Error: Selecciona una fila");
        }
    }

    /**
     * Permite eliminar todos los empleados
     * @param userTable tabla dónde se visualizan todos los empleados
     */
    public static void emptyAll(JTable userTable) {

        // Almacenamos el nº total de filas que hay en la tabla
        int totalrows = userTable.getRowCount();

        if (totalrows != 0) {
            // Recorremos el arrayList y eliminamos 1 a 1 los registros
            for (int i = (totalrows - 1); i >= 0; i--) {
                employeeList.removeEmployee(i);
            }

            // Actualizamos los datos de la tabla
            showData(userTable);
        }
        else {
            InputOutput.printError("Error: No hay ningún empleado creado");
        }
    }

    /**
     * Permite modificar un empleado
     * @param userTable tabla dónde se visualizan los empleados
     * @param name nombre del empleado
     * @param dni dni del empleado
     * @param nss nss del empleado
     * @param employeeId codigo del empleado
     */
    public static void editUser(JTable userTable, String name, String dni, String nss, String employeeId) {

        // Almacenamos el nº total de filas que hay en la tabla
        int totalRows = userTable.getRowCount();

        // Almacena el nº de fila seleccionado (coincide con el índice en el ArrayList)
        int selectedRow = userTable.getSelectedRow();

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
                if (name.isEmpty() || dni.isEmpty() || nss.isEmpty() || employeeId.isEmpty()) {
                    InputOutput.printError("Error: Por favor, rellene todos los campos");
                } else {
                    employeeList.getEmployeeFromDB(selectedRow).setName(name);
                    employeeList.getEmployeeFromDB(selectedRow).setDni(dni);
                    employeeList.getEmployeeFromDB(selectedRow).setNss(nss);
                    employeeList.getEmployeeFromDB(selectedRow).setEmployeeId(employeeId);

                    // Actualizamos datos de la tabla
                    showData(userTable);
                }
            }
        }
    }

    /**
     * Muestra los datos actualizados en la tabla de Usuarios
     * @param userTable tabla dónde se visualizan los empleados creados
     */
    public static void showData(JTable userTable) {

        // Creamos array de tipo string e inicializamos con el tamaño del ArrayList
        String[][] tablaUsuarios = new String[employeeList.sizeEmployeeDB()][4];

        // Recorre la lista de Empleados
        for(int i = 0; i < employeeList.sizeEmployeeDB(); i++) {

            // Datos de cada Empleado
            tablaUsuarios[i][0] = employeeList.getEmployeeFromDB(i).getName();
            tablaUsuarios[i][1] = employeeList.getEmployeeFromDB(i).getDni();
            tablaUsuarios[i][2] = employeeList.getEmployeeFromDB(i).getNss();
            tablaUsuarios[i][3] = employeeList.getEmployeeFromDB(i).getEmployeeId();
        }

        // Añade los datos al modelo
        userTable.setModel(new CustomTableModel(
                tablaUsuarios,
                new String [] {
                        "Nombre", "DNI", "NSS", "Cod. Empleado"
                }
        ));

        // Diseño básico de la tabla
        CustomTableConfig.initConfig(userTable);
    }
}
