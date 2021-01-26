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
        lista[0] = new School("IES Montsià", "Amposta", 977123456, "TGN123");
        lista[1] = new School("IES Blanco Amor", "Ourense", 988123456, "GAL123");
        lista[2] = new Company("Bonusan España SL", "Alicante", 966123456, "123456");
        lista[3] = new Company("Cabreiroá SA", "Verín", 988456789, "345678");
    }

    /**
     * Muestra el array de tipo Entidad
     * @return devuelve el array de tipo Entidad con los objetos creados
     */
    public Entity[] listEntities() {
        return lista;
    }
}


