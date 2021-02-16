package mainclasses.database;

import mainclasses.entity.Company;
import mainclasses.entity.Entity;
import mainclasses.entity.School;

/**
 * Grupo Individual Sprint 3 2021 - Carlos Masana
 * Clase EntityDB: Simula una base de datos con objetos de tipo Entidad
 */
public  class EntityDB {
    // Utilizado para combobox
    private final Entity[] lista;

    // Constructor vacío
    public EntityDB() {
        lista = new Entity[4];
        lista[0] = new School("IES Montsià", "Amposta",977700043, "43006101");
        lista[1] = new School("IES Eduardo Blanco Amor", "Ourense",988219843, "32008941");
        lista[2] = new Company("Aluminis Jovi SL", "La Ràpita",977744089, "B43437276");
        lista[3] = new Company("Cabreiroá SA", "Verín",988590015, "A32004194");
    }

    /**
     * Muestra el array de tipo Entidad
     * @return devuelve el array de tipo Entidad con los objetos creados
     */
    public Entity[] listEntities() {
        return lista;
    }
}


