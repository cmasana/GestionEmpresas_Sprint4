package mainclasses.database;

import mainclasses.user.Employee;
import java.util.ArrayList;

/**
 * Grupo Individual Sprint 3 2021 - Carlos Masana
 * Clase EmployeeDB: Simula una base de datos con usuarios de tipo Empleado
 */
public  class EmployeeDB extends ArrayList<Employee> {
    // ArrayList que simula la base de datos de Empleados
    private final ArrayList<Employee> listaEmpleados;

    // Constructor vacío
    public EmployeeDB() {
        listaEmpleados  = new ArrayList<Employee>();
    }

    // Métodos para realizar operaciones básicas en nuestro ArrayList

    /**
     * Añadir empleado al array list
     * @param emp Objeto de la clase empleado
     */
    public void addEmployee(Employee emp) {
        listaEmpleados.add(emp);
    }

    /**
     * Elimina un empleado del ArrayList
     * @param posicion posición del empleado dentro del ArrayList
     */
    public void removeEmployee(int posicion) {
        listaEmpleados.remove(posicion);
    }

    /**
     * Obtiene un empleado desde el ArrayList
     * @param posicion posición del empleado dentro del ArrayList
     * @return devuelve un objeto de la clase Empleado
     */
    public Employee getEmployeeFromDB(int posicion) {
        return listaEmpleados.get(posicion);
    }

    /**
     * Obtiene el tamaño del ArrayList de Empleados
     * @return devuelve un entero con el tamaño del ArrayList de tipo Empleado
     */
    public int sizeEmployeeDB() {
        return listaEmpleados.size();
    }
}


