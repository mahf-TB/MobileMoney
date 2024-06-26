/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Models;

/**
 *
 * @author macbookpro
 */
public class Retrait {
    private int id;
    private String dateRet;
    private int idtauxrec;

    public Retrait() {
        super();
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateRet() {
        return dateRet;
    }

    public void setDateRet(String dateRet) {
        this.dateRet = dateRet;
    }

    public int getIdtauxrec() {
        return idtauxrec;
    }

    public void setIdtauxrec(int idtauxrec) {
        this.idtauxrec = idtauxrec;
    }

    
    @Override
    public String toString() {
        return "Retrait{" + "id=" + id + ", dateRet=" + dateRet + ", idtauxrec=" + idtauxrec + '}';
    }   
    
}
