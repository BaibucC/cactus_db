/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cactus.entity;

import javax.validation.constraints.NotEmpty;

public class Bike {

    private int id;
    private String bikename;
    private Boolean status;
    private Boolean available;

    public Bike() {
    }

    public Bike(String bikename, Boolean status, Boolean available) {
        this.id = id;
        this.bikename = bikename;
        this.status = status;
        this.available = available;
    }

    /**
     * @return the bikename
     */
    public String getBikename() {
        return bikename;
    }

    /**
     * @param bikename the bikename to set
     */
    public void setBikename(String bikename) {
        this.bikename = bikename;
    }

    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return the available
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

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
}
