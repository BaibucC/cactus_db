/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cactus.entity;

/**
 *
 * @author Baiba
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table (name = "current_stock")
public class Cactus {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "genera")
    private String genera;
    @Column(name = "species")
    private String species;
    @Column(name = "origin")
    private String origin;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the genera
     */
    public String getGenera() {
        return genera;
    }

    /**
     * @param genera the genera to set
     */
    public void setGenera(String genera) {
        this.genera = genera;
    }

    /**
     * @return the species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * @param species the species to set
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }
}