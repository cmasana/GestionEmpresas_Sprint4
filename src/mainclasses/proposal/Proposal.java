package mainclasses.proposal;

import mainclasses.entity.Entity;

/**
 * Grupo Individual Sprint 3 2020 - Carlos Masana -
 *
 * Clase Proposal: Define los atributos y métodos de la clase Proposal
 */
public class Proposal {
    private String name;
    private String description;
    private String startDate;
    private int queryCount;
    private Entity entity;

    public Proposal(String name, String description, String startDate, Entity entity) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.queryCount = 0;
        this.entity = entity;
    }

    @Override
    public String toString() {
        this.queryCount++;
        return "Título: " + name + " | " +
                "Descripción: " + description + " | " +
                "Fecha de inicio: " + startDate + " | " +
                "Entidad asociada: " + entity.getNombre() + " | " +
                "Consultada: " + queryCount + " veces | ";
    }

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(int queryCount) {
        this.queryCount = queryCount;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

}
