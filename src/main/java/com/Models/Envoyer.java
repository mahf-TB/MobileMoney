/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Models;

/**
 *
 * @author macbookpro
 */
public class Envoyer {
    private int id;
    private boolean is_frais_retrait;
    private String dateEnv;
    private int idtauxenv;

    public Envoyer() {
        super();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs_frais_retrait() {
        return is_frais_retrait;
    }

    public void setIs_frais_retrait(boolean is_frais_retrait) {
        this.is_frais_retrait = is_frais_retrait;
    }

    public String getDateEnv() {
        return dateEnv;
    }

    public void setDateEnv(String dateEnv) {
        this.dateEnv = dateEnv;
    }

    public int getIdtauxenv() {
        return idtauxenv;
    }

    public void setIdtauxenv(int idtauxenv) {
        this.idtauxenv = idtauxenv;
    }

    @Override
    public String toString() {
        return "Envoyer{" + "id=" + id + ", is_frais_retrait=" + is_frais_retrait + ", dateEnv=" + dateEnv + ", idtauxenv=" + idtauxenv + '}';
    }
    
    
}
