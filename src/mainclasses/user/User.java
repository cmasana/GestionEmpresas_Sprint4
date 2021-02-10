package mainclasses.user;

/**
 * Grup Individual Sprint 3 2020
 * @author Carlos Masana
 * Clase User
 */
public abstract class User {
    protected String name;
    protected int dni;
    protected int nss;

    /**
     * Constructor vacío de la clase User
     */
    public User() {
        this.name = "";
        this.dni = 0;
        this.nss = 0;
    }

    /**
     * Constructor sobrecargado de la clase User
     *
     * @param name nombre del Usuario
     * @param dni documento nacional de identificación del Usuario
     * @param nss número de la seguridad social del Usuario
     */
    public User(String name, int dni, int nss) {
        this.name = name;
        this.dni = dni;
        this.nss = nss;
    }

    /**
     * Getters & Setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getNss() {
        return nss;
    }

    public void setNss(int nss) {
        this.nss = nss;
    }

    @Override
    public String toString() {
        return "Nombre: " + name + " | " +
                "DNI: " + dni + " | " +
                "NSS: " + nss + " | ";
    }
}